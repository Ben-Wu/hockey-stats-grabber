package ca.benwu.storage

import awscala._
import dynamodbv2._
import ca.benwu.model.{Player, StatYear}

object StorageApi {

  implicit lazy val dynamoDB = DynamoDB.at(Region.US_EAST_1)

  lazy val playerTable: Table = dynamoDB.table("hockey-stats-grabber-players").get

  lazy val statsTable: Table = dynamoDB.table("hockey-stats-grabber-playerStats").get

  /**
    * Save a player object to the database
    * Does not overwrite if player is already in the database
    *
    * @return true if written, false if player already existed in DB
    */
    def writePlayer(player: Player): Boolean = {
      if (playerTable.getItem(hashPK = player.id).isDefined) {
        false
      } else {
        playerTable.putItem(hashPK = player.id,
          "FullName" -> player.fullName,
          "JerseyNumber" -> player.jerseyNumber,
          "Position" -> player.position)
        true
      }
    }

  /**
    * Save a stat year to the database
    * Will overwrite any stat year belonging to the same player in the same season
    */
  def writeStatYear(statYear: StatYear): Unit = {
    val attributes = Map(
      "Games" -> Some(statYear.games),
      "Goals" -> Some(statYear.goals),
      "Assists" -> Some(statYear.assists),
      "Points" -> Some(statYear.points),
      "PIM" -> statYear.pim,
      "PlusMinus" -> statYear.plusMinus,
      "Team" -> Some(statYear.team),
      "League" -> Some(statYear.league),
    ).filter{ case(_, v) => v.isDefined }.map{ case(k, v) => (k, v.get) }.toList
    statsTable.putItem(hashPK = statYear.playerId, rangePK = statYear.season,
      attributes: _*)
  }
}
