package akka.support.inject.spring

import akka.actor.ActorSystem
import org.springframework.context.{ApplicationContext, ApplicationContextAware}

/**
 *
 * @author Przemyslaw Dadel
 */
class SpringActorSystemProvider extends ApplicationContextAware {

  var context: ApplicationContext = _

  override def setApplicationContext(applicationContext: ApplicationContext) {
    context = applicationContext
  }

  def createActorSystem(name: String): ActorSystem = {
    val system = ActorSystem(name)
    return SpringSupport.register(system, context)
  }

}


