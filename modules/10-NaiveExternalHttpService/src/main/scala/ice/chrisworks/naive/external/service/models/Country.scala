package ice.chrisworks.naive.external.service.models

sealed case class Country(race: Race)
object Country {
  final object Nigeria       extends Country(Race.African)
  final object Netherlands   extends Country(Race.European)
  final object Romania       extends Country(Race.European)
}
