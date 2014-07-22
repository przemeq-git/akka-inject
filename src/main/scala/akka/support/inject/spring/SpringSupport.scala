package akka.support.inject.spring

import akka.actor.ActorSystem
import akka.support.inject.DiExtension
import org.springframework.context.ApplicationContext

/**
 *
 * @author Przemyslaw Dadel
 */
object SpringSupport {

  /**
   * Registers SpringInjector with DiExtension for the provided ActorSystem.
   * @param system
   * @param context
   * @return
   */
  def register(system: ActorSystem, context: ApplicationContext): ActorSystem = {
    val injector = new SpringInjector(context)
    DiExtension(system).withInjector(injector)
    system
  }

}
