package ice.chrisworks.naive.external.service.models

case class Community(name: String, families: Set[Family]) {
  val numberOfFamily: Int = families.size
  val numberOfHumanInCommunity: Int = families.flatMap(_.familyMembers).size
}
