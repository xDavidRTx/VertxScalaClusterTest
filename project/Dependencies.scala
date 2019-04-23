import sbt._

object Version {
  final val Scala           =  "2.12.7"
  final val Vertx           =  "3.7.0"
}

object Library {
  val Vertx_lang_scala      = "io.vertx"                    %%  "vertx-lang-scala"               % Version.Vertx
  val Vertx_hazelcast       = "io.vertx"                    %   "vertx-hazelcast"                % Version.Vertx
  val Vertx_web             = "io.vertx"                    %%  "vertx-web-scala"                % Version.Vertx
  val vertx_hazelcast       = "io.vertx"                    %  "vertx-hazelcast"                 % Version.Vertx
}
