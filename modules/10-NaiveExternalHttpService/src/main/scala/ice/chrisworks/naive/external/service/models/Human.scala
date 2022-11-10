package ice.chrisworks.naive.external.service.models

case class Human(name: String, age: Int, gender: Gender, country: Country) {
  val race: Race                  = country.race
  val adulthoodStatus: Adulthood  = if (age < 16) Adulthood.Minor else Adulthood.Adult
}
