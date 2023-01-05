package ice.chrisworks.naive.external.service.implementations

import ice.chrisworks.naive.external.service.models.schema.DatabaseSchema
import zio.sql.postgresql.PostgresJdbcModule
import ice.chrisworks.naive.external.service.models.{Gender, Human => Individual}
import zio.ZIO

object QueryDisplayer extends App with PostgresJdbcModule with DatabaseSchema {
  import this.AggregationDef._
  import this.FunctionDef._
  import HumanSchema._

  val allHumanQuery = select(entityId, name, age, gender).from(human)

  ZIO.logInfo(s"Query to execute findOrderById is ${renderRead(allHumanQuery)}") *>
    ZIO.from(execute[Individual](allHumanQuery.to( {
      case (a, b, c, d) => Individual(a, b, c, d, null)
    })))
}
