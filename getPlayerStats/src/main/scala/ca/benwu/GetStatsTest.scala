package ca.benwu

import ca.benwu.model.MockContext

import collection.JavaConverters._
import ca.benwu.model.input.GetPlayerStatsInput

object GetStatsTest {
  def main(args: Array[String]): Unit = {
    val input = GetPlayerStatsInput(List(8464956, 8466139).asJava, 2018, 2018, done = false)
    val output = new Handler().handleRequest(input, MockContext())
    println(output)
  }
}
