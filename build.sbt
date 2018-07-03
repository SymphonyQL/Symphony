import sbtcrossproject.CrossPlugin.autoImport.crossProject
import sbtcrossproject.CrossType

name := "Symphony"

version := "0.0.1"

val sharedSettings = Seq(scalaVersion := "2.12.6")

SymphonyBuild.buildSetting

// - prelude

lazy val prelude = crossProject(JSPlatform, JVMPlatform)
  .crossType(crossType = CrossType.Full)
  .in(file("symphony-prelude"))
  .settings(sharedSettings)
  .settings(
    libraryDependencies ++= Seq(
      Dependencies.fastParse.value,
      Dependencies.playJson.value
    ))
  .settings(SymphonyBuild.buildSetting)

lazy val preludeJVM = prelude.jvm

lazy val preludeJS = prelude.js

// - symphony

lazy val orchestra = crossProject(JSPlatform, JVMPlatform)
  .crossType(crossType = CrossType.Full)
  .dependsOn(prelude)
  .in(file("symphony-orchestra"))
  .settings(sharedSettings)
  .settings(SymphonyBuild.buildSetting)


lazy val orchestraJVM = orchestra.jvm

lazy val orchestraJS = orchestra.js

//- below is the aggregation

lazy val root = project.in(file("."))
  .aggregate(
    preludeJVM,
    preludeJS,
    orchestraJVM,
    orchestraJS
  )
  .settings(
    sharedSettings,
    publishTo := None
  )

