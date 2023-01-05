package ice.chrisworks.naive.external.service

import ice.chrisworks.naive.external.service.implementations.HumanServiceImpl
import ice.chrisworks.naive.external.service.models.Human
import zio.sql.ConnectionPool
import zio.{Chunk, ZIO, ZLayer}

trait HumanService extends BaseCRUDService[Human] {
  //More Unimplemented Func here
  def specialReadAll(): ZIO[ConnectionPool, AppException, Chunk[Human]]
}

object HumanService extends BaseCRUDServiceAccessor[HumanService, Human] {

  object Layers {
    val live = ZLayer.succeed(HumanServiceImpl())
  }

  //Write Accessors for the Extra Func Here
  def specialReadAll() =
    ZIO.serviceWithZIO[HumanService](_.specialReadAll())
}
