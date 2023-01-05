package ice.chrisworks.naive.external.service

import ice.chrisworks.naive.external.service.configs.DbConfig
import zio.sql.ConnectionPool
import zio.{Scope, ZIO, ZIOAppArgs, ZIOAppDefault}

object NaiveExternalHttpService extends ZIOAppDefault {

  val readAllHuman = HumanService.specialReadAll()
  override def run: ZIO[Any with ZIOAppArgs with Scope, Any, Any] =
    readAllHuman
      .provide(
        HumanService.Layers.live,
        ConnectionPool.live,
        DbConfig.layer,
        DbConfig.connectionPoolConfig
      )
      .debug("Done: ")
      .exitCode
}
