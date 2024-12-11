package io.github.edadma.logger

@main def run(): Unit =
  val logger = LoggerFactory.getLogger

//  logger.setLogLevel(LogLevel.OFF)
//  LoggerFactory.enableFileLogging("log")
  logger.info("This is a basic log.")
  logger.info(Map("category" -> "log"), category = "HTTP")
  logger.warn("This log has an opId.", opId = 12345)
  logger.error("This log has both.", category = "DB", opId = "XYZ-123")
