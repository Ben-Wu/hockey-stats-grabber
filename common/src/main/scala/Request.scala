import scala.beans.BeanProperty

class Request(@BeanProperty input: String) {
  def this() = this("")
}
