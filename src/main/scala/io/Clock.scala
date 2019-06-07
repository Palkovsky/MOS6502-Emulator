package io

import java.util.{Calendar, GregorianCalendar, Date}
import spire.math.UByte

import cpu.MOS6502

class Clock(cpu: MOS6502) extends Device(cpu){

  val ports: Array[UByte] = Array(
    UByte(0), // 0 - HOUR
    UByte(0), // 1 - MINUTE
    UByte(0)  // 2  - SECONDS
  )

  override def run(): Unit = {
    val calendar: Calendar = new GregorianCalendar()

    while(true) {
      if(Thread.interrupted()) return

      calendar.setTime(new Date())
      ports(0) = UByte(calendar.get(Calendar.HOUR_OF_DAY))
      ports(1) = UByte(calendar.get(Calendar.MINUTE))
      ports(2) = UByte(calendar.get(Calendar.SECOND))

      try {
        Thread.sleep(1000)
      }catch {
        case _: InterruptedException => Thread.currentThread().interrupt()
      }
    }
  }

  override def finalize(): Unit = {
    super.finalize()
  }
}

object Clock{
  def apply(cpu: MOS6502): Clock = new Clock(cpu)
}
