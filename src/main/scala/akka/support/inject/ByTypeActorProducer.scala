package akka.support.inject

import akka.actor.{Actor, IndirectActorProducer}

/**
 * @author Przemyslaw Dadel
 */
private [inject] class ByTypeActorProducer[T <: Actor](injector: Injector, clazz: Class[T]) extends IndirectActorProducer {

  override def actorClass: Class[T] = clazz

  override def produce(): Actor = injector.getByType(clazz)

}
