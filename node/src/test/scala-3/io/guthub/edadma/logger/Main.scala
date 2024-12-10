package io.guthub.edadma.logger

@main def run(): Unit =
  val logger = NodeLoggerFactory.createFileLogger("log", LogLevel.DEBUG)

  logger.info("This is an info message.")
  logger.debug("This is a debug message.")
