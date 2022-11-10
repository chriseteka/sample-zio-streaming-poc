package ice.chrisworks.naive.external

import zio.{IO, Tag, ZIO}

import java.util.UUID

package object service {

  type EntityId = UUID

  sealed abstract class AppException(message: String)
  object AppException {
    final case class NotFoundException(entity: EntityId)  extends AppException(s"$entity not found")
    final case class CustomException(msg: String)         extends AppException(msg)
  }

  type AppRes[AppEntity] = IO[AppException, AppEntity]

  //Since it is shared, we can extract it here
  trait BaseCRUDService[AppEntity] {

    def create(entity: AppEntity): AppRes[AppEntity]

    def readOne(entityId: EntityId): AppRes[AppEntity]

    def readAll(): AppRes[List[AppEntity]]

    def update(entityId: EntityId, update: AppEntity): AppRes[AppEntity]

    def deleteOne(entityId: EntityId): AppRes[Boolean]
  }

  //Hence, we can do
  class BaseCRUDServiceAccessor[Service, AppEntity](implicit evi: Service <:< BaseCRUDService[AppEntity], tag: Tag[Service]) {

    def create(entity: AppEntity): ZIO[Service, AppException, AppEntity] =
      ZIO.serviceWithZIO[Service](_.create(entity))

    def readOne(entityId: EntityId): ZIO[Service, AppException, AppEntity] =
      ZIO.serviceWithZIO[Service](_.readOne(entityId))

    def readAll(): ZIO[Service, AppException, List[AppEntity]] =
      ZIO.serviceWithZIO[Service](_.readAll())

    def update(entityId: EntityId, update: AppEntity): ZIO[Service, AppException, AppEntity] =
      ZIO.serviceWithZIO[Service](_.update(entityId, update))

    def deleteOne(entityId: EntityId): ZIO[Service, AppException, Boolean] =
      ZIO.serviceWithZIO[Service](_.deleteOne(entityId))
  }

}
