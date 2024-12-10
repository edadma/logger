ThisBuild / licenses += "ISC" -> url("https://opensource.org/licenses/ISC")
ThisBuild / versionScheme     := Some("semver-spec")
ThisBuild / scalaVersion      := "3.5.2"

publish / skip := true

lazy val logger = project
  .in(file("."))
  .aggregate(core, node)
  .settings(
    version                := "0.0.1",
    organization           := "io.github.edadma",
    githubOwner            := "edadma",
    githubRepository       := name.value,
    publishMavenStyle      := true,
    Test / publishArtifact := false,
  )

lazy val core = (project in file("core"))
  .settings(
    name := "logger-core",
    libraryDependencies ++= Seq(
      "io.github.cquiroz" %%% "scala-java-time" % "2.6.0",
    ),
    Test / scalaJSUseMainModuleInitializer := true,
    Test / scalaJSUseTestModuleInitializer := false,
    zonesFilter                            := { _ == "America/Montreal" },
  )
  .enablePlugins(ScalaJSPlugin)
  .enablePlugins(TzdbPlugin)

lazy val node = (project in file("node"))
  .dependsOn(core)
  .settings(
    name := "logger-node",
    libraryDependencies ++= Seq(
      // Add Node.js dependencies if required
    ),
    Test / scalaJSUseMainModuleInitializer := true,
    Test / scalaJSUseTestModuleInitializer := false,
    jsEnv                                  := new org.scalajs.jsenv.nodejs.NodeJSEnv(),
  )
  .enablePlugins(ScalaJSPlugin)
