package cpu

import spire.math.{UByte, UShort}

class Reg6502 private(){
  // 8-bit registers
  var X:  UByte = UByte(0x00)
  var Y:  UByte = UByte(0x00)
  var A:  UByte = UByte(0x00)
  var SP: UByte = UByte(0x00)

  // 16-bit register
  var PC: UShort = UShort(0x0000)

  // FLAGS
  var CF: Boolean = false
  var ZF: Boolean = false
  var ID: Boolean = false
  var DM: Boolean = false
  var BC: Boolean = false
  var OF: Boolean = false
  var NF: Boolean = false

  // Performs ADC
  def performADC(value: UByte): Unit = {
    val aSign: Boolean = A.signed > 0
    val valSign: Boolean = value.signed > 0
    val resSign: Boolean = (A+value).signed > 0
    OF = (aSign && valSign && !resSign) || (!aSign && !valSign && resSign)
    CF = value.toInt + A.toInt > 255

    A = A + value
    updateZN(A)
  }

  // Performs SBC
  def performSBC(value: UByte): Unit = {
    val aSign: Boolean = A.signed > 0
    val valSign: Boolean = value.signed > 0
    val resSign: Boolean = (A+value).signed > 0
    OF = (aSign && valSign && !resSign) || (!aSign && !valSign && resSign)
    CF = A.toInt - value.toInt < 0

    A = A - value
    updateZN(A)
  }

  // Sets status Zero and Negative flag based on passed value
  def updateZN(v: UByte): Unit = {
    ZF = v.signed == 0
    NF = v.signed < 0
  }

  // Sets status flag for CMP, CPX, CPY operation
  def updateCMP(valA: UByte, valB: UByte): Unit = {
    CF = valA >= valB
    updateZN(valA - valB)
  }

  override def toString: String = {
    val flags = s"CF: $CF, ZF: $ZF, ID: $ID, DM: $DM, BC: $BC, OF: $OF, NF: $NF"
    s"X: 0x${X.toInt.toHexString}, Y: 0x${Y.toInt.toHexString}, A: 0x${A.toInt.toHexString}, PC: 0x${PC.toInt.toHexString}, SP: 0x${SP.toInt.toHexString}, $flags"
  }
}

object Reg6502{
  def apply(): Reg6502 = new Reg6502()
}