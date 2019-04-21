package cpu.instructions

import cpu._
import spire.math.UByte

/**
  * STA - 8 instructions
  */
object STA_ZP extends Instruction(UByte(0x85), 2, 3){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit =
    Addressing.write(AddressingType.ZP, memory, reg, args, reg.A)
}

object STA_ZP_X extends Instruction(UByte(0x95), 2, 4){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit =
    Addressing.write(AddressingType.ZP_X, memory, reg, args, reg.A)
}

object STA_ABS extends Instruction(UByte(0x8D), 3, 4){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit =
    Addressing.write(AddressingType.ABS, memory, reg, args, reg.A)
}

object STA_ABS_X extends Instruction(UByte(0x9D), 3, 5){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit =
    Addressing.write(AddressingType.ABS_X, memory, reg, args, reg.A)
}

object STA_ABS_Y extends Instruction(UByte(0x99), 3, 5){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit =
    Addressing.write(AddressingType.ABS_Y, memory, reg, args, reg.A)
}

object STA_Indirect_X extends Instruction(UByte(0x81), 2, 6){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit =
    Addressing.write(AddressingType.INDIRECT_X, memory, reg, args, reg.A)
}

object STA_Indirect_Y extends Instruction(UByte(0x91), 2, 6){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit =
    Addressing.write(AddressingType.INDIRECT_Y, memory, reg, args, reg.A)
}

/**
  * STX - 3 instructions
  */
object STX_ZP extends Instruction(UByte(0x86), 2, 3){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit =
    Addressing.write(AddressingType.ZP, memory, reg, args, reg.X)
}

object STX_ZP_Y extends Instruction(UByte(0x96), 2, 4){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit =
    Addressing.write(AddressingType.ZP_X, memory, reg, args, reg.X)
}

object STX_ABS extends Instruction(UByte(0x8E), 3, 4){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit =
    Addressing.write(AddressingType.ABS, memory, reg, args, reg.X)
}

/**
  * STY - 3 instructions
  */
object STY_ZP extends Instruction(UByte(0x84), 2, 3){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit =
    Addressing.write(AddressingType.ZP, memory, reg, args, reg.Y)
}

object STY_ZP_Y extends Instruction(UByte(0x94), 2, 4){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit =
    Addressing.write(AddressingType.ZP_X, memory, reg, args, reg.Y)
}

object STY_ABS extends Instruction(UByte(0x8C), 3, 4){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit =
    Addressing.write(AddressingType.ABS, memory, reg, args, reg.Y)
}