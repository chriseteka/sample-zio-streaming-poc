package ice.chrisworks.naive.external.service.implementations

import ice.chrisworks.naive.external.service.models.Community
import ice.chrisworks.naive.external.service.{AppRes, CommunityService, EntityId}

case class CommunityServiceImpl() extends CommunityService {
  override def create(entity: Community): AppRes[Community] = ???

  override def readOne(entityId: EntityId): AppRes[Community] = ???

  override def readAll(): AppRes[List[Community]] = ???

  override def update(entityId: EntityId, update: Community): AppRes[Community] = ???

  override def deleteOne(entityId: EntityId): AppRes[Boolean] = ???
}
