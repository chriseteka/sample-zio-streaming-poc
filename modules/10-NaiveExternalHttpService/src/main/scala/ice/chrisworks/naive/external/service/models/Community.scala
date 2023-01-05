package ice.chrisworks.naive.external.service.models

case class Community(entityId: String,
                     name: String,
                     country: String,
//                     families: Set[Family]
                    ) {
//  val numberOfFamily: Int = families.size
//  val numberOfHumanInCommunity: Int = families.flatMap(_.familyMembers).size
}

object Community {
//  implicit val decoder: JsonDecoder[Community] = DeriveJsonDecoder.gen[Community]
}