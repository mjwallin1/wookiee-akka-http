package com.webtrends.harness.component.akkahttp.verbs

import akka.http.scaladsl.server.Directives.{path => p, _}
import akka.http.scaladsl.server._
import com.webtrends.harness.command.{CommandBean, BaseCommand}
import com.webtrends.harness.component.akkahttp.AkkaHttpBase

trait AkkaHttpGet extends AkkaHttpBase {
  this: BaseCommand =>
  override protected def commandInnerDirective[T <: AnyRef : Manifest](bean: CommandBean): Route = get {
    super.commandInnerDirective(bean)
  }
}

