package akka.support.inject

import java.util.concurrent.TimeUnit

import akka.util.Timeout

/**
 *
 * @author Przemyslaw Dadel
 */
object ActorTestFixture {

  implicit val defaultTimeout = Timeout(5, TimeUnit.SECONDS)
  implicit val defaultDuration = defaultTimeout.duration

}
