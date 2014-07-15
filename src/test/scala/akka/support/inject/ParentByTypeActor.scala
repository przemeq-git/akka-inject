package akka.support.inject

import akka.actor.Actor

/**
 *
 * @author Przemyslaw Dadel
 */
class ParentByTypeActor(val childName: String, childClass: Class[_ <: Actor]) extends Actor {

  val child = context.actorOf(InjectProps(childClass), name = childName)

  override def receive = {
    case msg: String => child.forward(msg)
  }
}
