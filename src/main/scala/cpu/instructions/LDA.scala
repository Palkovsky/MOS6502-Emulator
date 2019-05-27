package cpu.instructions

import cpu._
import spire.math.UByte

/**
  * LDA - 8 instructions
  */
trait LDA_Base extends Instruction{
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    reg.A = Addressing(addressing, memory, reg, args)
    reg.updateZN(reg.A)
  }
}
object LDA_Imm extends Instruction(UByte(0xA9), 2, 2, AddressingType.IMM) with LDA_Base
object LDA_ZP extends Instruction(UByte(0xA5), 2, 3, AddressingType.ZP) with LDA_Base
object LDA_ZP_X extends Instruction(UByte(0xB5), 2, 4, AddressingType.ZP_X) with LDA_Base
object LDA_ABS extends Instruction(UByte(0xAD), 3, 4, AddressingType.ABS) with LDA_Base
object LDA_ABS_X extends Instruction(UByte(0xBD), 3, 4, AddressingType.ABS_X) with LDA_Base
object LDA_ABS_Y extends Instruction(UByte(0xB9), 3, 4, AddressingType.ABS_Y) with LDA_Base
object LDA_Indirect_X extends Instruction(UByte(0xA1), 2, 6, AddressingType.INDIRECT_X) with LDA_Base
object LDA_Indirect_Y extends Instruction(UByte(0xB1), 2, 5, AddressingType.INDIRECT_Y) with LDA_Base

/**
  * LDX - 5 instructions
  */
trait LDX_Base extends Instruction{
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    reg.X = Addressing(addressing, memory, reg, args)
    reg.updateZN(reg.X)
  }
}
object LDX_Imm extends Instruction(UByte(0xA2), 2, 2, AddressingType.IMM) with LDX_Base
object LDX_ZP extends Instruction(UByte(0xA6), 2, 3, AddressingType.ZP) with LDX_Base
object LDX_ZP_Y extends Instruction(UByte(0xB6), 2, 4, AddressingType.ZP_Y) with LDX_Base
object LDX_ABS extends Instruction(UByte(0xAE), 3, 4, AddressingType.ABS) with LDX_Base
object LDX_ABS_Y extends Instruction(UByte(0xBE), 3, 4, AddressingType.ABS_Y) with LDX_Base


/**
  * LDY - 5 instructions
  */
trait LDY_Base extends Instruction{
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    reg.Y = Addressing(addressing, memory, reg, args)
    reg.updateZN(reg.Y)
  }
}
object LDY_Imm extends Instruction(UByte(0xA0), 2, 2, AddressingType.IMM) with LDY_Base
object LDY_ZP extends Instruction(UByte(0xA4), 2, 3, AddressingType.ZP) with LDY_Base
object LDY_ZP_X extends Instruction(UByte(0xB4), 2, 4, AddressingType.ZP_X) with LDY_Base
object LDY_ABS extends Instruction(UByte(0xAC), 3, 4, AddressingType.ABS) with LDY_Base 
object LDY_ABS_X extends Instruction(UByte(0xBC), 3, 4, AddressingType.ABS_X) with LDY_Base
