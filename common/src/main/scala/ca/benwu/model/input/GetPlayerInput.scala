package ca.benwu.model.input

import scala.beans.BeanProperty

case class GetPlayerInput(@BeanProperty var startYear: Int, @BeanProperty var endYear: Int) {
  def this() = this(2018, 2018)
}
