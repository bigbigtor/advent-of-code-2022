val scala3Version = "3.2.1"

lazy val root = project
  .in(file("."))
  .settings(
    name := "advent-of-code-2022",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    libraryDependencies ++= Seq(
      "org.scalameta" %% "munit" % "0.7.29" % Test,
      "org.scalactic" %% "scalactic" % "3.2.14",
      "org.scalatest" %% "scalatest" % "3.2.14" % "test",
      "org.scalatestplus" %% "mockito-4-6" % "3.2.14.0" % "test",
      "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.14.1"
    )
  )
