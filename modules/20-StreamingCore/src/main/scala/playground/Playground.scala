package playground

import zio._
import zio.stream.{ZPipeline, ZSink, ZStream}

object Playground extends ZIOAppDefault {

  //Sample (All in 1)
  val helloWorldStream: ZIO[Any, Nothing, Chunk[String]] = ZStream
    .repeatZIO(ZIO.succeed("Hello"))
    .map(_ + " World!")
    .take(1)
    .runCollect

  //Sample (broken in pieces)
  val source: ZStream[Any, Nothing, String] = ZStream.repeatZIO(ZIO.succeed("Hello"))
  val pipeline: ZPipeline[Any, Nothing, String, String] = ZPipeline.map[String, String](_ + " World!!")
  val sink: ZSink[Any, Nothing, String, String, Chunk[String]] = ZSink.collectAllN[String](1)
  val helloWorldStream2: ZIO[Any, Nothing, Chunk[String]] = source >>> pipeline >>> sink


  override def run: ZIO[Any with ZIOAppArgs with Scope, Any, Any] =
//    helloWorldStream2
    helloWorldStream
      .debug("Done")
}
