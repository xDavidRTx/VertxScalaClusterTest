
import java.time.LocalDateTime

import io.vertx.lang.scala.ScalaVerticle
import io.vertx.lang.scala.json.JsonObject
import io.vertx.scala.core.Vertx
import io.vertx.scala.ext.web.{Router, RoutingContext}

import scala.concurrent.Future

class TestClusterProducer extends ScalaVerticle {

  override def startFuture(): Future[_] = {
    println(s"Deploying ${this.getClass.getName}")
    sys.addShutdownHook {
      println(s"Shutting down ${this.getClass.getName}")
    }

    val channel = "ui-kafka-user-sign-in"
    val router = Router.router(vertx)

    router
      .put("/topic")
      .handler { rc =>
        createSchema(channel, rc)
      }

    vertx
      .createHttpServer()
      .requestHandler(router.accept)
      .listenFuture(8666, "0.0.0.0")
      .map(_ => ())
  }

  private def createSchema(channel: String, rc: RoutingContext): Unit = {
    val vertx = Vertx.currentContext().map(_.owner()).getOrElse(Vertx.vertx)
    val eb = vertx.eventBus()
    val input : JsonObject = new JsonObject()
    input.put("username", "ClusterUser")
    input.put("email", "cluster@gmail.com")
    input.put("password", "StRoNgPaSsWoRd")
    input.put("startedDate", LocalDateTime.now().minusSeconds(1).toString)
    eb.sendFuture(channel, input)
    rc.response().setStatusCode(200).end()
  }

}
