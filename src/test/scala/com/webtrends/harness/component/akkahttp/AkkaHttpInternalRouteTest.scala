package com.webtrends.harness.component.akkahttp

import akka.http.scaladsl.testkit.ScalatestRouteTest
import com.webtrends.harness.command.{BaseCommandResponse, CommandBean}
import com.webtrends.harness.component.akkahttp.directives.AkkaHttpInternal
import com.webtrends.harness.component.akkahttp.methods.AkkaHttpGet
import com.webtrends.harness.component.akkahttp.routes.{ExternalAkkaHttpRouteContainer, InternalAkkaHttpRouteContainer}
import com.webtrends.harness.component.akkahttp.util.TestBaseCommand
import org.scalatest.prop.PropertyChecks
import org.scalatest.{FunSuite, MustMatchers}

import scala.concurrent.Future

class AkkaHttpInternalRouteTest extends FunSuite
  with PropertyChecks
  with MustMatchers
  with ScalatestRouteTest {

  test("Internal routes should not appear in external http server") {
    val extRoutes = ExternalAkkaHttpRouteContainer.getRoutes
    new AkkaHttpGet with AkkaHttpInternal with TestBaseCommand {
      override def path: String = "test"

      override def execute[T: Manifest](bean: Option[CommandBean]): Future[BaseCommandResponse[T]] = {
        Future.successful(AkkaHttpCommandResponse(None))
      }
    }

    ExternalAkkaHttpRouteContainer.getRoutes.size mustEqual extRoutes.size
    InternalAkkaHttpRouteContainer.isEmpty mustEqual false
    InternalAkkaHttpRouteContainer.getRoutes.size mustEqual 1
  }
}
