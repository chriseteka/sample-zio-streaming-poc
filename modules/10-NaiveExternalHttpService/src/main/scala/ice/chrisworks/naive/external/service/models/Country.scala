package ice.chrisworks.naive.external.service.models

sealed case class Country(race: String)
object Country {
  final object Nigeria       extends Country(Race.African.toString)
  final object Netherlands   extends Country(Race.European.toString)
  final object Romania       extends Country(Race.European.toString)

//  implicit val decoder: JsonDecoder[Country] = DeriveJsonDecoder.gen[Country]
}
