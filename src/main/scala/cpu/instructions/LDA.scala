package cpu.instructions

import cpu._
import spire.math.UByte

/**
  * LDA - 8 instructions
  */
object LDA_Imm extends Instruction(UByte(0xA9), 2, 2){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    reg.A = args(0)
    reg.updateZN(reg.A)
  }
}

object LDA_ZP extends Instruction(UByte(0xA5), 2, 3){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    reg.A = Addressing(AddressingType.ZP, memory, reg, args)
    reg.updateZN(reg.A)
  }
}

object LDA_ZP_X extends Instruction(UByte(0xB5), 2, 4){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    reg.A = Addressing(AddressingType.ZP_X, memory, reg, args)
    reg.updateZN(reg.A)
  }
}

object LDA_ABS extends Instruction(UByte(0xAD), 3, 4){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    reg.A = Addressing(AddressingType.ABS, memory, reg, args)
    reg.updateZN(reg.A)
  }
}

object LDA_ABS_X extends Instruction(UByte(0xBD), 3, 4){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    reg.A = Addressing(AddressingType.ABS_X, memory, reg, args)
    reg.updateZN(reg.A)
  }
}

object LDA_ABS_Y extends Instruction(UByte(0xB9), 3, 4){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    reg.A = Addressing(AddressingType.ABS_Y, memory, reg, args)
    reg.updateZN(reg.A)
  }
}

object LDA_Indirect_X extends Instruction(UByte(0xA1), 2, 6){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    reg.A = Addressing(AddressingType.INDIRECT_X, memory, reg, args)
    reg.updateZN(reg.A)
  }
}

object LDA_Indirect_Y extends Instruction(UByte(0xB1), 2, 5){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    reg.A = Addressing(AddressingType.INDIRECT_Y, memory, reg, args)
    reg.updateZN(reg.A)
  }
}

/**
  * LDX - 5 instructions
  */
object LDX_Imm extends Instruction(UByte(0xA2), 2, 2){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    reg.X = args(0)
    reg.updateZN(reg.X)
  }
}

object LDX_ZP extends Instruction(UByte(0xA6), 2, 3){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    reg.X = Addressing(AddressingType.ZP, memory, reg, args)
    reg.updateZN(reg.X)
  }
}

object LDX_ZP_Y extends Instruction(UByte(0xB6), 2, 4){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    reg.X = Addressing(AddressingType.ZP_Y, memory, reg, args)
    reg.updateZN(reg.X)
  }
}

object LDX_ABS extends Instruction(UByte(0xAE), 3, 4){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    reg.X = Addressing(AddressingType.ABS, memory, reg, args)
    reg.updateZN(reg.X)
  }
}

object LDX_ABS_Y extends Instruction(UByte(0xBE), 3, 4){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    reg.X = Addressing(AddressingType.ABS_Y, memory, reg, args)
    reg.updateZN(reg.X)
  }
}


/**
  * LDY - 5 instructions
  */
object LDY_Imm extends Instruction(UByte(0xA0), 2, 2){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    reg.Y = args(0)
    reg.updateZN(reg.Y)
  }
}

object LDY_ZP extends Instruction(UByte(0xA4), 2, 3){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    reg.Y = Addressing(AddressingType.ZP, memory, reg, args)
    reg.updateZN(reg.Y)
  }
}

object LDY_ZP_X extends Instruction(UByte(0xB4), 2, 4){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    reg.Y = Addressing(AddressingType.ZP_X, memory, reg, args)
    reg.updateZN(reg.Y)
  }
}

object LDY_ABS extends Instruction(UByte(0xAC), 3, 4){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    reg.Y = Addressing(AddressingType.ABS, memory, reg, args)
    reg.updateZN(reg.Y)
  }
}

object LDY_ABS_X extends Instruction(UByte(0xBC), 3, 4){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    reg.Y = Addressing(AddressingType.ABS_X, memory, reg, args)
    reg.updateZN(reg.Y)
  }
}
