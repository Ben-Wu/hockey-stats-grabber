package ca.benwu

import ca.benwu.network.NhlApi

object ApiTest {
  def main(args: Array[String]): Unit = {
    NhlApi.getRosters("20172018")
    NhlApi.getYearByYearStats(8474012)
  }
}
