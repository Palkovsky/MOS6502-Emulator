package io

import spire.math.UByte


/*
 * Ports - two byte array.
 * First element is for output, second for reading data from terminal
 */
class Terminal private() extends Runnable{

  val ports: Array[UByte] = Array(UByte(0), UByte(0))

  override def run(): Unit = {

    while(true) ports.synchronized {
      if (ports(0) != UByte(0)) {
        println(s"TERMINAL MSG: ${ports(0).toChar}")
        ports(0) = UByte(0)
      }

      ports(1) = UByte(0x43)
    }
  }

  override def finalize(): Unit = {
    super.finalize()
  }
}

object Terminal{
  def apply(): Terminal = new Terminal()
}
