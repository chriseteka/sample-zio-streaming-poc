import sbt._

object Dependencies {

  val ZIOVersion            = "2.0.3"
  val ZIOHttpVersion        = "2.0.1"
  val `zio-test-framework`  = new TestFramework("zio.test.sbt.ZTestFramework")

  //ZIO Dependencies
  case object dev {
    case object zio {
      val `zio-core`    =   "dev.zio"                 %%  "zio"                   % ZIOVersion
      val `zio-stream`  =   "dev.zio"                 %% "zio-streams"            % ZIOVersion
      val `zio-http`    =   "com.github.ghostdogpr"   %% "caliban-zio-http"       % ZIOHttpVersion
      val `zio-test`    =   "dev.zio"                 %%  "zio-test"              % ZIOVersion % Test
    }
  }
}
