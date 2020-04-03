import sbt.Keys.libraryDependencies

lazy val root = (project in file("."))
  .settings(
    inThisBuild(List(
      organization := "com.sous.title",
      scalaVersion := "2.13.1",
      version := "0.1"
    )),
    name := "sous-title",

    libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.2",

    libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.2",

    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % "test"
  )