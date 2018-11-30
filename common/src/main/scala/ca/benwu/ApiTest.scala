package ca.benwu

import ca.benwu.model.StatYear
import ca.benwu.network.NhlApi

object ApiTest {
  def main(args: Array[String]): Unit = {
    val rosters = NhlApi.getRosters("20172018")
    val stats = NhlApi.getYearByYearStats(8464956)
    val statYears = stats.map(stat => StatYear(8464956,
      stat.season, stat.stat.games, stat.stat.goals,
      stat.stat.assists, stat.stat.points, stat.stat.pim, stat.stat.plusMinus,
      stat.team.name, stat.league.name))
    val a = 1
  }
}
