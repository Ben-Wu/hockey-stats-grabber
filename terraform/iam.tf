resource "aws_iam_role" "lambda_iam_role" {
  name = "nhl-stats-grabber-lambda"
  assume_role_policy = <<EOF
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Effect": "Allow",
      "Action": "sts:AssumeRole",
      "Principal": {
        "Service": "lambda.amazonaws.com"
      },
      "Sid": ""
    }
  ]
}
EOF
}

resource "aws_iam_role_policy" "lambda_policy" {
  name = "nhl-stats-grabber-lambda"
  role = "${aws_iam_role.lambda_iam_role.id}"
  policy = <<EOF
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Effect": "Allow",
      "Action": [
        "logs:CreateLogGroup",
        "logs:CreateLogStream",
        "logs:PutLogEvents"
      ],
      "Resource": "arn:aws:logs:*:*:*"
    },
    {
      "Effect": "Allow",
      "Action": [
        "dynamodb:GetItem",
        "dynamodb:PutItem",
        "dynamodb:UpdateItem",
        "dynamodb:DeleteItem",
        "dynamodb:BatchWriteItem"
      ],
      "Resource": [
        "${aws_dynamodb_table.player_table.arn}",
        "${aws_dynamodb_table.player_stats_table.arn}"
      ]
    }
  ]
}
EOF
}

resource "aws_iam_role" "sfn_iam_role" {
name = "nhl-stats-grabber-sfn"
  assume_role_policy = <<EOF
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Effect": "Allow",
      "Action": "sts:AssumeRole",
      "Principal": {
        "Service": "states.amazonaws.com"
      },
      "Sid": ""
    }
  ]
}
EOF
}

resource "aws_iam_role_policy" "sfn_policy" {
  name = "nhl-stats-grabber-sfn"
  role = "${aws_iam_role.sfn_iam_role.id}"
  policy = <<EOF
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Effect": "Allow",
      "Action": [
        "lambda:InvokeFunction"
      ],
      "Resource": [
        "${aws_lambda_function.get_players_lambda.arn}",
        "${aws_lambda_function.get_player_stats_lambda.arn}"
      ]
    }
  ]
}
EOF
}

