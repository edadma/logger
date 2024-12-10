package io.guthub.edadma.logger

object NodeLoggerFactory {
  def createFileLogger(filePath: String, minLevel: LogLevel = LogLevel.INFO): Logger = {
    new Logger(new FileLogHandler(filePath), minLevel)
  }
}
