package cpu.instructions

import cpu.{Instruction, MemoryMap, Reg6502}
import spire.math.{UByte, UShort}

/**
  * Branching instructions - BCC, BCS, BNE, BEQ, BPL, BMI, BVC, BVS
  */
object BCC extends Instruction(UByte(0x90), 2, 2){ // if CF == 0
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit =
    if(!reg.CF) reg.PC = UShort(reg.PC.toInt + args(0).signed - 1)
}
object BCS extends Instruction(UByte(0xB0), 2, 2){ // if CF == 1
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit =
    if(reg.CF) reg.PC = UShort(reg.PC.toInt + args(0).signed - 1)
}
object BNE extends Instruction(UByte(0xD0), 2, 2){ // if ZF == 0
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit =
    if(!reg.ZF) reg.PC = UShort(reg.PC.toInt + args(0).signed - 1)
}
object BEQ extends Instruction(UByte(0xF0), 2, 2){ // if ZF == 1
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit =
    if(reg.ZF) reg.PC = UShort(reg.PC.toInt + args(0).signed - 1)
}
object BPL extends Instruction(UByte(0x10), 2, 2){ // if NF == 0
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit =
    if(!reg.NF) reg.PC = UShort(reg.PC.toInt + args(0).signed - 1)
}
object BMI extends Instruction(UByte(0x30), 2, 2){ // if NF == 1
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit =
    if(reg.NF) reg.PC = UShort(reg.PC.toInt + args(0).signed - 1)
}
object BVC extends Instruction(UByte(0x50), 2, 2){ // if OF == 0
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit =
    if(!reg.OF) reg.PC = UShort(reg.PC.toInt + args(0).signed - 1)
}
object BVS extends Instruction(UByte(0x70), 2, 2){ // if OF == 1
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit =
    if(reg.OF) reg.PC = UShort(reg.PC.toInt + args(0).signed - 1)
}
