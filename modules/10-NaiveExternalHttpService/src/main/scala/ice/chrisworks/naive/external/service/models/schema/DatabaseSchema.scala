package ice.chrisworks.naive.external.service.models.schema

import ice.chrisworks.naive.external.service.models.schema.Tables._
import zio.schema.DeriveSchema
import zio.sql.postgresql.PostgresJdbcModule

/**
 * Maps all the table objects to an actual database (allows for serializing and deserializing DB data)
 */
trait DatabaseSchema extends PostgresJdbcModule {

  object ChildrenSchema {
    implicit val childrenSchema = DeriveSchema.gen[children]
    val childrenTable = defineTable[children]
    val (humanId, familyId) = childrenTable.columns
  }


  object CommunitiesSchema {
    implicit val communitySchema = DeriveSchema.gen[communities]
    val communitiesTable = defineTable[communities]
    val (communityId, communityName, country) = communitiesTable.columns
  }

  object FamiliesSchema {
    implicit val communitySchema = DeriveSchema.gen[families]
    val familiesTable = defineTable[families]
    val (familyId, familyName, livesIn) = familiesTable.columns
  }

  object HumanSchema {
    implicit val humanSchema = DeriveSchema.gen[human]
    val humanTable = defineTable[human]
    val (humanId, humanName, age, gender, bornIn) = humanTable.columns
  }

  object ParentsSchema {
    implicit val parentsSchema = DeriveSchema.gen[parents]
    val parentsTable = defineTable[parents]
    val (humanId, familyId) = parentsTable.columns
  }
}
