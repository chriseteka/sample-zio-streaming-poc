package ice.chrisworks.naive.external.service.models

object Race extends Enumeration {

  type Race = Value
  val African, European, Unknown = Value

  def withNameOpt(s: String): Option[Value] = values.find(_.toString == s)
  def withNameWithDefault(name: String): Value =
    values.find(_.toString.toLowerCase == name.toLowerCase()).getOrElse(Unknown)

}
