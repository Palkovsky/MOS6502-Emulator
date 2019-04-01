package cpu.instructions

import cpu._
import spire.math.UByte

/**
  * AND - 8 instructions
  */
object AND_Imm extends Instruction(UByte(0x29), 2, 2){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    reg.A = reg.A & args(0)
    reg.updateZN(reg.A)
  }
}

object AND_ZP extends Instruction(UByte(0x25), 2, 3){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    reg.A = reg.A & Addressing(AddressingType.ZP, memory, reg, args)
    reg.updateZN(reg.A)
  }
}

object AND_ZP_X extends Instruction(UByte(0x35), 2, 4){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    reg.A = reg.A & Addressing(AddressingType.ZP_X, memory, reg, args)
    reg.updateZN(reg.A)
  }
}

object AND_ABS extends Instruction(UByte(0x2D), 3, 4){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    reg.A = reg.A & Addressing(AddressingType.ABS, memory, reg, args)
    reg.updateZN(reg.A)
  }
}

object AND_ABS_X extends Instruction(UByte(0x3D), 3, 4){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit ={
    reg.A = reg.A & Addressing(AddressingType.ABS_X, memory, reg, args)
    reg.updateZN(reg.A)
  }
}

object AND_ABS_Y extends Instruction(UByte(0x39), 3, 4){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit ={
    reg.A = reg.A & Addressing(AddressingType.ABS_Y, memory, reg, args)
    reg.updateZN(reg.A)
  }
}

object AND_Indirect_X extends Instruction(UByte(0x21), 2, 6){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit ={
    reg.A = reg.A & Addressing(AddressingType.INDIRECT_X, memory, reg, args)
    reg.updateZN(reg.A)
  }
}

object AND_Indirect_Y extends Instruction(UByte(0x31), 2, 5){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit ={
    reg.A = reg.A & Addressing(AddressingType.INDIRECT_Y, memory, reg, args)
    reg.updateZN(reg.A)
  }
}