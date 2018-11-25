package ca.benwu

import ca.benwu.model.MockContext

object Test {
  def main(args: Array[String]): Unit = {
    val input = Input(2014, 2019)
    val output = new Handler().handleRequest(input, MockContext())
    println(output)
  }
}
