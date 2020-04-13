name := "sous-title"

scalaVersion := "2.13.1"

organization := "io.github.mdauthentic"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % "test"

publishMavenStyle := true

licenses := Seq("APL2" -> url("https://www.apache.org/licenses/LICENSE-2.0.txt"))

description := "A scala library for parsing .srt files and strings format"

import xerial.sbt.Sonatype._
sonatypeProjectHosting := Some(GitHubHosting("mdauthentic", "sous-title", "muideen.lawal20@gmail.com"))

publishTo := sonatypePublishTo.value

lazy val showVersion = taskKey[Unit]("Show version")
showVersion := {
  println(version.value)
}

pgpPublicRing := file("ci/pubring.asc")
pgpSecretRing := file("ci/secring.asc")
pgpPassphrase := sys.env.get("PGP_PASSPHRASE").map(_.toArray)