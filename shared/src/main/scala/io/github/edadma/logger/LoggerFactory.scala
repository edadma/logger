package io.github.edadma.logger

import io.github.edadma.logger.FileHandler

import scala.scalajs.js
import scala.scalajs.js.Dynamic.global

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
