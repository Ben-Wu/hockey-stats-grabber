package ca.benwu.model.raw

case class Stat(games: Option[Int], goals: Option[Int], assists: Option[Int], points: Option[Int],
                pim: Option[Int], plusMinus: Option[Int])