package io.guthub.edadma.logger

import scala.scalajs.js
import scala.scalajs.js.Dynamic.global

class FileLogHandler(filePath: String) extends LogHandler {
  private val fs = global.require("fs")

  override def log(level: LogLevel, message: String): Unit = {
    val timestamp  = TimestampUtil.getTimestamp
    val logMessage = s"[$timestamp] [${level.toString}] $message\n"

    fs.appendFileSync(filePath, logMessage)
  }
}
