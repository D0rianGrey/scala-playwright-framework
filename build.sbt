name := "scala-playwright-framework"
version := "0.1"
scalaVersion := "2.13.16"

// Зависимости для Playwright
libraryDependencies ++= Seq(
  "com.microsoft.playwright" % "playwright" % "1.37.0",
  
  // ScalaTest для написания тестов
  "org.scalatest" %% "scalatest" % "3.2.15" % Test,
  
  // Config для управления конфигурациями
  "com.typesafe" % "config" % "1.4.2",
  
  // Логирование
  "ch.qos.logback" % "logback-classic" % "1.4.7",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.5"
)