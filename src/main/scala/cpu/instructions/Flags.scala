package cpu.instructions

import cpu.{Instruction, MemoryMap, Reg6502}
import spire.math.UByte

object CLS extends Instruction(UByte(0x18), 1, 2){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = reg.CF = false
}

object CLD extends Instruction(UByte(0xD8), 1, 2){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = reg.DM = false
}

object CLI extends Instruction(UByte(0x58), 1, 2){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = reg.ID = false
}

object CLV extends Instruction(UByte(0xB8), 1, 2){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = reg.OF = false
}
