package io.github.edadma.logger

import io.github.edadma.cross_platform.appendFile

class FileHandler(filePath: String) extends LogHandler {
  override def log(level: LogLevel, message: String): Unit = {
    appendFile(filePath, message + "\n")
  }
}
