package io.guthub.edadma.logger

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object TimestampUtil {
  private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

  def getTimestamp: String = LocalDateTime.now().format(formatter)
}
