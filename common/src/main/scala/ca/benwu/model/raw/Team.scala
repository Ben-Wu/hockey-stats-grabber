package ca.benwu.model.raw

case class Team(id: Int, name: String, teamName: String, locationName: String, abbreviation: String, roster: Option[Roster])
