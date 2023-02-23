package ice.chrisworks.naive.external

import ice.chrisworks.naive.external.service.models.schema.DatabaseSchema
import zio.sql.ConnectionPool
import zio.sql.postgresql.PostgresJdbcModule
import zio.{Chunk, Tag, ZIO}

import java.util.UUID

package object service {

  type EntityId = UUID

  sealed abstract class AppException(message: String) extends Throwable
  object AppException {
    final case class NotFoundException(entity: EntityId)  extends AppException(s"$entity not found")
    final case class CustomException(msg: String)         extends AppException(msg)
  }

  type AppRes[AppEntity] = ZIO[ConnectionPool, AppException, AppEntity]

  //Since it is shared, we can extract it here
  trait BaseCRUDService[AppEntity] extends PostgresJdbcModule with DatabaseSchema {

    def create(entity: AppEntity): AppRes[AppEntity]

    def readOne(entityId: EntityId): AppRes[Option[AppEntity]]

    def readAll(): AppRes[Chunk[AppEntity]]

    def update(entityId: EntityId, update: AppEntity): AppRes[AppEntity]

    def deleteOne(entityId: EntityId): AppRes[Boolean]
  }

  //Hence, we can do
  class BaseCRUDServiceAccessor[Service, AppEntity](implicit evi: Service <:< BaseCRUDService[AppEntity], tag: Tag[Service]) {

    def create(entity: AppEntity): ZIO[Service with ConnectionPool, AppException, AppEntity] =
      ZIO.serviceWithZIO[Service](_.create(entity))

    def readOne(entityId: EntityId): ZIO[Service with ConnectionPool, AppException, Option[AppEntity]] =
      ZIO.serviceWithZIO[Service](_.readOne(entityId))

    def readAll(): ZIO[Service with ConnectionPool, AppException, Chunk[AppEntity]] =
      ZIO.serviceWithZIO[Service](_.readAll())

    def update(entityId: EntityId, update: AppEntity): ZIO[Service with ConnectionPool, AppException, AppEntity] =
      ZIO.serviceWithZIO[Service](_.update(entityId, update))

    def deleteOne(entityId: EntityId): ZIO[Service with ConnectionPool, AppException, Boolean] =
      ZIO.serviceWithZIO[Service](_.deleteOne(entityId))
  }

}
