name := "nhl-stats-grabber"

organization in ThisBuild := "ca.benwu"
version in ThisBuild := "0.1"
scalaVersion in ThisBuild := "2.12.7"

/**
  * Projects
  */

lazy val global = project
  .in(file("."))
  .settings(commonSettings)
  .aggregate(common, getPlayers, getPlayerStats)

lazy val common = project
  .settings(
    name := "common",
    libraryDependencies := commonDependencies,
    commonSettings
  )

lazy val getPlayers = project
  .settings(
    name := "getPlayers",
    libraryDependencies ++= lambdaDependencies,
    commonSettings,
    assemblySettings
  )
  .dependsOn(common)

lazy val getPlayerStats = project
  .settings(
    name := "getPlayerStats",
    libraryDependencies ++= lambdaDependencies,
    commonSettings,
    assemblySettings
  )
  .dependsOn(common)

/**
  * Dependencies
  */

lazy val lambdaDependencies = Seq()

lazy val commonDependencies = Seq(
  "com.amazonaws" % "aws-lambda-java-events" % "2.2.1",
  "com.amazonaws" % "aws-lambda-java-core" % "1.2.0",
  "com.github.seratch" %% "awscala-dynamodb" % "0.8.+",
  "org.json4s" %% "json4s-native" % "3.6.2",
  "org.scalaj" %% "scalaj-http" % "2.4.1"
)

/**
  * Settings
  */

lazy val commonSettings = Seq(
  scalacOptions := compilerOptions
)

lazy val compilerOptions = Seq(
  "-unchecked",
  "-deprecation",
  "-feature",
  "-Xfatal-warnings"
)

lazy val assemblySettings = Seq(
  assemblyJarName in assembly := name.value + ".jar",
  assemblyMergeStrategy in assembly := {
    case PathList("META-INF", xs @ _*) => MergeStrategy.discard
    case _                             => MergeStrategy.first
  }
)
