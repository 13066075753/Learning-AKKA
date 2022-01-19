name := "AKKA"

version := "0.1"

scalaVersion := "2.13.3"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.5.23",
  "com.typesafe.akka" %% "akka-testkit" % "2.5.23" % "test",
  "org.scala-lang.modules" %% "scala-java8-compat" % "1.0.1",
  "org.scalatest" %% "scalatest" % "3.2.10" % "test"
)