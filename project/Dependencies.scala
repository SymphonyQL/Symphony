
import org.portablescala.sbtplatformdeps.PlatformDepsPlugin.autoImport._
import sbt._

object Dependencies {

  val fastParse = Def.setting("com.lihaoyi" %%% "fastparse" % "1.0.0")

  val playJson = Def.setting("com.typesafe.play" %%% "play-json" % "2.6.9")

}
