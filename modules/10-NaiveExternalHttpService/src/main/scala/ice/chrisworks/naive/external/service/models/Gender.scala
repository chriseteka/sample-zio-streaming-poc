package ice.chrisworks.naive.external.service.models

sealed trait Gender
object Gender {
  final case object Male    extends Gender
  final case object Female  extends Gender
}
