package akka.support.inject

import akka.actor.ActorSystem
import org.junit.{Assert, Test}
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

/**
 * @author Przemyslaw Dadel
 */
@RunWith(classOf[SpringJUnit4ClassRunner])
@ContextConfiguration(Array("/systemCreateContext.xml"))
class SpringActorSystemProviderTest {

  @Autowired
  private var system: ActorSystem = _

  @Test
  def shouldActorSystemBeCreatedWithName() {
    val name: String = system.name
    Assert.assertEquals("testName", name)
  }

}