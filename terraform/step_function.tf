resource "aws_sfn_state_machine" "main_state_machine" {
  name = "nhl-stats-grabber"
  role_arn = "${aws_iam_role.sfn_iam_role.arn}"
  definition = <<EOF
{
  "Comment": "Collect player data and stats and store them in database",
  "StartAt": "GetPlayers",
  "States": {
    "GetPlayers": {
      "Type": "Task",
      "Resource": "${aws_lambda_function.get_players_lambda.arn}",
      "Next": "CheckYear"
    },
    "GetPlayerStats": {
      "Type": "Task",
      "Resource": "${aws_lambda_function.get_player_stats_lambda.arn}",
      "Next": "GetPlayers"
    },
    "CheckYear": {
      "Type": "Choice",
      "Choices": [
      {
        "Variable": "$.done",
        "BooleanEquals": true,
        "Next": "SuccessState"
      }
      ],
      "Default": "GetPlayerStats"
    },
    "SuccessState": {
      "Type": "Succeed"
    }
  }
}
EOF
}
