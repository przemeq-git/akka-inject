package akka.support.inject

import akka.actor.Actor

/**
 * @author Przemyslaw Dadel
 */
trait Injection {
  self: Actor =>

  def SpringProps(beanName: String) = DiExtension(context.system).createProps(beanName)

}
