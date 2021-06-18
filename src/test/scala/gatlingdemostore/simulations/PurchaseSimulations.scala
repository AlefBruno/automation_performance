package gatlingdemostore.simulations

import gatlingdemostore.commons.Utils.{getParams, httpProtocol}
import gatlingdemostore.journeys.PurchaseJourney._
import io.gatling.core.Predef._
import scala.concurrent.duration._

class PurchaseSimulations extends Simulation {

  def userCount: Int = getParams("USERS", "1").toInt
  def rampDuration: Int = getParams("RAMP_DURATION", "30").toInt

  before {
    println(s"Running test with ${userCount} users")
    println(s"Ramping users over ${rampDuration} seconds")
  }

  after {
    println("Stress testing complete")
  }

  setUp(
    Scenarios.default
      .inject(rampUsers(userCount) during (rampDuration.seconds)).protocols(httpProtocol),
    Scenarios.highPurchase
      .inject(rampUsers(userCount) during (rampDuration.seconds)).protocols(httpProtocol)
  )
}
