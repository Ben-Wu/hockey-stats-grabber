package ca.benwu.model.input

import collection.JavaConverters._
import scala.beans.BeanProperty
import scala.collection.mutable

case class GetPlayerStatsInput(@BeanProperty var playerIds: java.util.List[Int], @BeanProperty var year: Int,
                               @BeanProperty var endYear: Int, @BeanProperty var done: Boolean) {
  def this() = this(mutable.Buffer[Int]().asJava, 2018, 2018, false)
}
