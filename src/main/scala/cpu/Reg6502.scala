package cpu

class Reg6502 private(){
  // 8-bit registers
  private var _X      = 0
  private var _Y      = 0
  private var _A      = 0
  private var _SP     = 0

  // 16-bit register
  private var _PC     = 0

  // FLAGS
  private var _CF: Boolean = false
  private var _ZF: Boolean = false
  private var _ID: Boolean = false
  private var _DM: Boolean = false
  private var _BC: Boolean = false
  private var _OF: Boolean = false
  private var _NF: Boolean = false

  def X = _X
  def Y = _Y
  def A = _A
  def SP = _SP
  def PC = _PC
  def CF = _CF
  def ZF = _ZF
  def ID = _ID
  def DM = _DM
  def BC = _BC
  def OF = _OF
  def NF = _NF

  def X_=(newX: Int): Unit   = _X  = newX%256
  def Y_=(newY: Int): Unit   = _Y  = newY%256
  def A_=(newA: Int): Unit   = _A  = newA%256
  def SP_(newSP: Int): Unit  = _SP = newSP%256
  def PC_=(newPC: Int): Unit = _PC = newPC%65536

  override def toString: String = {
    val flags = s"CF: $CF, ZF: $ZF, ID: $ID, DM: $DM, BC: $BC, OF: $OF, NF: $NF"
    s"X: ${X.toHexString}, Y: ${Y.toHexString}, A: ${_A.toHexString}, PC: ${PC.toHexString}, SP: ${SP.toHexString}, $flags"
  }
}

object Reg6502{
  def apply(): Reg6502 = new Reg6502()
}