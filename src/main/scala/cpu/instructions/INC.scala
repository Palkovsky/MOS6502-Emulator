package cpu.instructions

import cpu._
import spire.math.UByte

/**
  * INC - 4 instructions
  */
trait INC_Base extends Instruction{
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    val value: UByte = Addressing(addressing, memory, reg, args) + UByte(1)
    reg.updateZN(value)
    Addressing.write(addressing, memory, reg, args, value)
  }
}
object INC_ZP extends Instruction(UByte(0xE6), 2, 5, AddressingType.ZP) with INC_Base
object INC_ZP_X extends Instruction(UByte(0xF6), 2, 6, AddressingType.ZP_X) with INC_Base
object INC_ABS extends Instruction(UByte(0xEE), 3, 6, AddressingType.ABS) with INC_Base
object INC_ABS_X extends Instruction(UByte(0xFE), 3, 7, AddressingType.ABS_X) with INC_Base

/**
  * INX - 1 instruction
  */
object INX extends Instruction(UByte(0xE8), 1, 2, AddressingType.IMPL){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    reg.X = reg.X + UByte(1)
    reg.updateZN(reg.X)
  }
}

/**
  * INY - 1 instruction
  */
object INY extends Instruction(UByte(0xC8), 1, 2, AddressingType.IMPL){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    reg.Y = reg.Y + UByte(1)
    reg.updateZN(reg.Y)
  }
}
