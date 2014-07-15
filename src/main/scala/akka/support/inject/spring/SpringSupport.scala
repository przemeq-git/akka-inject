package akka.support.inject.spring

import akka.actor.ActorSystem
import akka.support.inject.DiExtension
import org.springframework.context.ApplicationContext

/**
 *
 * @author Przemyslaw Dadel
 */
object SpringSupport {

  def register(system: ActorSystem, context: ApplicationContext): ActorSystem = {
    DiExtension(system).setInjector(new SpringInjector(context))
    system
  }

}
