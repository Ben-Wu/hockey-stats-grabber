package ca.benwu.model

import scala.beans.BeanProperty

class LambdaInput(@BeanProperty input: String) {
  def this() = this("")
}
