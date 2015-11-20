import akka.actor.{ActorRef, Actor}

/**
  * Created by mjyoung on 11/19/2015.
  */
class Server extends Actor {
  var clients = List[(String, ActorRef)]();
  def receive = {
    case Connect(username) => {
      broadcast(Info(f"$username%s joined the chat"))
      clients = (username,sender) :: clients
      context.watch(sender)
    }
    case Broadcast(msg) => {
      val username = getUsername(sender)
      broadcast(NewMsg(username,msg))
    }
    case Terminated(client) => {
      val username = getUsername(client)
      clients = clients.filter(sender  != _._2)
      broadcast(Info(f"$username%s left the chat"))
    }
  }

  def broadcast(msg: Msg): Unit ={
    clients.foreach(x => x._2 ! msg)
  }

  def getUsername(actor: ActorRef):String ={
    clients.filter(actor == _._2).head._1
  }
}
