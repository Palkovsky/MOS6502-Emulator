package cpu.instructions

import cpu._
import spire.math.UByte

/**
  * ORA - 8 instructions
  */
object ORA_Imm extends Instruction(UByte(0x09), 2, 2){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    val value: UByte = args(0)
    reg.A = reg.A | value
    reg.updateZN(reg.A)
  }
}

object ORA_ZP extends Instruction(UByte(0x05), 2, 3){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    val value: UByte = Addressing(AddressingType.ZP, memory, reg, args)
    reg.A = reg.A | value
    reg.updateZN(reg.A)
  }
}

object ORA_ZP_X extends Instruction(UByte(0x15), 2, 4){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit ={
    val value: UByte = Addressing(AddressingType.ZP_X, memory, reg, args)
    reg.A = reg.A | value
    reg.updateZN(reg.A)
  }
}

object ORA_ABS extends Instruction(UByte(0x0D), 3, 4){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit ={
    val value: UByte = Addressing(AddressingType.ABS, memory, reg, args)
    reg.A = reg.A | value
    reg.updateZN(reg.A)
  }
}

object ORA_ABS_X extends Instruction(UByte(0x1D), 3, 4){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit ={
    val value: UByte = Addressing(AddressingType.ABS_X, memory, reg, args)
    reg.A = reg.A | value
    reg.updateZN(reg.A)
  }
}

object ORA_ABS_Y extends Instruction(UByte(0x19), 3, 4){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit ={
    val value: UByte = Addressing(AddressingType.ABS_Y, memory, reg, args)
    reg.A = reg.A | value
    reg.updateZN(reg.A)
  }
}

object ORA_Indirect_X extends Instruction(UByte(0x01), 2, 6){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit ={
    val value: UByte = Addressing(AddressingType.INDIRECT_X, memory, reg, args)
    reg.A = reg.A | value
    reg.updateZN(reg.A)
  }
}

object ORA_Indirect_Y extends Instruction(UByte(0x11), 2, 5){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit ={
    val value: UByte = Addressing(AddressingType.INDIRECT_Y, memory, reg, args)
    reg.A = reg.A | value
    reg.updateZN(reg.A)
  }
}