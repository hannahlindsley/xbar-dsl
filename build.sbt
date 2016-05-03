name := "dsl"

version := "1.0"

scalaVersion := "2.11.8"

(resolvers in ThisBuild) += Resolver.mavenLocal

lazy val dsl = project.in(file("."))

resourceDirectory in Compile := baseDirectory.value / "src" / "resources"

libraryDependencies ++= Seq(
  "org.scalatest" % "scalatest_2.11" % "2.2.5" % "test",
  "junit" % "junit" % "4.11",
  "org.scalaz" %% "scalaz-concurrent" % "7.1.4",
  "org.scalaz" %% "scalaz-core" % "7.1.4",
  "org.scalaz" %% "scalaz-scalacheck-binding" % "7.1.4" % "test"
)