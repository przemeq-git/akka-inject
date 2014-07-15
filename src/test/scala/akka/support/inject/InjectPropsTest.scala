package akka.support.inject

import akka.actor.{ActorSystem, Props}
import akka.pattern.ask
import akka.support.inject.ActorTestFixture.defaultTimeout
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

import scala.concurrent.{Await, Future}
import scala.concurrent.duration.FiniteDuration

/**
 * @author Przemyslaw Dadel
 */
@RunWith(classOf[SpringJUnit4ClassRunner])
@ContextConfiguration(Array("/actorCreateContext.xml"))
class InjectPropsTest {

  private var system: ActorSystem = _

  @Test
  def shouldCreateActorByNameFromSystem() {
    //when
    val actor = system.actorOf(InjectProps("byNameActor", system));

    val future = actor ? "test"
    assertResponse("byNameActor", future, defaultTimeout.duration)
  }

  @Test
  def shouldCreateActorByNameFromContext() {
    //given
    val actor = system.actorOf(Props(classOf[ParentByNameActor], "name", "byNameActor"))

    //when
    val future = actor ? "test"

    //then
    assertResponse("byNameActor", future, defaultTimeout.duration)
  }

  @Test
  def shouldCreateActorByTypeFromSystem() {
    //when
    val actor = system.actorOf(InjectProps(classOf[ByTypeActor], system));

    val future = actor ? "test"
    assertResponse("byTypeActor", future, defaultTimeout.duration)
  }

  @Test
  def shouldCreateActorByTypeFromContext() {
    //when
    val actor = system.actorOf(Props(classOf[ParentByTypeActor], "name", classOf[ByTypeActor]))

    val future = actor ? "test"
    assertResponse("byTypeActor", future, defaultTimeout.duration)
  }


  def assertResponse(expectedResponse: Any, future: Future[Any], duration: FiniteDuration) {
    val result = Await.result(future, duration);
    assertEquals(expectedResponse, result)
  }

  @Autowired
  def setActorSystem(system: ActorSystem) {
    this.system = system
  }
}