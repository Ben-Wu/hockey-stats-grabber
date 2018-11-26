package ca.benwu

import com.amazonaws.services.lambda.runtime.{Context, RequestHandler}

import ca.benwu.model.input.{GetPlayerInput, GetPlayerStatsInput}

class Handler extends RequestHandler[GetPlayerStatsInput, GetPlayerInput] {

  def handleRequest(input: GetPlayerStatsInput, context: Context): GetPlayerInput = {
    println(s"${input.playerIds}")
    GetPlayerInput(input.year + 1, input.endYear)
  }
}
