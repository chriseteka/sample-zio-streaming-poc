package ice.chrisworks.naive.external.service.configs

import io.getquill.SnakeCase
import io.getquill.jdbczio.Quill

class AbstractQuillPGConfig(quill: Quill.Postgres[SnakeCase]) {
  import quill._

}
