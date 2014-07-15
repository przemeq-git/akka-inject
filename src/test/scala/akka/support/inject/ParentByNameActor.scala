package akka.support.inject

import akka.actor.Actor

/**
 *
 * @author Przemyslaw Dadel
 */
class ParentByNameActor(val childName: String, beanName: String) extends Actor {

  val child = context.actorOf(InjectProps(beanName), name = childName)

  override def receive = {
    case msg: String => child.forward(msg)
  }
}
