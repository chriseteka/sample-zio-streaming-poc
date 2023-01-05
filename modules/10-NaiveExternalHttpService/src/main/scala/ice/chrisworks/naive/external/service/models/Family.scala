package ice.chrisworks.naive.external.service.models

case class Family(entityId: String,
                  familyName: String,
//                  parents: Set[Human],
//                  children: Set[Human]
                 ) {
//  val familyMembers: Set[Human] = parents ++ children
}

object Family {
//  implicit val decoder: JsonDecoder[Family] = DeriveJsonDecoder.gen[Family]
}
