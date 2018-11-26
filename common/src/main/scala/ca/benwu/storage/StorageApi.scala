package ca.benwu.storage

import awscala._
import dynamodbv2._

import ca.benwu.model.Player

object StorageApi {

  implicit lazy val dynamoDB = DynamoDB.at(Region.US_EAST_1)

  lazy val playerTable: Table = dynamoDB.table("hockey-stats-grabber-players").get

  lazy val statsTable: Table = dynamoDB.table("hockey-stats-grabber-playerStats").get

  /**
    * Save a player object to the database.  Does not overwrite if already existing
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
}
