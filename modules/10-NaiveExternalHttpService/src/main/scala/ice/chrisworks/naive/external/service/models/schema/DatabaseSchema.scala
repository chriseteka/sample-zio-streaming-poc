package ice.chrisworks.naive.external.service.models.schema

import ice.chrisworks.naive.external.service.models.{Community, Family, Human}
import zio.schema.{DeriveSchema, Schema}
import zio.sql.postgresql.PostgresJdbcModule


trait DatabaseSchema extends PostgresJdbcModule {

  case class Children(humanId: String, childId: String)
  case class Parents(humanId: String, familyId: String)

  object Children {
    implicit val childrenSchema = DeriveSchema.gen[Children]
    val children = defineTable[Children]
    val (humanId, childId) = children.columns
  }


  object Community {
    implicit val communitySchema = DeriveSchema.gen[Community]
    val communities = defineTable[Community]
    val (entityId, name, country) = communities.columns
  }

  object Family {
    implicit val communitySchema = DeriveSchema.gen[Family]
    val families = defineTable[Family]
    val (entityId, familyNae) = families.columns
  }

  object HumanSchema {
    implicit val humanSchema = DeriveSchema.gen[Human]
    val human = defineTable[Human]
    val (entityId, name, age, gender, community) = human.columns
  }

  object Parent {
    implicit val parentsSchema = DeriveSchema.gen[Parents]
    val parents = defineTable[Parents]
    val (humanId, familyId) = parents.columns
  }
}
