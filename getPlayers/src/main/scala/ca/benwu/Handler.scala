package ca.benwu

import collection.JavaConverters._
import scala.collection.mutable
import com.amazonaws.services.lambda.runtime.{Context, RequestHandler}
import ca.benwu.model.input.{GetPlayerInput, GetPlayerStatsInput}
import ca.benwu.network.NhlApi
import ca.benwu.storage.StorageApi

class Handler extends RequestHandler[GetPlayerInput, GetPlayerStatsInput] {

  def handleRequest(input: GetPlayerInput, context: Context): GetPlayerStatsInput = {
    val year = input.startYear

    if (year > input.endYear) {
      GetPlayerStatsInput(mutable.Buffer[Int]().asJava, year, input.endYear, done = true)
    } else {
      val season = s"$year${year + 1}"
      println(s"Getting players for $season")

      val players = NhlApi.getRosters(season)
      println(s"Got ${players.length} players for $season")

      val writtenPlayers = players.filter(player => StorageApi.writePlayer(player))
      println(s"${writtenPlayers.length} players for saved for $season")

      GetPlayerStatsInput(writtenPlayers.map(player => player.id).toBuffer.asJava,
        year, input.endYear, done = false)
    }
  }
}
