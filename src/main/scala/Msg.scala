import akka.actor.{ActorRef, Actor}

/**
  * Created by mjyoung on 11/19/2015.
  */
abstract class Msg

case class Send(msg: String) extends Msg
case class NewMsg(from: String, msg: String) extends Msg
case class Info(msg: String) extends Msg
case class Connect(msg: String) extends Msg
case class Terminated(client: ActorRef) extends Msg
case class Broadcast(msg: String) extends Msg
case object Disconnect extends Msg
