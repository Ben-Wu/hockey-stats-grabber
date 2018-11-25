package ca.benwu

import scala.beans.BeanProperty

case class Input(@BeanProperty startYear: Int, @BeanProperty endYear: Int) {
  def this() = this(2018, 2018)
}

