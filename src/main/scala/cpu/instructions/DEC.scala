package cpu.instructions

import cpu._
import spire.math.UByte

/**
  * DEC - 4 instructions
  */
object DEC_ZP extends Instruction(UByte(0xC6), 2, 5){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    val value: UByte = Addressing(AddressingType.ZP, memory, reg, args) - UByte(1)
    reg.updateZN(value)
    Addressing.write(AddressingType.ZP, memory, reg, args, value)
  }
}

object DEC_ZP_X extends Instruction(UByte(0xD6), 2, 6){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    val value: UByte = Addressing(AddressingType.ZP_X, memory, reg, args) - UByte(1)
    reg.updateZN(value)
    Addressing.write(AddressingType.ZP_X, memory, reg, args, value)
  }
}

object DEC_ABS extends Instruction(UByte(0xCE), 3, 6){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    val value: UByte = Addressing(AddressingType.ABS, memory, reg, args) - UByte(1)
    reg.updateZN(value)
    Addressing.write(AddressingType.ABS, memory, reg, args, value)
  }
}

object DEC_ABS_X extends Instruction(UByte(0xDE), 3, 7){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    val value: UByte = Addressing(AddressingType.ABS_X, memory, reg, args) - UByte(1)
    reg.updateZN(value)
    Addressing.write(AddressingType.ABS_X, memory, reg, args, value)
  }
}

/**
  * DEX - 1 instruction
  */
object DEX extends Instruction(UByte(0xCA), 1, 2){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    reg.X = reg.X - UByte(1)
    reg.updateZN(reg.X)
  }
}

/**
  * DEY - 1 instruction
  */
object DEY extends Instruction(UByte(0x88), 1, 2){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    reg.Y = reg.Y - UByte(1)
    reg.updateZN(reg.Y)
  }
}