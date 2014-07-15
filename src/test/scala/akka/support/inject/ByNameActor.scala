package akka.support.inject

import akka.actor.Actor

/**
 *
 * @author Przemyslaw Dadel
 */
class ByNameActor(val response: String) extends Actor {
  override def receive = {
    case msg: String => {
      sender() ! response
      context.stop(self)
    }
  }
}
