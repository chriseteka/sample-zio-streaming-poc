package ice.chrisworks.naive.external.service.models.schema

import ice.chrisworks.naive.external.service.models.{Community, Country, Family, Gender, Human}

import java.util.UUID

/**
 * These class maps directly to the database, they are low level, simple classes
 */
object Tables {

  sealed abstract class DBTable[Obj](pryKey: String) {
    val entityUUID: UUID = UUID.fromString(pryKey)
     def toDomainObject: Obj
  }

  final case class human(humanId: String, humanName: String, age: Int, gender: String, bornIn: String)
    extends DBTable[Human](humanId) {

    override def toDomainObject: Human = Human(entityUUID, humanName, age,
      Gender.withNameWithDefault(gender), Community.empty)
  }

  final case class communities(communityId: String, communityName: String, country: String)
    extends DBTable[Community](communityId) {

    override def toDomainObject: Community = Community(entityUUID, communityName, Country.from(country), Set.empty)
  }

  final case class families(familyId: String, familyName: String, livesIn: String)
    extends DBTable[Family](familyId) {

    override def toDomainObject: Family = Family(entityUUID, familyName, Set.empty, Set.empty, Community.empty)
  }

  final case class parents(parentId: String, familyId: String) //Join table
  final case class children(childId: String, familyId: String) //Join table

  /**
   * Maps the data from the DB-like classes to the actual domain objects that we want
   */
  object TablesToDomainObject {

  }
}
