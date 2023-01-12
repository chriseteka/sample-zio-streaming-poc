package ice.chrisworks.naive.external.service

import ice.chrisworks.naive.external.service.configs.DbConfig
import zio.sql.ConnectionPool
import zio.{Scope, ZIO, ZIOAppArgs, ZIOAppDefault}

import java.util.UUID

object NaiveExternalHttpService extends ZIOAppDefault {

  val readOneHuman = HumanService.readOne(UUID.fromString("93e561ee-64da-42d8-a501-cbbd1706b39f"))
  override def run: ZIO[Any with ZIOAppArgs with Scope, Any, Any] =
    readOneHuman
      .provide(
        HumanService.Layers.live,
        ConnectionPool.live,
        DbConfig.layer,
        DbConfig.connectionPoolConfig
      )
      .debug("Done: ")
      .exitCode
}
