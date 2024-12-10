package io.guthub.edadma.logger

class Logger(handler: LogHandler, minLevel: LogLevel = LogLevel.INFO) {
  def log(level: LogLevel, message: String): Unit = {
    if (shouldLog(level)) handler.log(level, message)
  }

  def trace(message: String): Unit = log(LogLevel.TRACE, message)
  def debug(message: String): Unit = log(LogLevel.DEBUG, message)
  def info(message: String): Unit  = log(LogLevel.INFO, message)
  def warn(message: String): Unit  = log(LogLevel.WARN, message)
  def error(message: String): Unit = log(LogLevel.ERROR, message)

  private def shouldLog(level: LogLevel): Boolean = {
    // Define the order of log levels
    val levelOrder = List(LogLevel.TRACE, LogLevel.DEBUG, LogLevel.INFO, LogLevel.WARN, LogLevel.ERROR)
    levelOrder.indexOf(level) >= levelOrder.indexOf(minLevel)
  }
}
