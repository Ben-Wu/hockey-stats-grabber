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

start:
	amm scripts/start_job.sc

.PHONY: build buildplayers buildstats deploy start
