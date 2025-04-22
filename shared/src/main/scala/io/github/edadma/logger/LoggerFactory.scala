package io.github.edadma.logger

import io.github.edadma.logger.FileHandler

object LoggerFactory {
  private val logger = newLogger

  val defaultFormatter = new DefaultLogFormatter(includeTimestamp = true)

  def newLogger: Logger = new Logger(new ConsoleHandler, defaultFormatter)

  def getLogger: Logger = logger

  def setFileLogging(): Unit = logger.setFileLogging()

  def setFormatter(newFormatter: LogFormatter): Unit = {
    logger.setFormatter(newFormatter)
  }
}
