package io

import spire.math.UByte

import cpu.MOS6502


class Screen(cpu: MOS6502) extends Device(cpu) {

  val ports: Array[UByte] = Array(
    UByte(0), /* BUFFER */
    UByte(0) /* MODE: 0 for ASCII, non-0 for decimal */
  )

  override def run(): Unit = {

    while(true) {
      if(Thread.interrupted()) return
      if (ports(0) != UByte(0)) {

        if(ports(1) == UByte(0)){
          print(s"${ports(0).toChar}")
        }else{
          println(s"${ports(0).toInt}")
        }


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
