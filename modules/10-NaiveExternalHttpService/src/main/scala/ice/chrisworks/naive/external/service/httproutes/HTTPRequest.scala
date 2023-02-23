package ice.chrisworks.naive.external.service.httproutes


import ice.chrisworks.naive.external.service
import ice.chrisworks.naive.external.service.HumanService
import ice.chrisworks.naive.external.service.configs.DbConfig
import ice.chrisworks.naive.external.service.models.Human
import zhttp.http._
import zhttp.service.Server
import zio._
import zio.json._
import zio.sql.ConnectionPool

object HTTPRequest extends ZIOAppDefault {

  val c: String = Chunk.empty[Human].toJson //without this, our app just tumbles

  val port = 9000
  val app: Http[HumanService with ConnectionPool, service.AppException, Request, Response] = Http.collectZIO[Request]{

    case Method.GET -> !! / "Humans" =>
      HumanService.readAll().map(d => Response.json(d.toJson))
  }

  val zApp = Http.collectZIO[Request]{
    case Method.GET -> !! /"HumansZ" => ZIO.succeed("hello humansZ").map(n => Response.text(n))
  }

  val combined = app ++ zApp

  val httpProgram: ZIO[HumanService with ConnectionPool, Throwable, Unit] = for {
    - <- Console.printLine(s"Starting server at http://localhost:$port")
    - <- Server.start(port, combined)
  } yield ()
  override def run: ZIO[Any, Throwable, Unit] = httpProgram.provide(
    HumanService.Layers.live,
    ConnectionPool.live,
    DbConfig.layer,
    DbConfig.connectionPoolConfig
  )
}
