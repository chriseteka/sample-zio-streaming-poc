package ice.chrisworks.naive.external.service.models

object Gender extends Enumeration {
  type Gender = Value
  val MALE, FEMALE, Unknown = Value

  def withNameOpt(s: String): Option[Value] = values.find(_.toString == s)

  def withNameWithDefault(name: String): Value =
    values.find(_.toString.toLowerCase == name.toLowerCase()).getOrElse(Unknown)
}
