resource "aws_sfn_state_machine" "main_state_machine" {
  name = "nhl-stats-grabber"
  role_arn = "${aws_iam_role.sfn_iam_role.arn}"
  definition = <<EOF
{
  "Comment": "Collect players stats and store them in DB",
  "StartAt": "GetPlayers",
  "States": {
    "GetPlayers": {
      "Type": "Task",
      "Resource": "${aws_lambda_function.get_players_lambda.arn}",
      "Next": "GetPlayerStats"
    },
    "GetPlayerStats": {
      "Type": "Task",
      "Resource": "${aws_lambda_function.get_player_stats_lambda.arn}",
      "End": true
    }
  }
}
EOF
}
