ThisBuild / scalaVersion     := "2.13.10"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "ice.chrisworks"
ThisBuild / organizationName := "example"

lazy val root = (project in file("."))
  .settings(
    name := "sample-zio-streaming-poc",
    libraryDependencies ++= Seq(
      "dev.zio" %% "zio" % "2.0.2",
      "dev.zio" %% "zio-test" % "2.0.2" % Test
    ),
    testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")
  )
