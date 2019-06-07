package io

import spire.math.UByte

import cpu.MOS6502

/*
 * Ports
 *  0 - input byte
 */
class Keyboard(cpu: MOS6502) extends Device(cpu){

  val ports: Array[UByte] = Array(UByte(0))

  override def run(): Unit = {

    while(true) {
      if(Thread.interrupted()) return
      try{
        Thread.sleep(2000)
      } catch {
        case _: InterruptedException => {
          Thread.currentThread().interrupt()
        }
      }
      ports(0) = UByte(0x43)
      cpu.irq()
    }
  }

  override def finalize(): Unit = {
    super.finalize()
  }
}

object Keyboard{
  def apply(cpu: MOS6502): Keyboard = new Keyboard(cpu)
}
