ThisBuild / licenses += "ISC" -> url("https://opensource.org/licenses/ISC")
ThisBuild / versionScheme     := Some("semver-spec")
ThisBuild / scalaVersion      := "3.5.2"
ThisBuild / organization      := "io.github.edadma"
ThisBuild / githubOwner       := "edadma"
ThisBuild / version           := "0.0.2"
ThisBuild / githubRepository  := "logger"

lazy val logger = project
  .in(file("."))
  .settings(
    name                                   := "logger",
    Test / scalaJSUseMainModuleInitializer := true,
    Test / scalaJSUseTestModuleInitializer := false,
    zonesFilter                            := { _ == "America/Montreal" },
    publishMavenStyle                      := true,
    Test / publishArtifact                 := false,
    libraryDependencies ++= Seq(
      "io.github.cquiroz" %%% "scala-java-time" % "2.6.0",
    ),
    scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.CommonJSModule) }, // Enable CommonJS modules
  )
  .enablePlugins(ScalaJSPlugin)
  .enablePlugins(TzdbPlugin)
