import akka.actor.{PoisonPill, ActorRef, Actor}

/**
  * Created by mjyoung on 11/19/2015.
  */
class Client(val username: String, server: ActorRef) extends Actor {
  server ! Connect(username)

  def receive = {
    case NewMsg(from, msg) => {
      println(f"[$username%s's client] - $from%s: $msg%s")
    }

    case Send(msg) => server ! Broadcast(msg)
    case Info(msg) => {
      println(f"[$username%s's client] - $msg%s")
    }
    case Disconnect => {
      self ! PoisonPill
    }
  }
}
