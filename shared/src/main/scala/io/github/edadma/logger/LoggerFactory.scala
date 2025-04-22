package io.github.edadma.logger

import io.github.edadma.logger.FileHandler

import scala.scalajs.js
import scala.scalajs.js.Dynamic.global

object LoggerFactory {
  private val logger           = newLogger

  val defaultFormatter = new DefaultLogFormatter(includeTimestamp = true)

  def newLogger: Logger = new Logger(new ConsoleHandler, defaultFormatter)

  def getLogger: Logger = logger

  def enableFileLogging(filePath: String): Unit = {
    if (isNodeJS) {
      logger.setHandler(new FileHandler(filePath))
    } else {
      throw new UnsupportedOperationException("File logging is only supported in Node.js")
    }
  }

  def setFormatter(newFormatter: LogFormatter): Unit = {
    logger.setFormatter(newFormatter)
  }

  private def isNodeJS: Boolean = {
    !js.isUndefined(global.process) && !js.isUndefined(global.process.version)
  }
}
