package ice.chrisworks.naive.external.service.models

import ice.chrisworks.naive.external.service.EntityId
import ice.chrisworks.naive.external.service.models.Gender.Gender
import ice.chrisworks.naive.external.service.models.Race.Race
import zio.json.{DeriveJsonDecoder, DeriveJsonEncoder, JsonDecoder, JsonEncoder}

case class Human(entityId: EntityId,
                 name: String,
                 age: Int,
                 gender: Gender,
                 bornIn: Community) {

  require(name.nonEmpty && age > 0 && gender.toString.nonEmpty && bornIn.entityId.toString.nonEmpty) //Can throw NPE

  val country: Country            = bornIn.country
  val race: Race                  = Race.withNameWithDefault(country.race)
  val adulthoodStatus: Adulthood  = if (age < 16) Adulthood.Minor else Adulthood.Adult

  def withCommunity(bornIn: Community): Human = this.copy(bornIn = bornIn)
}

object Human {
  implicit val encoder: JsonEncoder[Human] = DeriveJsonEncoder.gen[Human]
  implicit val decoder: JsonDecoder[Human] = DeriveJsonDecoder.gen[Human]

}
