package gatlingdemostore.commons

import java.io.File
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder
import com.typesafe.config.{Config, ConfigFactory}
import scala.util.Random

object Utils {

  val environment: String = getParams("ENV", "hml")
  val config: Config = ConfigFactory.parseFile(new File(s"src/test/resources/configs/$environment.conf"))
  val httpProtocol: HttpProtocolBuilder = http.baseUrl(config.getString("baseUrl"))
  val rnd = new Random()

  def getParams(propertyName: String, defaultValue: String): String = {
    Option(System.getenv(propertyName))
      .orElse(Option(System.getProperty(propertyName)))
      .getOrElse(defaultValue)
  }

	def randomString(length: Int): String = {
		rnd.alphanumeric.filter(_.isLetter).take(length).mkString
	}
}
