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

  val country: Country            = bornIn.country
  val race: Race                  = Race.withNameWithDefault(country.race)
  val adulthoodStatus: Adulthood  = if (age < 16) Adulthood.Minor else Adulthood.Adult

//  implicit val humanSchema: Schema[Human] = DeriveSchema.gen[Human]

  def withCommunity(bornIn: Community): Human = this.copy(bornIn = bornIn)
}

object Human {
  implicit val encoder: JsonEncoder[Human] = DeriveJsonEncoder.gen[Human]

}
