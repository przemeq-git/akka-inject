package akka.support.inject

import akka.actor.{Actor, IndirectActorProducer}

/**
 * @author Przemyslaw Dadel
 */
private [inject] class ByNameActorProducer(injector: Injector, beanName: String) extends IndirectActorProducer {

  override def actorClass: Class[_ <: Actor] = injector.getTypeByName(beanName)

  override def produce(): Actor = injector.getByName(beanName)

}
