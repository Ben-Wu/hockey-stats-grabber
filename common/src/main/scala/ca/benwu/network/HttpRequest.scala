package ca.benwu.network

import scalaj.http._

object HttpRequest {

  def get(baseUrl: String, endpoint: String = "",
          queryParams: Map[String, String] = Map()): String = {
    Http(s"$baseUrl$endpoint").params(queryParams).asString.body
  }
}
