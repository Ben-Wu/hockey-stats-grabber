buildplayers:
	sbt getPlayers/assembly

buildstats:
	sbt getPlayerStats/assembly

build: buildplayers buildstats

update:
	$(MAKE) -C terraform apply

deploy: build update

clean:
	sbt clean

.PHONY: build buildplayers buildstats deploy
