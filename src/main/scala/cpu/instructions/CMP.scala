package cpu.instructions

import cpu._
import spire.math.UByte

/*
  CMP - 8 instructions
 */
object CMP_Imm extends Instruction(UByte(0xC9), 2, 3){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit =
    reg.updateCMP(reg.A, args(0))
}

object CMP_ZP extends Instruction(UByte(0xC5), 2, 3){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit =
    reg.updateCMP(reg.A, Addressing(AddressingType.ZP, memory, reg, args))
}

object CMP_ZP_X extends Instruction(UByte(0xD5), 2, 4){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit =
    reg.updateCMP(reg.A, Addressing(AddressingType.ZP_X, memory, reg, args))
}

object CMP_ABS extends Instruction(UByte(0xCD), 3, 4){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit =
    reg.updateCMP(reg.A, Addressing(AddressingType.ABS, memory, reg, args))
}

object CMP_ABS_X extends Instruction(UByte(0xDD), 3, 4){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit =
    reg.updateCMP(reg.A, Addressing(AddressingType.ABS_X, memory, reg, args))
}

object CMP_ABS_Y extends Instruction(UByte(0xD9), 3, 4){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit =
    reg.updateCMP(reg.A, Addressing(AddressingType.ABS_Y, memory, reg, args))
}

object CMP_Indirect_X extends Instruction(UByte(0xC1), 2, 6){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit =
    reg.updateCMP(reg.A, Addressing(AddressingType.INDIRECT_X, memory, reg, args))
}

object CMP_Indirect_Y extends Instruction(UByte(0xD1), 2, 5){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit =
    reg.updateCMP(reg.A, Addressing(AddressingType.INDIRECT_Y, memory, reg, args))
}

/*
  CPX - 3 instructions
*/
object CPX_Imm extends Instruction(UByte(0xE0), 2, 3){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit =
    reg.updateCMP(reg.X, args(0))
}

object CPX_ZP extends Instruction(UByte(0xE4), 2, 3){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit =
    reg.updateCMP(reg.X, Addressing(AddressingType.ZP, memory, reg, args))
}

object CPX_ABS extends Instruction(UByte(0xEC), 3, 4){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit =
    reg.updateCMP(reg.X, Addressing(AddressingType.ABS, memory, reg, args))
}

/*
  CPY - 3 instructions
*/
object CPY_Imm extends Instruction(UByte(0xC0), 2, 3){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit =
    reg.updateCMP(reg.Y, args(0))
}

object CPY_ZP extends Instruction(UByte(0xC4), 2, 3){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit =
    reg.updateCMP(reg.Y, Addressing(AddressingType.ZP, memory, reg, args))
}

object CPY_ABS extends Instruction(UByte(0xCC), 3, 4){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit =
    reg.updateCMP(reg.Y, Addressing(AddressingType.ABS, memory, reg, args))
}
