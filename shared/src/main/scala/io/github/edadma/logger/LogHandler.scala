package io.github.edadma.logger

trait LogHandler {
  def log(level: LogLevel, message: String): Unit
}

class ConsoleHandler extends LogHandler {
  override def log(level: LogLevel, message: String): Unit = {
    println(message)
  }
}
