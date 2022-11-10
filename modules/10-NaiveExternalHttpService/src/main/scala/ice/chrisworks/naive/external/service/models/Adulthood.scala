package ice.chrisworks.naive.external.service.models

sealed trait Adulthood
object Adulthood {
  final case object Minor extends Adulthood
  final case object Adult extends Adulthood
}
