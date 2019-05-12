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
    val carry: UByte = if (CF) UByte(1) else UByte(0)
    val aSign: Boolean = A.signed > 0
    val valSign: Boolean = value.signed > 0
    val resSign: Boolean = (A + value + carry).signed > 0

    OF = (aSign && valSign && !resSign) || (!aSign && !valSign && resSign)
    CF = (value.toInt + A.toInt + carry.toInt) > 255

    A = A + value + carry
    updateZN(A)
  }

  // Performs SBC
  def performSBC(value: UByte): Unit = {
    val carry: UByte = if (CF) UByte(0) else UByte(1)
    val aSign: Boolean = A.signed > 0
    val valSign: Boolean = value.signed > 0
    val resSign: Boolean = (A-value-carry).signed > 0

    OF = (aSign && valSign && !resSign) || (!aSign && !valSign && resSign)
    CF = (A.toInt - value.toInt - carry.toInt) < 0

    A = A - value - carry
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

  // Returns status register
  // Structure: NV_BDIZC
  def status(): UByte = {
    var status: UByte = UByte(0)
    val boolToByte = (b: Boolean) => UByte(if (b) 1 else 0)
    status |= boolToByte(NF) << 7
    status |= boolToByte(OF) << 6
    status |= boolToByte(BC) << 4
    status |= boolToByte(DM) << 3
    status |= boolToByte(ID) << 2
    status |= boolToByte(ZF) << 1
    status |= boolToByte(CF) << 0
    status
  }

  def updateStatus(status: UByte): Unit = {
    val getBit = (b: UByte, idx: Int) => (b & (UByte(1) << idx)) == (UByte(1) << idx)
    NF = getBit(status, 7)
    OF = getBit(status, 6)
    BC = getBit(status, 4)
    DM = getBit(status, 3)
    ID = getBit(status, 2)
    ZF = getBit(status, 1)
    CF = getBit(status, 0)
  }

  def reset(): Unit = {
    X = UByte(0x00)
    Y = UByte(0x00)
    A = UByte(0x00)
    SP = UByte(0x00)
    PC = UShort(0x0000)
    updateStatus(UByte(0x00))
  }

  override def toString: String = {
    val flags = s"CF: $CF, ZF: $ZF, ID: $ID, DM: $DM, BC: $BC, OF: $OF, NF: $NF"
    s"X: 0x${X.toInt.toHexString}, Y: 0x${Y.toInt.toHexString}, A: 0x${A.toInt.toHexString}, PC: 0x${PC.toInt.toHexString}, SP: 0x${SP.toInt.toHexString}, $flags"
  }
}

object Reg6502{
  def apply(): Reg6502 = new Reg6502()
}
