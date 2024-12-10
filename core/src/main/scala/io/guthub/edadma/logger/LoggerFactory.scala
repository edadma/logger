package io.guthub.edadma.logger

object LoggerFactory {
  def createConsoleLogger(minLevel: LogLevel = LogLevel.INFO): Logger = {
    new Logger(new DefaultConsoleHandler(), minLevel)
  }
}
