package akka.support.inject

import akka.actor.Actor

/**
 *
 * @author Przemyslaw Dadel
 */
trait Injector {

  def getByType[T <: Actor](clazz: Class[T]): T

  def getByName(name: String): Actor

  def getTypeByName(name: String): Class[_ <: Actor]

}
