akka-inject
===========

Support for Dependency Injection in Akka 

This small library exposes convenient hookups to register DI framworks within ActorSystem 
and provides _Props_ implementation that allows to construct Actor objects through DI containter.

### Example
```scala
object Example {

  @Configuration
  class SpringConfiguration {

    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    @Bean(name = Array("byNameActor"))
    def fixedResponseActor() = new ByNameActor(response = "foo")
  }

  class ParentActor extends Actor {

    val child = context.actorOf(InjectProps("byNameActor"), name = "child")
    context.watch(child)

    def receive = {
      case msg: String => {
        child.forward(msg)
      }
      case Terminated(terminated) => {
        context.stop(self)
        context.system.shutdown()
      }
    }

  }

  def main(args: Array[String]) {

    val context = new AnnotationConfigApplicationContext(classOf[SpringConfiguration])
    val system = SpringSupport.register(ActorSystem("test"), context)

    val parent = system.actorOf(Props(classOf[ParentActor]), name = "parent")

    implicit val timeout = Timeout(5, TimeUnit.SECONDS)
    import scala.concurrent.ExecutionContext.Implicits.global

    val future = parent ? "test"

    future.onSuccess {
      case response => println(response)
    }

    system.awaitTermination(timeout.duration)
    context.close()
  }

}
```


### DI Framework hookup
`DiExtension(system).withInjector(new SpringInjector(context))`

or a shorthand

`SpringSupport.register(system, context)`

### Creating actor e.g by bean name (implicit ActorContext required) 
`context.actorOf(InjectProps(beanName), name = actorName)`

Further examples of using _akka-inject_ with Spring are provided as project tests

