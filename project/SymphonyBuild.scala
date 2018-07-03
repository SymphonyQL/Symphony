import sbt._
import sbt.Keys._

object SymphonyBuild {
  lazy val buildSetting = Seq(
    organization := "xyz.reactiveplatform.symphony",
    version := (version in ThisBuild).value
  )
}
