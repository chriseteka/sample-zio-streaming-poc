package ice.chrisworks.naive.external.service.implementations

import ice.chrisworks.naive.external.service.models.Family
import ice.chrisworks.naive.external.service.{AppRes, EntityId, FamilyService}

case class FamilyServiceImpl() extends FamilyService {
  override def create(entity: Family): AppRes[Family] = ???

  override def readOne(entityId: EntityId): AppRes[Family] = ???

  override def readAll(): AppRes[List[Family]] = ???

  override def update(entityId: EntityId, update: Family): AppRes[Family] = ???

  override def deleteOne(entityId: EntityId): AppRes[Boolean] = ???
}
