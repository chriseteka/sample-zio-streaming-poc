package ice.chrisworks.naive.external.service.models

sealed case class Gender()
object Gender {
  final object Male    extends Gender
  final object Female  extends Gender
}
