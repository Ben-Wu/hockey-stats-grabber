package ca.benwu.model

case class StatYear(playerId: Int, season: String, games: Option[Int], goals: Option[Int], assists: Option[Int],
                    points: Option[Int], pim: Option[Int], plusMinus: Option[Int], team: String, league: String)
