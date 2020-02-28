package org.kata

import akka.stream.scaladsl._
import akka.stream.alpakka.csv.scaladsl.{CsvParsing, CsvToMap}
import akka.Done
import akka.actor.ActorSystem
import akka.util.ByteString

import scala.concurrent._
import java.nio.file.Paths

import akka.stream.alpakka.amqp.{AmqpUriConnectionProvider, AmqpWriteSettings, QueueDeclaration}
import akka.stream.alpakka.amqp.scaladsl.AmqpSink


object Main extends App {
  implicit val system = ActorSystem("KataIMDB")

  val file = Paths.get("/tmp/title.basics.tsv")

  val queueName = "comedy-movies"
  val queueDeclaration = QueueDeclaration(queueName)
  val connectionUri = System.getenv("RABBITMQ_URI")
  val connectionProvider = AmqpUriConnectionProvider(connectionUri)

  val amqpSink: Sink[ByteString, Future[Done]] =
    AmqpSink.simple(
      AmqpWriteSettings(connectionProvider)
        .withRoutingKey(queueName)
        .withDeclaration(queueDeclaration)
    )

  val done: Future[Done] = FileIO.fromPath(file)
    .via(CsvParsing.lineScanner(delimiter = '\t'))
    .via(CsvToMap.toMapAsStrings())
    .filter(t => t("titleType") == "movie" && t("genres").contains("Comedy"))
    .map(t => ByteString(t("originalTitle")))
    .runWith(amqpSink)

  implicit val ec = system.dispatcher
  done.onComplete(_ => system.terminate())
}
