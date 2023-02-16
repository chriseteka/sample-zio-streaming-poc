package ice.chrisworks.naive.external.service.implementations

import ice.chrisworks.naive.external.service.AppException.CustomException
import ice.chrisworks.naive.external.service.models.Human
import ice.chrisworks.naive.external.service.models.schema.DatabaseSchema
import ice.chrisworks.naive.external.service.{AppRes, EntityId, HumanService}
import zio.sql.postgresql.PostgresJdbcModule
import zio.stream.{ZSink, ZStream}
import zio.{Chunk, ZIO}
import zio.sql._
final case class HumanServiceImpl()
  extends HumanService with PostgresJdbcModule with DatabaseSchema {
  import CommunitiesSchema._
  import HumanSchema._
  import ice.chrisworks.naive.external.service.models.schema.Tables._

  override def create(entity: Human): AppRes[Human] = ???

  //Not efficient, cause we get a list from filter, how do we turn it to an option of a single data
  override def readOne(id: EntityId): AppRes[Option[Human]] = {
    val query =
      select(
        humanId,
        humanName,
        age,
        gender,
        communityId,
        communityName,
        country
      ).from(humanTable.leftOuter(communitiesTable).on(bornIn === communityId))
      .where(humanId === id.toString)

    val res: ZStream[SqlDriver, Exception, Human] = execute[Human](query.to({
      //todo: of course we can call an apply function and give all the args to it
      case (a, b, c, d, e, f, g) =>
        human(a, b, c, d, e)
          .toDomainObject
          .withCommunity(communities(e, f, g)
            .toDomainObject
          )
    }))

    ZIO.logInfo(s"Query to execute fetchAllHuman is ${println(renderRead(query))}") *>
    res.runHead.mapError(err => {
      err.printStackTrace()
      CustomException(err.getMessage)
    }).provideLayer(SqlDriver.live)
  }

  override def readAll(): AppRes[Chunk[Human]] = {
    val query =
      select(
        humanId,
        humanName,
        age,
        gender,
        communityId,
        communityName,
        country
      ).from(humanTable.leftOuter(communitiesTable)
        .on(bornIn === communityId))

    val res: ZStream[SqlDriver, Exception, Human] = execute[Human](query.to({
      //todo: of course we can call an apply function and give all the args to it
      case (a, b, c, d, e, f, g) =>
        human(a, b, c, d, e)
          .toDomainObject
          .withCommunity(communities(e, f, g)
            .toDomainObject
          )
    }))

    ZIO.logInfo(s"Query to execute fetchAllHuman is ${println(renderRead(query))}") *>
    res.runCollect.mapError(err => {
      err.printStackTrace()
      CustomException(err.getMessage)
    }).provideLayer(SqlDriver.live)
  }

  override def update(entityId: EntityId, update: Human): AppRes[Human] = ???

  override def deleteOne(entityId: EntityId): AppRes[Boolean] = ???
}
