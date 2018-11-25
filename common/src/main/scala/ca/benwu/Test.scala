package ca.benwu

import ca.benwu.network.NhlApi

object Test {
  def main(args: Array[String]): Unit = {
    NhlApi.getRosters("20172018")
  }
}
