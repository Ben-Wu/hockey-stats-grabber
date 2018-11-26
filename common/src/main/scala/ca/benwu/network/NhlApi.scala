package ca.benwu.network

import org.json4s._
import org.json4s.native.JsonMethods._
import ca.benwu.model.Player
import ca.benwu.model.raw.{Team, YearByYearStats}

object NhlApi {

  val baseUrl = "https://statsapi.web.nhl.com"

  implicit lazy val formats = DefaultFormats

  def getRosters(season: String): List[Player] = {
    val queryParams = Map("season" -> season, "expand" -> "team.roster")
    val response = HttpRequest.get(baseUrl, "/api/v1/teams", queryParams)

    val rosters = (parse(response) \ "teams").extract[List[Team]].map(team => team.roster)

    rosters.filter(roster => roster.isDefined)
      .flatMap(roster => roster.get.roster)
      .map(unparsed => Player(unparsed.person.id, unparsed.person.fullName,
        unparsed.jerseyNumber, unparsed.position.name))
  }

  def getYearByYearStats(playerId: Int): List[YearByYearStats] = {
    val queryParams = Map("stats" -> "yearByYear")
    val response = HttpRequest.get(baseUrl, s"/api/v1/people/$playerId/stats", queryParams)

    ((parse(response) \ "stats")(0) \ "splits").extract[List[YearByYearStats]]
  }
}
