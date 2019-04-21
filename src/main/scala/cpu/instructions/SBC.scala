package cpu.instructions

import cpu._
import spire.math.UByte

/**
  * SBC - 8 instructions
  */
object SBC_Imm extends Instruction(UByte(0xE9), 2, 2){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit =
    reg.performSBC(args(0))
}

object SBC_ZP extends Instruction(UByte(0xE5), 2, 3){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit =
    reg.performSBC(Addressing(AddressingType.ZP, memory, reg, args))
}

object SBC_ZP_X extends Instruction(UByte(0xF5), 2, 4){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit =
    reg.performSBC(Addressing(AddressingType.ZP_X, memory, reg, args))
}

object SBC_ABS extends Instruction(UByte(0xED), 3, 4){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit =
    reg.performSBC(Addressing(AddressingType.ABS, memory, reg, args))
}

object SBC_ABS_X extends Instruction(UByte(0xFD), 3, 4){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit =
    reg.performSBC(Addressing(AddressingType.ABS_X, memory, reg, args))
}

object SBC_ABS_Y extends Instruction(UByte(0xF9), 3, 4){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit =
    reg.performSBC(Addressing(AddressingType.ABS_Y, memory, reg, args))
}

object SBC_Indirect_X extends Instruction(UByte(0xE1), 2, 6){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit =
    reg.performSBC(Addressing(AddressingType.INDIRECT_X, memory, reg, args))
}

object SBC_Indirect_Y extends Instruction(UByte(0xF1), 2, 5){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit =
    reg.performSBC(Addressing(AddressingType.INDIRECT_Y, memory, reg, args))
}