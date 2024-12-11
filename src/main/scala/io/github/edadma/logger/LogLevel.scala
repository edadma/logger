package io.github.edadma.logger

sealed trait LogLevel
object LogLevel {
  case object ALL   extends LogLevel
  case object TRACE extends LogLevel
  case object DEBUG extends LogLevel
  case object INFO  extends LogLevel
  case object WARN  extends LogLevel
  case object ERROR extends LogLevel
  case object OFF   extends LogLevel

  val levels: List[LogLevel] = List(ALL, TRACE, DEBUG, INFO, WARN, ERROR, OFF)

  def shouldLog(currentLevel: LogLevel, messageLevel: LogLevel): Boolean = {
    levels.indexOf(messageLevel) >= levels.indexOf(currentLevel)
  }
}
