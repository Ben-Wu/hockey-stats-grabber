package ca.benwu

import com.amazonaws.services.lambda.runtime.{Context, RequestHandler}

import ca.benwu.model.{LambdaInput, LambdaResponse}
import ca.benwu.network.NhlApi

class Handler extends RequestHandler[Input, LambdaResponse] {
  def handleRequest(input: Input, context: Context): LambdaResponse = {
    var playerCount = 0
    for (year <- input.startYear to input.endYear) {
      val season = s"$year${year+1}"
      println(s"Getting players for $season")
      val players = NhlApi.getRosters(season)
      playerCount = playerCount + players.length
      println(players.length)
    }
    LambdaResponse(playerCount.toString, new LambdaInput())
  }
}
