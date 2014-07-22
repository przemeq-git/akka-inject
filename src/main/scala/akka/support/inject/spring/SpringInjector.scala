package akka.support.inject.spring

import akka.actor.Actor
import akka.support.inject.Injector
import org.springframework.context.ApplicationContext

/**
 *
 * @author Przemyslaw Dadel
 */
private[spring] class SpringInjector(private val context: ApplicationContext) extends Injector {

  override def getByType[T <: Actor](clazz: Class[T]): T = context.getBean(clazz)

  override def getTypeByName(name: String): Class[_ <: Actor] = context.getType(name).asInstanceOf[Class[Actor]]

  override def getByName(name: String): Actor = context.getBean(name, classOf[Actor])

}
