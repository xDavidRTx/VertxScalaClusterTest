import sbt.Package.ManifestAttributes
import sbt._

scalaVersion := Version.Scala

credentials += Credentials(new File("credentials.properties"))

lazy val playerApplication = (project in file("."))
  .settings(
    name := "ui-kafka-log-producer",
    mainClass in assembly := Some("io.vertx.core.Launcher"),
    libraryDependencies ++= Seq(
      Library.Vertx_lang_scala,
      Library.Vertx_web,
      Library.vertx_hazelcast,
      "org.apache.httpcomponents" % "httpclient" % "4.5.7",
    ).map(_.exclude("org.slf4j", "slf4j-jdk14")),
    packageOptions += ManifestAttributes(
      ("Main-Verticle", "scala:TestClusterProducer"))
  ) 
