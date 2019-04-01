package cpu.instructions

import cpu.{Instruction, MemoryMap, Reg6502}
import spire.math.UByte

object BRK extends Instruction(UByte(0x00), 1, 7){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = Unit
}

