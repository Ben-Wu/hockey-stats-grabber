resource "aws_lambda_function" "get_players_lambda" {
  filename = "../getPlayers/target/scala-2.12/getPlayers.jar"
  role = "${aws_iam_role.lambda_iam_role.arn}"
  function_name = "nhl-stats-grabber-getPlayers"
  runtime = "java8"
  handler = "ca.benwu.Handler"
  source_code_hash = "${base64sha256(file("../getPlayers/target/scala-2.12/getPlayers.jar"))}"
  timeout = 300
  memory_size = 512
}

resource "aws_lambda_function" "get_player_stats_lambda" {
  filename = "../getPlayerStats/target/scala-2.12/getPlayerStats.jar"
  role = "${aws_iam_role.lambda_iam_role.arn}"
  function_name = "nhl-stats-grabber-getPlayerStats"
  runtime = "java8"
  handler = "ca.benwu.Handler"
  source_code_hash = "${base64sha256(file("../getPlayerStats/target/scala-2.12/getPlayerStats.jar"))}"
  timeout = 60
  memory_size = 512
}

