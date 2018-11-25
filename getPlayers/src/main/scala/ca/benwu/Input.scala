package ca.benwu

import scala.beans.BeanProperty

case class Input(@BeanProperty var startYear: Int, @BeanProperty var endYear: Int) {
  def this() = this(2018, 2018)
}

