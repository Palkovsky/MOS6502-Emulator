package cpu.instructions

import cpu._
import spire.math.UByte

/**
  * DEX - 1 instruction
  */
object DEX extends Instruction(UByte(0xCA), 1, 2, AddressingType.IMPL){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    reg.X = reg.X - UByte(1)
    reg.updateZN(reg.X)
  }
}

/**
  * DEY - 1 instruction
  */
object DEY extends Instruction(UByte(0x88), 1, 2, AddressingType.IMPL){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    reg.Y = reg.Y - UByte(1)
    reg.updateZN(reg.Y)
  }
}

/**
  * DEC - 4 instructions
  */
trait DEC_Base extends Instruction{
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    val value: UByte = Addressing(addressing, memory, reg, args) - UByte(1)
    reg.updateZN(value)
    Addressing.write(addressing, memory, reg, args, value)
  }
}
object DEC_ZP extends Instruction(UByte(0xC6), 2, 5, AddressingType.ZP) with DEC_Base
object DEC_ZP_X extends Instruction(UByte(0xD6), 2, 6, AddressingType.ZP_X) with DEC_Base
object DEC_ABS extends Instruction(UByte(0xCE), 3, 6, AddressingType.ABS) with DEC_Base
object DEC_ABS_X extends Instruction(UByte(0xDE), 3, 7, AddressingType.ABS_X) with DEC_Base


