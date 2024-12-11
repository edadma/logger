package io.github.edadma.logger

trait LogFormatter {
  def format(level: LogLevel, message: String, category: Option[String], opId: Option[String]): String
}

class DefaultLogFormatter(includeTimestamp: Boolean = false) extends LogFormatter {
  private val timestampFormatter: java.time.format.DateTimeFormatter =
    java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

  override def format(level: LogLevel, message: String, category: Option[String], opId: Option[String]): String = {
    val timestamp    = if (includeTimestamp) s"[${java.time.LocalDateTime.now.format(timestampFormatter)}] " else ""
    val categoryPart = category.map(c => s"[$c] ").getOrElse("")
    val opIdPart     = opId.map(id => s"[opId: $id] ").getOrElse("")
    s"$timestamp$categoryPart$opIdPart[${level.toString}] $message"
  }
}
