package io.github.edadma.logger

import io.github.edadma.logger.{LogHandler, LogFormatter, DefaultLogFormatter, LogLevel}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("fs", JSImport.Namespace)
object FS extends js.Object {
  def appendFileSync(path: String, data: String): Unit = js.native
}

class FileHandler(filePath: String) extends LogHandler {
  override def log(level: LogLevel, message: String): Unit = {
    FS.appendFileSync(filePath, message + "\n")
  }
}
