package gatlingdemostore.journeys

import gatlingdemostore.pageobjects.{AboutUs, Catalog, Checkout}
import io.gatling.core.Predef._
import io.gatling.http.Predef.flushCookieJar
import scala.concurrent.duration._


object PurchaseJourney {
  val initSession = exec(flushCookieJar)
    .exec(session => session.set("customerLoggedIn", false))

  def minPause = 100.milliseconds

  def maxPause = 500.milliseconds

  def browseStore = {
    exec(initSession)
      .exec(AboutUs.homepage)
      .pause(maxPause)
      .exec(AboutUs.aboutUs)
      .pause(minPause, maxPause)
      .repeat(5) {
        exec(Catalog.Category.view)
          .pause(minPause, maxPause)
          .exec(Catalog.Product.view)
      }
  }

  def abandonCart = {
    exec(initSession)
      .exec(AboutUs.homepage)
      .pause(maxPause)
      .exec(Catalog.Category.view)
      .pause(minPause, maxPause)
      .exec(Catalog.Product.view)
      .pause(minPause, maxPause)
      .exec(Catalog.Product.add)
  }

  def completePurchase = {
    exec(initSession)
      .exec(AboutUs.homepage)
      .pause(maxPause)
      .exec(Catalog.Category.view)
      .pause(minPause, maxPause)
      .exec(Catalog.Product.view)
      .pause(minPause, maxPause)
      .exec(Catalog.Product.add)
      .pause(minPause, maxPause)
      .exec(Checkout.viewCart)
      .pause(minPause, maxPause)
      .exec(Checkout.completeCheckout)
  }

  object Scenarios {
    def default = scenario("Default Load Test")
      .randomSwitch(
        75d -> exec(browseStore),
        15d -> exec(abandonCart),
        10d -> exec(completePurchase)
      )

    def highPurchase = scenario("High Purhcase Load Test")
      .randomSwitch(
        25d -> exec(browseStore),
        25d -> exec(abandonCart),
        50d -> exec(completePurchase)
      )
  }
}
