package ice.chrisworks.naive.external.service.models

import zio.schema.{DeriveSchema, Schema}

case class Human(entityId: String,
                 name: String,
                 age: Int,
                 gender: String,
                 community: String) {
//  val country: Country            = community.country
//  val race: Race                  = Race.withNameWithDefault(country.race)
  val adulthoodStatus: Adulthood  = if (age < 16) Adulthood.Minor else Adulthood.Adult

  implicit val humanSchema: Schema[Human] = DeriveSchema.gen[Human]
}

object Human {
//  implicit val decoder: JsonDecoder[Human] = DeriveJsonDecoder.gen[Human]
}
