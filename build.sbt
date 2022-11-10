ThisBuild / scalaVersion     := "2.13.10"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "ice.chrisworks"
ThisBuild / organizationName := "example"

val ZIOVersion = "2.0.3"

lazy val root = (project in file("."))
  .settings(
    name := "sample-zio-streaming-poc",
    libraryDependencies ++= Seq(
      "dev.zio"     %% "zio"             % ZIOVersion,
      "dev.zio"     %% "zio-streams"     % ZIOVersion,
      "dev.zio"     %% "zio-test"        % ZIOVersion % Test
    ),
    testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")
  )
