# Scala Logger

A simple, flexible logging library for Scala applications that supports both console and file-based logging. The library provides different log levels, categories, operation IDs, and customizable formatting.

## Features

- Multiple log levels (ALL, TRACE, DEBUG, INFO, WARN, ERROR, OFF)
- Console and file-based logging
- Support for categories to organize logs
- Operation ID tracking
- Customizable log formatting
- Optional timestamps (enabled by default)
- Node.js file system integration

## Installation

Add the following dependency to your `build.sbt`:

```scala
libraryDependencies += "io.github.edadma" %%% "logger" % "0.0.9"
```

## Basic Usage

```scala
import io.github.edadma.logger._

// Get the default logger instance
val logger = LoggerFactory.getLogger

// Basic logging
logger.info("This is a basic log message")
logger.debug("Debug message with category", category = "HTTP")
logger.warn("Warning message with operation ID", opId = 123)
logger.error("Error message with category and opId", category = "DB", opId = "XYZ-789", metadata = Map("key1" -> 123, "key2" -> "value2"))

// Configure timestamp display
logger.setFormatter(new DefaultLogFormatter(includeTimestamp = false)) // Disable timestamps
logger.info("Message without timestamp")

// Re-enable timestamps
logger.setFormatter(new DefaultLogFormatter(includeTimestamp = true))
logger.info("Message with timestamp")
```

## Log Levels

The library supports the following log levels (in order of increasing severity):
- ALL (lowest)
- TRACE
- DEBUG
- INFO
- WARN
- ERROR
- OFF (highest)

To set the log level:

```scala
logger.setLogLevel(LogLevel.DEBUG)
```

Messages with a level lower than the current log level will not be logged.

## File Logging

To enable file logging:

```scala
LoggerFactory.setFileLogging()
```

or

```scala
logger.setHandler(new FileHandler("path/to/logfile.log"))
```

or

```scala
logger.setFileLogging()
```

## Custom Formatting

You can create a custom formatter by implementing the `LogFormatter` trait:

```scala
class CustomFormatter extends LogFormatter {
  override def format(
    level: LogLevel,
    message: String,
    category: Option[String],
    opId: Option[String]
  ): String = {
    val categoryPart = category.map(c => s"($c)").getOrElse("")
    val opIdPart = opId.map(id => s"[Op:$id]").getOrElse("")
    s"$categoryPart$opIdPart ${level.toString}: $message"
  }
}

// Set the custom formatter
LoggerFactory.setFormatter(new CustomFormatter)
```

## Operation IDs

The logger supports operation IDs for tracking related log messages:

```scala
// Using auto-incrementing operation IDs
val opId = logger.nextOpId
logger.info("Starting operation", opId = opId)
logger.info("Operation completed", opId = opId)

// Reset operation ID counter
logger.resetOpId()
```

## Categories

Categories help organize logs by component or feature:

```scala
logger.info("Database connected", category = "DB")
logger.warn("HTTP request timeout", category = "HTTP")
logger.error("Authentication failed", category = "AUTH")
```

## Default Log Format

The default log format includes:
- Timestamp (enabled by default, can be disabled)
- Category (if provided)
- Operation ID (if provided)
- Log level
- Message

Example outputs:
```
# With timestamp (default)
[2024-12-11 14:30:00] [HTTP] [opId: 123] [INFO] Request processed successfully

# Without timestamp
[HTTP] [opId: 123] [INFO] Request processed successfully
```

## Custom Handlers

You can create custom log handlers by implementing the `LogHandler` trait:

```scala
class CustomHandler extends LogHandler {
  override def log(level: LogLevel, message: String): Unit = {
    // Custom logging implementation
  }
}

// Set the custom handler
logger.setHandler(new CustomHandler)
```

## Building and Testing

The project uses sbt and Scala.js. Main build commands:

```bash
sbt compile      # Compile the project
sbt test        # Run tests
sbt fastLinkJS  # Create development JS bundle
sbt fullLinkJS  # Create production JS bundle
```

## Requirements

- Scala 3.5.2+
- Scala.js 1.17.0+
- Node.js (for file logging)