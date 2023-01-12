package ice.chrisworks.naive.external.service.implementations

import ice.chrisworks.naive.external.service.models.schema.{DatabaseSchema, Tables}
import zio.sql.postgresql.PostgresJdbcModule
import ice.chrisworks.naive.external.service.models.Human
import zio.ZIO

object QueryDisplayer extends App with PostgresJdbcModule with DatabaseSchema {
  import this.AggregationDef._
  import this.FunctionDef._
  import HumanSchema._

  val allHumanQuery = select(humanId, humanName, age, gender).from(humanTable)

  ZIO.logInfo(s"Query to execute findOrderById is ${println(renderRead(allHumanQuery))}") *>
    ZIO.from(execute[Human](allHumanQuery.to( {
      case (a, b, c, d) => Tables.human(a, b, c, d, "niave").toDomainObject
    })))
}
