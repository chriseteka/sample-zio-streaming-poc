package ice.chrisworks.naive.external.service.models

import zio.json.JsonEncoder

object Gender extends Enumeration {
  type Gender = Value
  val MALE, FEMALE, Unknown = Value

  def withNameOpt(s: String): Option[Value] = values.find(_.toString == s)

  def withNameWithDefault(name: String): Value =
    values.find(_.toString.toLowerCase == name.toLowerCase()).getOrElse(Unknown)

  implicit val genderEncoder: JsonEncoder[Gender] = JsonEncoder[String].contramap(_.toString)
}
