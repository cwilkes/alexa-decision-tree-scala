import sbt.Keys._

javacOptions ++= Seq("-source", "1.8", "-target", "1.8", "-Xlint")

lazy val root = (project in file(".")).
  settings(
    name := "lambda-demo",
    version := "1.0",
    scalaVersion := "2.11.8",
    retrieveManaged := true,
    libraryDependencies += "com.amazonaws" % "aws-lambda-java-core" % "1.1.0",
    libraryDependencies += "com.amazonaws" % "aws-lambda-java-events" % "1.2.0",
    libraryDependencies += "com.fasterxml.jackson.module" % "jackson-module-scala_2.11" % "2.7.3"
  )

assemblyMergeStrategy in assembly := {
   {
    case PathList("META-INF", xs @ _*) => MergeStrategy.discard
    case x => MergeStrategy.first
   }
}