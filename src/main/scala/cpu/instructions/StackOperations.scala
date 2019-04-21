package cpu.instructions

import cpu.{Instruction, MemoryMap, Reg6502}
import spire.math.UByte

// Push accumulator on stack
object PHA extends Instruction(UByte(0x48), 1, 3){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    memory.writeTo(reg.SP, reg.A)
    reg.SP -= UByte(1)
  }
}

// Pull accumulator from stack
object PLA extends Instruction(UByte(0x68), 1, 4){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit =  {
    reg.SP += UByte(1)
    reg.A = memory.readFrom(reg.SP)
  }
}

//  Push processor status register on stack
object PHP extends Instruction(UByte(0x08), 1, 3){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit =  {
    memory.writeTo(reg.SP, reg.status())
    reg.SP -= UByte(1)
  }
}

