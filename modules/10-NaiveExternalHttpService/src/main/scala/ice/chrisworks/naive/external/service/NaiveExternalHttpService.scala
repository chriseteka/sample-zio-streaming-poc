package ice.chrisworks.naive.external.service

import io.getquill.SnakeCase
import io.getquill.jdbczio.Quill
import zio.{Scope, ZIO, ZIOAppArgs, ZIOAppDefault}

object NaiveExternalHttpService extends ZIOAppDefault {

  val readAllHuman = HumanService.readAll()
  override def run: ZIO[Any with ZIOAppArgs with Scope, Any, Any] =
    readAllHuman
      .provide(
        HumanService.Layers.live,
        Quill.Postgres.fromNamingStrategy(SnakeCase),
        Quill.DataSource.fromPrefix("db.config")
      )
      .debug("Done: ")
      .exitCode
}
