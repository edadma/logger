package io.github.edadma.logger

import java.time.format.DateTimeFormatter
import java.time.{ZoneOffset, ZonedDateTime}

trait LogFormatter {
  def format(
      level: LogLevel,
      message: String,
      category: Option[String],
      opId: Option[String],
      metadata: Option[Map[String, Any]],
  ): String
}

class DefaultLogFormatter(includeTimestamp: Boolean = false) extends LogFormatter {
  private val timestampFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

  override def format(
      level: LogLevel,
      message: String,
      category: Option[String],
      opId: Option[String],
      metadata: Option[Map[String, Any]],
  ): String = {
    val timestamp = if (includeTimestamp) s"[${ZonedDateTime.now(ZoneOffset.UTC).format(timestampFormatter)}] " else ""
    val categoryPart = category.map(c => s"[$c] ").getOrElse("")
    val opIdPart     = opId.map(id => s"[opId: $id] ").getOrElse("")
    val metadataPart = metadata.map(m => s" | ${m.map((k, v) => s"$k=$v").mkString(" ")}").getOrElse("")

    s"$timestamp$categoryPart$opIdPart[${level.toString}] $message$metadataPart"
  }
}
