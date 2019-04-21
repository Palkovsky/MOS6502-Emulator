package cpu.instructions

import cpu._
import spire.math.UByte

/**
  * INC - 4 instructions
  */
object INC_ZP extends Instruction(UByte(0xE6), 2, 5){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    val value: UByte = Addressing(AddressingType.ZP, memory, reg, args) + UByte(1)
    reg.updateZN(value)
    Addressing.write(AddressingType.ZP, memory, reg, args, value)
  }
}

object INC_ZP_X extends Instruction(UByte(0xF6), 2, 6){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    val value: UByte = Addressing(AddressingType.ZP_X, memory, reg, args) + UByte(1)
    reg.updateZN(value)
    Addressing.write(AddressingType.ZP_X, memory, reg, args, value)
  }
}

object INC_ABS extends Instruction(UByte(0xEE), 3, 6){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    val value: UByte = Addressing(AddressingType.ABS, memory, reg, args) + UByte(1)
    reg.updateZN(value)
    Addressing.write(AddressingType.ABS, memory, reg, args, value)
  }
}

object INC_ABS_X extends Instruction(UByte(0xFE), 3, 7){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    val value: UByte = Addressing(AddressingType.ABS_X, memory, reg, args) + UByte(1)
    reg.updateZN(value)
    Addressing.write(AddressingType.ABS_X, memory, reg, args, value)
  }
}

/**
  * INX - 1 instruction
  */
object INX extends Instruction(UByte(0xE8), 1, 2){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    reg.X = reg.X + UByte(1)
    reg.updateZN(reg.X)
  }
}

/**
  * INY - 1 instruction
  */
object INY extends Instruction(UByte(0xC8), 1, 2){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    reg.Y = reg.Y + UByte(1)
    reg.updateZN(reg.Y)
  }
}