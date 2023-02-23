package ice.chrisworks.naive.external.service.implementations

import ice.chrisworks.naive.external.service.AppException.{CustomException, NotFoundException}
import ice.chrisworks.naive.external.service.models.Family
import ice.chrisworks.naive.external.service.{AppRes, EntityId, FamilyService}
import zio.{Chunk, ZIO}

case class FamilyServiceImpl() extends FamilyService {
  import FamiliesSchema._
  override def create(entity: Family): AppRes[Family] = ???

  override def readOne(entityId: EntityId): AppRes[Option[Family]] = ???

  override def readAll(): AppRes[Chunk[Family]] = ???

  override def update(entityId: EntityId, data: Family): AppRes[Family] =
    execute(
      update(familiesTable)
        .set(familyName, data.familyName)
        .set(livesIn, data.livesIn.entityId.toString)
        .where(familyId === entityId))
      .mapBoth(_ => NotFoundException(entityId), _ => data)
      .provideLayer(SqlDriver.live)

  override def deleteOne(entityId: EntityId): AppRes[Boolean] =
    execute(deleteFrom(familiesTable).where(familyId === entityId.toString))
      .mapBoth(_ => NotFoundException(entityId), deleted => deleted == 1)
      .provideLayer(SqlDriver.live)
}
