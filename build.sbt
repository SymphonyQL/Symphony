import sbtcrossproject.CrossPlugin.autoImport.crossProject
import sbtcrossproject.CrossType

name := "Symphony"

version := "0.0.1"

val sharedSettings = Seq(scalaVersion := "2.12.6")

//- parser

lazy val parser = crossProject(JSPlatform, JVMPlatform)
  .crossType(crossType = CrossType.Pure)
  .in(file("parser"))
  .settings(sharedSettings)
  .settings(
    libraryDependencies ++= Seq(
      "com.lihaoyi" %%% "fastparse" % "1.0.0",
      "com.typesafe.play" %%% "play-json" % "2.6.9"
    )
  )

lazy val parserJVM = parser.jvm

lazy val parserJS = parser.js

// - schema

lazy val schema = crossProject(JSPlatform, JVMPlatform)
  .crossType(crossType = CrossType.Pure)
  .dependsOn(parser)
  .in(file("schema"))
  .settings(sharedSettings)

lazy val schemaJVM = schema.jvm

lazy val schemaJS = schema.js

// - symphony

lazy val symphony = crossProject(JSPlatform, JVMPlatform)
  .crossType(crossType = CrossType.Full)
  .dependsOn(schema)
  .in(file("symphony"))
  .settings(sharedSettings)

lazy val symphonyJVM = symphony.jvm

lazy val symphonyJS = symphony.js

//- below is the aggregation

lazy val root = project.in(file("."))
  .aggregate(
    parserJVM,
    parserJS,
    schemaJVM,
    schemaJS,
    symphonyJVM,
    symphonyJS
  )
  .settings(
    sharedSettings,
    publishTo := None
  )

