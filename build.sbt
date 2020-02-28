name := "kata-imdb"

version := "0.1"

scalaVersion := "2.13.1"

libraryDependencies += "com.typesafe.akka" %% "akka-stream" % "2.6.3"
libraryDependencies += "com.lightbend.akka" %% "akka-stream-alpakka-csv" % "1.1.2"
libraryDependencies += "com.lightbend.akka" %% "akka-stream-alpakka-amqp" % "1.1.2"
libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2"
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3"