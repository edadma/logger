package io.guthub.edadma.logger

@main def run(): Unit =
  val logger = LoggerFactory.createConsoleLogger(LogLevel.DEBUG)
  logger.info("This is an info message.")
  logger.debug("This is a debug message.")
