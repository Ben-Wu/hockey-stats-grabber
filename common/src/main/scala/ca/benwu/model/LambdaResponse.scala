package ca.benwu.model

import scala.beans.BeanProperty

case class LambdaResponse(@BeanProperty message: String, @BeanProperty request: LambdaInput)
