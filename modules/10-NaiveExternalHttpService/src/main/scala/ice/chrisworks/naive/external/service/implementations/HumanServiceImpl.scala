package ice.chrisworks.naive.external.service.implementations

import ice.chrisworks.naive.external.service.AppException.CustomException
import ice.chrisworks.naive.external.service.models.Human
import ice.chrisworks.naive.external.service.{AppRes, EntityId, HumanService}
import io.getquill.SnakeCase
import io.getquill.jdbczio.Quill

case class HumanServiceImpl(quill: Quill.Postgres[SnakeCase]) extends HumanService {
  import quill._

  override def create(entity: Human): AppRes[Human] = ???

  //Not efficient
  override def readOne(entityId: EntityId): AppRes[Human] =
    run(query[Human].filter(_.name == lift(entityId.toString))) mapBoth (
      err => CustomException(s"Read one human failed with reason: ${err.getMessage}"), _.head
    )

  override def readAll(): AppRes[List[Human]] =
    run(query[Human]) mapError { err =>
      CustomException(s"Read all human failed with reason: ${err.getMessage}")
    }

  override def update(entityId: EntityId, update: Human): AppRes[Human] = ???

  override def deleteOne(entityId: EntityId): AppRes[Boolean] = ???
}
