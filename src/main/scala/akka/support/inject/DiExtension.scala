package akka.support.inject

import akka.actor._

/**
 *
 */
class DiExtensionImpl extends Extension {

  @volatile
  private var injector: Injector = _

  def setInjector(injector: Injector) {
    this.injector = injector
  }

  def createProps(beanName: String) = Props(classOf[ByNameSpringActorProducer], injector, beanName)

  def createProps[T <: Actor](clazz: Class[T]) = Props(classOf[ByTypeSpringActorProducer[T]], injector, clazz)


}

object DiExtension extends ExtensionId[DiExtensionImpl] with ExtensionIdProvider {

  override def lookup(): ExtensionId[_ <: Extension] = DiExtension

  override def createExtension(system: ExtendedActorSystem): DiExtensionImpl = new DiExtensionImpl

}
