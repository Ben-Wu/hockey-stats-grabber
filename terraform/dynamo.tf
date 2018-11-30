resource "aws_dynamodb_table" "player_table" {
  name = "hockey-stats-grabber-players"
  read_capacity = 8
  write_capacity = 8
  hash_key = "PlayerId"

  attribute {
    name = "PlayerId"
    type = "N"
  }
}

resource "aws_dynamodb_table" "player_stats_table" {
  name = "hockey-stats-grabber-playerStats"
  read_capacity = 16
  write_capacity = 16
  hash_key = "PlayerId"
  range_key = "Season"

  attribute = [{
    name = "PlayerId"
    type = "N"
  }, {
    name = "Season"
    type = "S"
  }]
}
