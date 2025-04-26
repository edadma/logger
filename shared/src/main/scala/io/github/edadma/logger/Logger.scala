package io.github.edadma.logger

class Logger(private var handler: LogHandler, private var formatter: LogFormatter) {
  private var logLevel: LogLevel = LogLevel.INFO
  private var opId: Int          = 0

  def nextOpId: Int =
    val n = opId

    opId += 1
    n

  def resetOpId(): Unit = opId = 0

  def setLogLevel(level: LogLevel): Unit = {
    logLevel = level
  }

  def setFileLogging(): Unit = setHandler(new FileHandler(s"log_${System.currentTimeMillis}"))

  def setHandler(newHandler: LogHandler): Unit = {
    handler = newHandler
  }

  def setFormatter(newFormatter: LogFormatter): Unit = {
    formatter = newFormatter
  }

  private def toString(a: Any): String =
    a match
      case m: Map[?, ?] => m map ((k, v) => s"$k: $v") mkString ", "
      case s: Seq[?]    => s map String.valueOf mkString ", "
      case _            => String.valueOf(a)

  def log(
      level: LogLevel,
      message: Any,
      category: String = "",
      opId: Any = null,
      metadata: Map[String, Any] = Map.empty,
  ): Unit = {
    if (LogLevel.shouldLog(logLevel, level)) {
      val opIdStr = if (opId != null) opId.toString else ""
      println(formatter)
      val formattedMessage =
        formatter.format(
          level,
          toString(message),
          Option(category).filter(_.nonEmpty),
          Option(opIdStr).filter(_.nonEmpty),
          Option(metadata).filter(_.nonEmpty),
        )
      handler.log(level, formattedMessage)
    }
  }

  def trace(message: Any, category: String = "", opId: Any = null, metadata: Map[String, Any] = Map.empty): Unit =
    log(LogLevel.TRACE, message, category, opId, metadata)

  def debug(message: Any, category: String = "", opId: Any = null, metadata: Map[String, Any] = Map.empty): Unit =
    log(LogLevel.DEBUG, message, category, opId, metadata)

  def info(message: Any, category: String = "", opId: Any = null, metadata: Map[String, Any] = Map.empty): Unit =
    log(LogLevel.INFO, message, category, opId, metadata)

  def warn(message: Any, category: String = "", opId: Any = null, metadata: Map[String, Any] = Map.empty): Unit =
    log(LogLevel.WARN, message, category, opId, metadata)

  def error(message: Any, category: String = "", opId: Any = null, metadata: Map[String, Any] = Map.empty): Unit =
    log(LogLevel.ERROR, message, category, opId, metadata)
}
