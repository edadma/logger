package io.github.edadma.logger

class Logger(private var handler: LogHandler, private var formatter: LogFormatter) {
  private var globalLogLevel: LogLevel = LogLevel.INFO

  def setLogLevel(level: LogLevel): Unit = {
    globalLogLevel = level
  }

  def setHandler(newHandler: LogHandler): Unit = {
    handler = newHandler
  }

  def setFormatter(newFormatter: LogFormatter): Unit = {
    formatter = newFormatter
  }

  def log(level: LogLevel, message: String, category: String = "", opId: Any = null): Unit = {
    if (LogLevel.shouldLog(globalLogLevel, level)) {
      val opIdStr = if (opId != null) opId.toString else ""
      val formattedMessage =
        formatter.format(level, message, Option(category).filter(_.nonEmpty), Option(opIdStr).filter(_.nonEmpty))
      handler.log(level, formattedMessage)
    }
  }

  def trace(message: String, category: String = "", opId: Any = null): Unit =
    log(LogLevel.TRACE, message, category, opId)

  def debug(message: String, category: String = "", opId: Any = null): Unit =
    log(LogLevel.DEBUG, message, category, opId)

  def info(message: String, category: String = "", opId: Any = null): Unit =
    log(LogLevel.INFO, message, category, opId)

  def warn(message: String, category: String = "", opId: Any = null): Unit =
    log(LogLevel.WARN, message, category, opId)

  def error(message: String, category: String = "", opId: Any = null): Unit =
    log(LogLevel.ERROR, message, category, opId)
}
