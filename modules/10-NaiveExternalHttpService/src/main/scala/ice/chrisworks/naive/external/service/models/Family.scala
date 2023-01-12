package ice.chrisworks.naive.external.service.models

import ice.chrisworks.naive.external.service.EntityId

case class Family(entityId: EntityId,
                  familyName: String,
                  parents: Set[Human],
                  children: Set[Human],
                  livesIn: Community) {
  val familyMembers: Set[Human] = parents ++ children
}

object Family {
//  implicit val decoder: JsonDecoder[Family] = DeriveJsonDecoder.gen[Family]
}
