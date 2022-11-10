package ice.chrisworks.naive.external.service.models

sealed case class Country(race: Race)
object Country {
  final case object Nigeria       extends Country(Race.African)
  final case object Netherlands   extends Country(Race.European)
  final case object Romania       extends Country(Race.European)
}
