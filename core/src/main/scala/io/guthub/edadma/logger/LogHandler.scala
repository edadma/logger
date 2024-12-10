package io.guthub.edadma.logger

trait LogHandler {
  def log(level: LogLevel, message: String): Unit
}

class DefaultConsoleHandler extends LogHandler {
  override def log(level: LogLevel, message: String): Unit = {
    val timestamp = TimestampUtil.getTimestamp

    println(s"[$timestamp] [${level.toString}] $message")
  }
}
