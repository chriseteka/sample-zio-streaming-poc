package ice.chrisworks.naive.external.service.models

sealed case class Country(name: String, race: String)
object Country {
  final object Nigeria       extends Country("Nigeria", Race.African.toString)
  final object Netherlands   extends Country("Netherlands", Race.European.toString)
  final object Romania       extends Country("Romania", Race.European.toString)

//  implicit val decoder: JsonDecoder[Country] = DeriveJsonDecoder.gen[Country]
  val from: String => Country = _.toLowerCase match {
    case "nigeria"      => Nigeria
    case "netherlands"  => Netherlands
    case "romania"      => Romania
  }
}
