package helpers

trait Logger {
  def log(msg: String)

  def apply(msg: String) = {
    log(msg)
  }
}

object Logger {
  def empty(): EmptyLogger = new EmptyLogger()
  def stdout(): StdOutLogger = new StdOutLogger()
}

class EmptyLogger extends Logger{
  def log(msg: String) = {}
}

class StdOutLogger extends Logger{
  def log(msg: String) = println(msg)
}
