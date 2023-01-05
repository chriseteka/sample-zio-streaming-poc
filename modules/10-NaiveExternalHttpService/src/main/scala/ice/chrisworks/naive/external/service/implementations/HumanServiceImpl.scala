package ice.chrisworks.naive.external.service.implementations

import ice.chrisworks.naive.external.service.AppException.CustomException
import ice.chrisworks.naive.external.service.models.Human
import ice.chrisworks.naive.external.service.models.schema.DatabaseSchema
import ice.chrisworks.naive.external.service.{AppRes, EntityId, HumanService}
import zio.{ZIO, ZLayer}
import zio.sql.postgresql.PostgresJdbcModule
import zio.stream.ZStream

final case class HumanServiceImpl()
  extends HumanService with PostgresJdbcModule with DatabaseSchema {
  import HumanSchema._

  override def create(entity: Human): AppRes[Human] = ???

  //Not efficient, cause we get a list from filter, how do we turn it to an option of a single data
  override def readOne(id: EntityId): AppRes[Human] = ???

//  {
//    val query = select(entityId, name, age, gender)
//      .from(human)
//      .where(entityId === id)
//
////    ZIO.logInfo(s"Query to execute findOrderById is ${renderRead(query)}") *>
//    val res: ZStream[SqlDriver, Exception, Human] = execute[Human](query.to({
//      case (a, b, c, d) => Human(a, b, c, d, null)
//    }))
//
//    res
//  }

  override def readAll(): AppRes[List[Human]] = ???

//  {
//    val selectAll =
//      select(entityId, name, age, gender)
//        .from(human)
//
//    execute[Human](selectAll.to(({
//        case (a, b, c, d) => Human(a, b, c, d, null)
//      })))
//  }

  override def update(entityId: EntityId, update: Human): AppRes[Human] = ???

  override def deleteOne(entityId: EntityId): AppRes[Boolean] = ???

  override def specialReadAll() = {
      val query = select(entityId, name, age, gender).from(human)

      ZIO.logInfo(s"Query to execute findOrderById is ${renderRead(query)}")
      val res: ZStream[SqlDriver, Exception, Human] = execute[Human](query.to({
        case (a, b, c, d) => Human(a, b, c, d, null)
      }))

      res.runCollect.mapError(err => CustomException(err.getMessage)).provideLayer(SqlDriver.live)
    }
}
