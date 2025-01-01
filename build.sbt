ThisBuild / licenses += "ISC"    -> url("https://opensource.org/licenses/ISC")
ThisBuild / versionScheme        := Some("semver-spec")
ThisBuild / scalaVersion         := "3.5.2"
ThisBuild / organization         := "io.github.edadma"
ThisBuild / organizationName     := "edadma"
ThisBuild / organizationHomepage := Some(url("https://github.com/edadma"))
ThisBuild / version              := "0.0.6"

ThisBuild / sonatypeCredentialHost := "s01.oss.sonatype.org"
ThisBuild / sonatypeRepository     := "https://s01.oss.sonatype.org/service/local"

ThisBuild / publishConfiguration := publishConfiguration.value.withOverwrite(true).withChecksums(Vector.empty)
ThisBuild / resolvers ++= Seq(
  Resolver.mavenLocal,
)
ThisBuild / resolvers ++= Resolver.sonatypeOssRepos("snapshots") ++ Resolver.sonatypeOssRepos("releases")

ThisBuild / sonatypeProfileName := "io.github.edadma"

ThisBuild / scmInfo := Some(
  ScmInfo(
    url("https://github.com/edadma/logger"),
    "scm:git@github.com:edadma/logger.git",
  ),
)
ThisBuild / developers := List(
  Developer(
    id = "edadma",
    name = "Edward A. Maxedon, Sr.",
    email = "edadma@gmail.com",
    url = url("https://github.com/edadma"),
  ),
)
ThisBuild / description := "A Scala.js logger library"
ThisBuild / homepage    := Some(url("https://github.com/edadma/logger"))

// Sonatype settings
ThisBuild / pomIncludeRepository := { _ => false }
ThisBuild / publishTo := {
  val nexus = "https://s01.oss.sonatype.org/"
  if (isSnapshot.value) Some("snapshots" at nexus + "content/repositories/snapshots")
  else Some("releases" at nexus + "service/local/staging/deploy/maven2")
}
ThisBuild / publishMavenStyle := true

lazy val logger = project
  .in(file("."))
  .settings(
    name                                   := "logger",
    Test / scalaJSUseMainModuleInitializer := true,
    Test / scalaJSUseTestModuleInitializer := false,
    zonesFilter                            := { _ == "America/Montreal" },
    Test / publishArtifact                 := false,
    libraryDependencies ++= Seq(
      "io.github.cquiroz" %%% "scala-java-time" % "2.6.0",
    ),
    scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.CommonJSModule) },
  )
  .enablePlugins(ScalaJSPlugin)
  .enablePlugins(TzdbPlugin)

// https://www.awwsmm.com/blog/publish-your-scala-sbt-project-to-maven-in-5-minutes-with-sonatype
