package io

import java.util.Scanner
import spire.math.UByte

import cpu.MOS6502

class Monitor(cpu: MOS6502) extends Device(cpu){

  val ports: Array[UByte] = Array(
    UByte(0), // 0 - STOP

    UByte(0), // 1 - SLEEP AMOUNT
    UByte(0) // 2 - SLEEP TRIG
  )

  override def run(): Unit = {
    while(true) ports.synchronized {
      if(Thread.interrupted()) return

      if(ports(0) != UByte(0)){
        cpu.reset()
        ports(0) = UByte(0)
      }

      if(ports(2) != UByte(0) && ports(1) != UByte(0)){
        cpu.halt(true)
        Thread.sleep(ports(1).toInt*1000)
        cpu.halt(false)
        ports(1) = UByte(0)
        ports(2) = UByte(0)
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
