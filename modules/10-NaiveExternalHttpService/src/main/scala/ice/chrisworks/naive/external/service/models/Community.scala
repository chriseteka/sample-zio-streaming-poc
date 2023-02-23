package ice.chrisworks.naive.external.service.models

import ice.chrisworks.naive.external.service.EntityId
import zio.json.{DeriveJsonEncoder, JsonEncoder}

import java.util.UUID

case class Community(entityId: EntityId,
                     name: String,
                     country: Country,
                     bornIn: Set[Human],
                     families: Set[Family]) {

  val numberOfFamily: Int = families.size
  val numberOfHumanInCommunity: Int = families.flatMap(_.familyMembers).size
}

object Community {
  implicit val encoder: JsonEncoder[Community] = DeriveJsonEncoder.gen[Community]

  val empty: Community = Community(UUID.randomUUID(), "", Country.Nigeria, Set.empty, Set.empty)
}