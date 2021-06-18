package gatlingdemostore.simulations

import gatlingdemostore.commons.Utils.{getParams, httpProtocol}
import gatlingdemostore.pageobjects.{AboutUs, Catalog, Checkout}
import io.gatling.core.Predef._
import io.gatling.http.Predef.flushCookieJar
import scala.concurrent.duration._

class PurchaseBasic extends Simulation {
  def userCount: Int = getParams("USERS", "1").toInt
  def rampDuration: Int = getParams("RAMP_DURATION", "30").toInt

  val initSession = exec(flushCookieJar)
    .exec(session => session.set("customerLoggedIn", false))

  val scenarios = scenario("DemostoreSimulation")
    .exec(initSession)
    .exec(AboutUs.homepage)
    .pause(2)
    .exec(AboutUs.aboutUs)
    .pause(2)
    .exec(Catalog.Category.view)
    .pause(2)
    .exec(Catalog.Product.add)
    .pause(2)
    .exec(Checkout.viewCart)
    .pause(2)
    .exec(Checkout.completeCheckout)

  before {
    println(s"Running test with ${userCount} users")
    println(s"Ramping users over ${rampDuration} seconds")
  }

  after {
    println("Stress testing complete")
  }

  setUp(
    scenarios.inject(rampUsers(userCount) during (rampDuration.seconds)).protocols(httpProtocol)
  )
}
