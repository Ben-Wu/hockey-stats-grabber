package ca.benwu

import ca.benwu.model.MockContext
import ca.benwu.model.input.GetPlayerInput

object GetPlayersTest {
  def main(args: Array[String]): Unit = {
    val input = GetPlayerInput(2014, 2019)
    val output = new Handler().handleRequest(input, MockContext())
    println(output)
  }
}
