package cpu.instructions

import bitwise.Bitwise
import cpu.{Instruction, MemoryMap, Reg6502}
import spire.math.{UByte, UShort}

/**
  * JSR - Jump to subroutine
  */
object JSR extends Instruction(UByte(0x20), 3, 6){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    val subAddrLower: UByte = args(0)
    val subAddrUpper: UByte = args(1)

    val retAddr: UShort = reg.PC + UShort(2)
    val retLower: UByte = Bitwise.lower(retAddr)
    val retUpper: UByte = Bitwise.upper(retAddr)

    memory.writeTo(reg.SP, retUpper)
    memory.writeTo(reg.SP - UByte(1), retLower)
    reg.SP -= UByte(2)

    val subAddr: UShort = (UShort(subAddrUpper.toInt) << 8) + UShort(subAddrLower.toInt)
    reg.PC = subAddr - UShort(size)
  }
}

/**
  * RTS - Return from subroutine
  */
object RTS extends Instruction(UByte(0x60), 1, 6){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    val retLower = memory.readFrom(reg.SP + UByte(1))
    val retUpper = memory.readFrom(reg.SP + UByte(2))
    reg.SP += UByte(2)

    val retAddr: UShort = Bitwise.word(retLower, retUpper)
    reg.PC = retAddr
  }
}

/**
  * RTI - Return from interrupt
  */
object RTI extends Instruction(UByte(0x40), 1, 6){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    val status = memory.readFrom(reg.SP + UByte(1))
    val retLower = memory.readFrom(reg.SP + UByte(2))
    val retUpper = memory.readFrom(reg.SP + UByte(3))
    reg.SP += UByte(3)

    reg.updateStatus(status)
    val retAddr: UShort = Bitwise.word(retLower, retUpper)
    reg.PC = retAddr
  }
}
