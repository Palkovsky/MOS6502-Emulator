package io

import java.util.Scanner
import spire.math.UByte

import cpu.MOS6502

/*
 * Ports:
 *  0 - if set to high stops CPU
 */
class Monitor(cpu: MOS6502) extends Device(cpu){

  val ports: Array[UByte] = Array(UByte(0))

  override def run(): Unit = {

    while(true) ports.synchronized {
      if(Thread.interrupted()) return
      if(ports(0) != UByte(0)){
        cpu.reset()
        ports(0) = UByte(0)
      }
    }
  }

  override def finalize(): Unit = {
    super.finalize()
  }
}

object Monitor{
  def apply(cpu: MOS6502): Monitor = new Monitor(cpu)
}
