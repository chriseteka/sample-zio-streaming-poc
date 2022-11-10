import sbt._

object Dependencies {

  val ZIOVersion               = "2.0.3"
  val ZIOQuillVersion          = "4.6.0"
  val CalibanZIOHttpVersion    = "2.0.1"
  val PostGreSQLVersion        = "42.5.0"
  val `zio-test-framework`     = new TestFramework("zio.test.sbt.ZTestFramework")

  case object dev {
    //ZIO Dependencies
    case object zio {
      val `zio-core`    =   "dev.zio"    %%  "zio"             % ZIOVersion
      val `zio-stream`  =   "dev.zio"    %% "zio-streams"      % ZIOVersion
      val `zio-test`    =   "dev.zio"    %%  "zio-test"        % ZIOVersion % Test
    }
  }

  case object com {
    case object github {
      case object ghostdogpr {
        val `caliban-zio-http` = "com.github.ghostdogpr"  %% "caliban-zio-http"  % CalibanZIOHttpVersion
      }
    }
  }

  case object ioi {
    case object getquill {
      val `quill-jdbc-zio` = "io.getquill" %% "quill-jdbc-zio" % ZIOQuillVersion
    }
  }

  case object org {
    case object postgresql {
      val postgresql = "org.postgresql" % "postgresql" % PostGreSQLVersion
    }
  }
}
