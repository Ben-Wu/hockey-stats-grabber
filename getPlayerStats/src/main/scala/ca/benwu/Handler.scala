package ca.benwu

import ca.benwu.model.StatYear
import com.amazonaws.services.lambda.runtime.{Context, RequestHandler}
import ca.benwu.model.input.{GetPlayerInput, GetPlayerStatsInput}
import ca.benwu.network.NhlApi
import ca.benwu.storage.StorageApi

class Handler extends RequestHandler[GetPlayerStatsInput, GetPlayerInput] {

  def handleRequest(input: GetPlayerStatsInput, context: Context): GetPlayerInput = {
    println(s"Getting stats for ${input.playerIds.size()} players")

    input.playerIds.forEach(playerId => {
      println(s"Getting stats for player $playerId")
      val stats = NhlApi.getYearByYearStats(playerId)
      val statYears = stats.map(stat => StatYear(playerId,
        stat.season, stat.stat.games, stat.stat.goals,
        stat.stat.assists, stat.stat.points, stat.stat.pim,
        stat.stat.plusMinus, stat.team.name, stat.league.name))
      statYears.filter(statYear => statYear.league == "National Hockey League")
        .foreach(statYear => StorageApi.writeStatYear(statYear))
    })

    GetPlayerInput(input.year + 1, input.endYear)
  }
}
