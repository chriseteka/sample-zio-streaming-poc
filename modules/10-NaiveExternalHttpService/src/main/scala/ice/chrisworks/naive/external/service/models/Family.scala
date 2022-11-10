package ice.chrisworks.naive.external.service.models

case class Family(familyName: String, parents: Set[Human], children: Set[Human]) {
  val familyMembers: Set[Human] = parents ++ children
}
