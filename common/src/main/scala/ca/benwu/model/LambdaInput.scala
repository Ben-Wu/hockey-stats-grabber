package ca.benwu.model

import scala.beans.BeanProperty

class LambdaInput(@BeanProperty var input: String) {
  def this() = this("")
}
