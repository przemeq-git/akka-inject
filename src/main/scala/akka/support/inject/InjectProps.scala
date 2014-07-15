package akka.support.inject

import akka.actor.{Actor, ActorContext, ActorSystem}

/**
 *
 * @author Przemyslaw Dadel
 */
object InjectProps {

  def apply(beanName: String, system: ActorSystem) = DiExtension(system).createProps(beanName)

  def apply[T <: Actor](clazz: Class[T], system: ActorSystem) = DiExtension(system).createProps(clazz)

  def apply(beanName: String)(implicit context: ActorContext) = DiExtension(context.system).createProps(beanName)

  def apply[T <: Actor](clazz: Class[T])(implicit context: ActorContext) = DiExtension(context.system).createProps(clazz)

}
