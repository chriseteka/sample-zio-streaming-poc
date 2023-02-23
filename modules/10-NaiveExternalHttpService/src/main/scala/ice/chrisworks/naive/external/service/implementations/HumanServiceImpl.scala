package ice.chrisworks.naive.external.service.implementations

import ice.chrisworks.naive.external.service.AppException.{CustomException, NotFoundException}
import ice.chrisworks.naive.external.service.models.Human
import ice.chrisworks.naive.external.service.{AppRes, EntityId, HumanService}
import zio.Console.printLine
import zio.stream.ZStream
import zio.{Chunk, ZIO}
final case class HumanServiceImpl() extends HumanService {
  import CommunitiesSchema._
  import HumanSchema._
  import ice.chrisworks.naive.external.service.models.schema.Tables._

  override def create(entity: Human): AppRes[Human] = {
    val query = insertInto(humanTable)(humanName, age, gender, bornIn)
      .values((entity.name, entity.age, entity.gender.toString, entity.bornIn.entityId.toString))

    val res = execute(query)
    ZIO.logInfo(s"Query to execute deleteOneHuman is ${println(renderInsert(query))}") *>
      res
        .mapBoth(exp => CustomException(exp.getMessage), _ => entity)
        .provideLayer(SqlDriver.live)
  }

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
      NotFoundException(id)
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
    res
      .tap(r => printLine(r))
      .runCollect.mapError(err => {
      err.printStackTrace()
      CustomException(err.getMessage)
    }).provideLayer(SqlDriver.live)
  }

  override def update(entityId: EntityId, data: Human): AppRes[Human] = {
    val query = update(humanTable).set(humanName, data.name)
      .set(age, data.age)
      .set(gender, data.gender.toString)
      .where(humanId === entityId)

    val res = execute(query)
    ZIO.logInfo(s"Query to execute updateHuman is ${println(renderUpdate(query))}") *>
      res
        .mapBoth(_ => NotFoundException(entityId), _ => data)
        .provideLayer(SqlDriver.live)

  }

  override def deleteOne(entityId: EntityId): AppRes[Boolean] = {
    val query = deleteFrom(humanTable).where(humanId === entityId.toString)
    val res = execute(query)

    ZIO.logInfo(s"Query to execute deleteOneHuman is ${println(renderDelete(query))}") *>
      res
        .mapBoth(_ => NotFoundException(entityId), deleted => deleted == 1)
        .provideLayer(SqlDriver.live)
  }
}
