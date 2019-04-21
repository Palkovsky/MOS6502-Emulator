package cpu.instructions

import cpu.{Instruction, MemoryMap, Reg6502}
import spire.math.UByte

/*
  Docs says that these operations update ZN flags. Don't know how.
 */

// A ==> X
object TAX extends Instruction(UByte(0xAA), 1, 2){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    reg.X = reg.A
  }
}

// X ==> A
object TXA extends Instruction(UByte(0x8A), 1, 2){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit =  {
    reg.A = reg.X
  }
}

// A ==> Y
object TAY extends Instruction(UByte(0xA8), 1, 2){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit =  {
    reg.Y = reg.A
  }
}

// Y ==> A
object TYA extends Instruction(UByte(0x98), 1, 2){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    reg.A = reg.Y
  }
}

// S ==> X
object TSX extends Instruction(UByte(0xBA), 1, 2){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    reg.X = reg.SP
  }
}

// X ==> S
object TXS extends Instruction(UByte(0x9A), 1, 2){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    reg.SP = reg.X
  }
}
