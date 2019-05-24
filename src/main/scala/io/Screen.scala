package io

import spire.math.UByte

import cpu.MOS6502

/*
 * Ports
 *  0 - byte to print
 *  1 - print mode
 */
class Screen(cpu: MOS6502) extends Device(cpu) {

  val ports: Array[UByte] = Array(UByte(0), UByte(1))

  override def run(): Unit = {

    while(true) ports.synchronized {
      if(Thread.interrupted()) return
      if (ports(0) != UByte(0)) {
        println(s"TERMINAL MSG: ${ports(0).toChar}")
        ports(0) = UByte(0)
      }
    }
  }

  override def finalize(): Unit = {
    super.finalize()
  }
}

object Screen{
  def apply(cpu: MOS6502): Screen = new Screen(cpu)
}