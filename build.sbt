import Dependencies._

ThisBuild / scalaVersion     := "2.13.10"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "ice.chrisworks"
ThisBuild / organizationName := "example"

//Module 1: Here we build a naive service which simulates something similar to an external service living somewhere
lazy val NaiveExternalHttpService = {
  project
    .in(file("modules/10-NaiveExternalHttpService"))
    .settings(
      name := "10-NaiveExternalHttpService",
      libraryDependencies ++= Seq(
        dev.zio.`zio-core`,
        dev.zio.`zio-json`,
        dev.zio.`zio-sql`,
        dev.zio.`zio-test`,
        dev.zio.`zio-config`,
        dev.zio.`zio-config-typesafe`,
        dev.zio.`zio-config-magnolia`,
        org.postgresql.postgresql,
        com.github.ghostdogpr.`caliban-zio-http`
      ),
      testFrameworks += `zio-test-framework`
    )
}

//Module 2: Here is the HEART/CORE of this POC. Here lies the logics/pipelines
lazy val StreamingCore = {
  project
    .in(file("modules/20-StreamingCore"))
    .settings(
      name := "20-StreamingCore",
      libraryDependencies ++= Seq(
        dev.zio.`zio-core`,
        dev.zio.`zio-test`,
        dev.zio.`zio-stream`
      ),
      testFrameworks += `zio-test-framework`
    )
}

//Projects Core Dependencies
lazy val CoreDependencies = Seq(
  dev.zio.`zio-core`,
)

//Projects Test Dependencies
lazy val TestDependencies = Seq(
  dev.zio.`zio-test`
)

//In the root, we shall dare to connect the entire scattered modules needed
// for the stream processing and then start the process
lazy val root = (project in file("."))
  .settings(
    name := "sample-zio-streaming-poc",
    libraryDependencies ++= CoreDependencies ++ TestDependencies
  )
  .dependsOn(
    StreamingCore
  )
  .aggregate(
    NaiveExternalHttpService,
    StreamingCore,
  )
