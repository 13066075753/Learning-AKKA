package pong

import akka.actor.Status.Success
import akka.actor.{ActorSystem, Props}
import akka.pattern.ask
import akka.util.Timeout
import org.scalatest.funspec.AnyFunSpecLike
import org.scalatest.matchers.must.Matchers

import scala.concurrent.Await
import scala.concurrent.duration.DurationInt
import scala.util.Try


class ScalaAskExamplesTest extends AnyFunSpecLike with Matchers{
  val system = ActorSystem()
  implicit val timeout = Timeout(5 seconds)
  val pongActor = system.actorOf(Props(classOf[PongActor]))

  def askMessage(message:String) = {
    (pongActor ? message).mapTo[String]
  }

  describe("Pong actor") {
    it ("should respond with Pong") {
      val future = askMessage("Ping")
      val result = Await.result(future, 1 second)
      assert(result == "Pong")
    }

    it ("should fail on unknown message") {
      val future = askMessage("unknown")
      intercept[Exception] {
        Await.result(future, 1 second)
      }
    }
  }

  describe("FutureExamples") {
    import scala.concurrent.ExecutionContext.Implicits.global
    it ("should print to console") {
      val future = askMessage("ping")
      future.onComplete({
        case value: Try[String] => println("replied with: " + value)
      })

      Thread.sleep(10000)
    }
  }
}
