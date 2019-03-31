package cpu

import spire.math.{UByte, UShort}

class Reg6502 private(){
  // 8-bit registers
  private var _X:  UByte = UByte(0x00)
  private var _Y:  UByte = UByte(0x00)
  private var _A:  UByte = UByte(0x00)
  private var _SP: UByte = UByte(0x00)

  // 16-bit register
  private var _PC: UShort = UShort(0x0000)

  // FLAGS
  private var _CF: Boolean = false
  private var _ZF: Boolean = false
  private var _ID: Boolean = false
  private var _DM: Boolean = false
  private var _BC: Boolean = false
  private var _OF: Boolean = false
  private var _NF: Boolean = false

  def X: UByte  = _X
  def Y: UByte  = _Y
  def A: UByte  = _A
  def SP:UByte  = _SP
  def PC:UShort = _PC
  def CF:Boolean = _CF
  def ZF:Boolean = _ZF
  def ID:Boolean = _ID
  def DM:Boolean = _DM
  def BC:Boolean = _BC
  def OF:Boolean = _OF
  def NF:Boolean = _NF

  def X_=(newX: UByte): Unit   = _X   = newX
  def Y_=(newY: UByte): Unit   = _Y   = newY
  def A_=(newA: UByte): Unit   = _A   = newA
  def SP_(newSP: UByte): Unit  = _SP  = newSP
  def PC_=(newPC: UShort): Unit = _PC = newPC

  override def toString: String = {
    val flags = s"CF: $CF, ZF: $ZF, ID: $ID, DM: $DM, BC: $BC, OF: $OF, NF: $NF"
    s"X: 0x${X.toInt.toHexString}, Y: 0x${Y.toInt.toHexString}, A: 0x${_A.toInt.toHexString}, PC: 0x${PC.toInt.toHexString}, SP: 0x${SP.toInt.toHexString}, $flags"
  }
}

object Reg6502{
  def apply(): Reg6502 = new Reg6502()
}