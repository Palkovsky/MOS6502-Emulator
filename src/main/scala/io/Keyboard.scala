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

    var key: UByte = UByte(0x41)

    Thread.sleep(50)
    
    while(true) {
      if(Thread.interrupted()) return
      try{
        Thread.sleep(50)
      } catch {
        case _: InterruptedException => {
          Thread.currentThread().interrupt()
        }
      }

      ports(0) = key
      cpu.irq()
      key = key + UByte(0x01)
    }
  }
}

object Keyboard{
  def apply(cpu: MOS6502): Keyboard = new Keyboard(cpu)
}
