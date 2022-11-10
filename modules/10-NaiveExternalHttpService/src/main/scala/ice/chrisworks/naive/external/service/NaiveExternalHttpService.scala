package ice.chrisworks.naive.external.service

import zio.{Scope, ZIO, ZIOAppArgs, ZIOAppDefault}

object NaiveExternalHttpService extends ZIOAppDefault{
  override def run: ZIO[Any with ZIOAppArgs with Scope, Any, Any] = ???
}
