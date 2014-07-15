akka-inject
===========

Support for Dependency Injection in Akka 

This small library exposes convenient hookups to register DI framworks within ActorSystem 
and provides _Props_ implementation that allows to construct Actor objects through DI containter.

### DI Framework hookup
`DiExtension(system).setInjector(new SpringInjector(context))`

or a shorhand

`SpringSupport.register(system, context)`

### Creating actor e.g by bean name (implicit ActorContext required) 
`context.actorOf(InjectProps(beanName), name = actorName)`

Examples of using _akka-inject_ with Spring are provided as project tests

