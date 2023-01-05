package ice.chrisworks.naive.external.service.models

import zio.json.{DeriveJsonDecoder, JsonDecoder}

sealed class Adulthood
object Adulthood {
  final case object Minor extends Adulthood
  final case object Adult extends Adulthood

  implicit val decoder: JsonDecoder[Adulthood] = DeriveJsonDecoder.gen[Adulthood]
}
