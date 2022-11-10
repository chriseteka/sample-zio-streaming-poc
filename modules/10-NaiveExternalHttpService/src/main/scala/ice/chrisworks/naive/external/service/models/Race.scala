package ice.chrisworks.naive.external.service.models

sealed trait Race
object Race {
  final case object African   extends Race
  final case object European  extends Race
}
