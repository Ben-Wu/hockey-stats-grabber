package ca.benwu.network

import org.json4s._
import org.json4s.native.JsonMethods._

import ca.benwu.model.Player
import ca.benwu.model.raw.Team

object NhlApi {

  val baseUrl = "https://statsapi.web.nhl.com"

  def getRosters(year: String): List[Player] = {
    val queryParams = Map("season" -> year, "expand" -> "team.roster")
    val response = HttpRequest.get(baseUrl, "/api/v1/teams", queryParams)

    implicit val formats = DefaultFormats

    val rosters = (parse(response) \ "teams").extract[List[Team]].map(team => team.roster)

    rosters.filter(roster => roster.isDefined)
      .flatMap(roster => roster.get.roster)
      .map(unparsed => Player(unparsed.person.id, unparsed.person.fullName,
        unparsed.jerseyNumber, unparsed.position.name))
  }
}
