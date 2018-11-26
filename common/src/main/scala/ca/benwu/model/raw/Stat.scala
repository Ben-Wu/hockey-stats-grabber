package ca.benwu.model.raw

case class Stat(games: Int, goals: Int, assists: Int, points: Int, pim: Option[Int], plusMinus: Option[Int])