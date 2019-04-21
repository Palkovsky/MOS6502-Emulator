package cpu.instructions

import cpu._
import spire.math.UByte

/**
  * EOR - 8 instructions
  */
object EOR_Imm extends Instruction(UByte(0x49), 2, 2){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    val value: UByte = args(0)
    reg.A = reg.A ^ value
    reg.updateZN(reg.A)
  }
}

object EOR_ZP extends Instruction(UByte(0x45), 2, 3){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    val value: UByte = Addressing(AddressingType.ZP, memory, reg, args)
    reg.A = reg.A ^ value
    reg.updateZN(reg.A)
  }
}

object EOR_ZP_X extends Instruction(UByte(0x55), 2, 4){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit ={
    val value: UByte = Addressing(AddressingType.ZP_X, memory, reg, args)
    reg.A = reg.A ^ value
    reg.updateZN(reg.A)
  }
}

object EOR_ABS extends Instruction(UByte(0x4D), 3, 4){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit ={
    val value: UByte = Addressing(AddressingType.ABS, memory, reg, args)
    reg.A = reg.A ^ value
    reg.updateZN(reg.A)
  }
}

object EOR_ABS_X extends Instruction(UByte(0x5D), 3, 4){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit ={
    val value: UByte = Addressing(AddressingType.ABS_X, memory, reg, args)
    reg.A = reg.A ^ value
    reg.updateZN(reg.A)
  }
}

object EOR_ABS_Y extends Instruction(UByte(0x59), 3, 4){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit ={
    val value: UByte = Addressing(AddressingType.ABS_Y, memory, reg, args)
    reg.A = reg.A ^ value
    reg.updateZN(reg.A)
  }
}

object EOR_Indirect_X extends Instruction(UByte(0x41), 2, 6){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit ={
    val value: UByte = Addressing(AddressingType.INDIRECT_X, memory, reg, args)
    reg.A = reg.A ^ value
    reg.updateZN(reg.A)
  }
}

object EOR_Indirect_Y extends Instruction(UByte(0x51), 2, 5){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit ={
    val value: UByte = Addressing(AddressingType.INDIRECT_Y, memory, reg, args)
    reg.A = reg.A ^ value
    reg.updateZN(reg.A)
  }
}