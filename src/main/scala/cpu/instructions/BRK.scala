package cpu.instructions

import helpers.Bitwise
import cpu.{Instruction, MOS6502, MemoryMap, Reg6502}
import spire.math.{UByte, UShort}

/*
 * BRK defines software interrupt.
 */
object BRK extends Instruction(UByte(0x00), 1, 7){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    reg.BC = true

    val status: UByte = reg.status()
    val pcLower: UByte = Bitwise.lower(reg.PC)
    val pcUpper: UByte = Bitwise.upper(reg.PC)

    memory.writeTo(reg.SP, pcUpper)
    memory.writeTo(reg.SP - UByte(1), pcLower)
    memory.writeTo(reg.SP - UByte(2), status)
    reg.SP -= UByte(3)

    val newPcLower: UByte = memory.readFrom(MOS6502.IRQ_IVT)
    val newPcUpper: UByte = memory.readFrom(MOS6502.IRQ_IVT + UShort(1))

    reg.PC = Bitwise.word(newPcLower, newPcUpper) - UShort(1)
  }
}

