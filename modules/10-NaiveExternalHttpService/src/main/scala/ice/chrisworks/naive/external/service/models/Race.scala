package ice.chrisworks.naive.external.service.models

sealed case class Race()
object Race {
  final object African   extends Race
  final object European  extends Race
}
