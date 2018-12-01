# Hockey Stats Grabber

ETL pipeline for NHL roster data taking team rosters and storing player information and stats to a database.

Runs on AWS using a Step Function state machine to execute Lambda functions, saving results to DynamoDB.

### Execution Visualization

![execution visualization](/images/execution.png)

## Usage

### Setup 

Requires AWS credentials set up using [AWS CLI](https://aws.amazon.com/cli/) (uses default)

Install [sbt](https://github.com/sbt/sbt) and [Terraform](https://github.com/hashicorp/terraform)

### Deploying

`make build` will compile the sources and package the JARs that will be uploaded to Lambda.

`make update` will provision the AWS resources required and also upload the built JARs 
if any changes are detected

`make deploy` will both create the JARs and upload them to Lambda

### Running

Jobs can be started through the Step Function console with input:
```json
{
  "startYear": 2017,
  "endYear": 2017
}
```

Or by running `make start` ([Ammonite](https://github.com/lihaoyi/ammonite) must be installed)