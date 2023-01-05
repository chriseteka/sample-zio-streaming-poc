import sbt._

object Dependencies {

  val ZIOVersion               = "2.0.5"
  val ZIOJSONVersion           = "0.4.2"
  val ZIOSQLVersion            = "0.1.1"
  val ZIOQuillVersion          = "4.6.0"
  val ZIOConfigVersion         = "3.0.6"
  val CalibanZIOHttpVersion    = "2.0.1"
  val PostGreSQLVersion        = "42.5.0"
  val `zio-test-framework`     = new TestFramework("zio.test.sbt.ZTestFramework")

  case object dev {
    //ZIO Dependencies
    case object zio {
      val `zio-core`    =   "dev.zio"    %% "zio"                 % ZIOVersion
      val `zio-stream`  =   "dev.zio"    %% "zio-streams"         % ZIOVersion
      val `zio-test`    =   "dev.zio"    %% "zio-test"            % ZIOVersion % Test
      val `zio-json`    =   "dev.zio"    %% "zio-json"            % ZIOJSONVersion
      val `zio-sql`     =   "dev.zio"    %% "zio-sql-postgres"    % ZIOSQLVersion
      val `zio-config`  =   "dev.zio"    %% "zio-config"          % ZIOConfigVersion

      val `zio-config-typesafe`  =   "dev.zio"    %% "zio-config-typesafe"          % ZIOConfigVersion
      val `zio-config-magnolia`  =   "dev.zio"    %% "zio-config-magnolia"          % ZIOConfigVersion
    }
  }

  case object com {
    case object github {
      case object ghostdogpr {
        val `caliban-zio-http` = "com.github.ghostdogpr"  %% "caliban-zio-http"  % CalibanZIOHttpVersion
      }
    }
  }

  case object org {
    case object postgresql {
      val postgresql = "org.postgresql" % "postgresql" % PostGreSQLVersion
    }
  }
}
