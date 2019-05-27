package cpu.instructions

import cpu._
import spire.math.UByte

/*
  CMP - 8 instructions
 */
trait CMP_Base extends Instruction{
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit =
    reg.updateCMP(reg.A, Addressing(addressing, memory, reg, args))
}
object CMP_Imm extends Instruction(UByte(0xC9), 2, 3, AddressingType.IMM) with CMP_Base
object CMP_ZP extends Instruction(UByte(0xC5), 2, 3, AddressingType.ZP) with CMP_Base
object CMP_ZP_X extends Instruction(UByte(0xD5), 2, 4, AddressingType.ZP_X) with CMP_Base
object CMP_ABS extends Instruction(UByte(0xCD), 3, 4, AddressingType.ABS) with CMP_Base
object CMP_ABS_X extends Instruction(UByte(0xDD), 3, 4, AddressingType.ABS_X) with CMP_Base
object CMP_ABS_Y extends Instruction(UByte(0xD9), 3, 4, AddressingType.ABS_Y) with CMP_Base
object CMP_Indirect_X extends Instruction(UByte(0xC1), 2, 6, AddressingType.INDIRECT_X) with CMP_Base
object CMP_Indirect_Y extends Instruction(UByte(0xD1), 2, 5, AddressingType.INDIRECT_Y) with CMP_Base

/*
  CPX - 3 instructions
 */
trait CPX_Base extends Instruction{
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit =
    reg.updateCMP(reg.X, Addressing(addressing, memory, reg, args))
}
object CPX_Imm extends Instruction(UByte(0xE0), 2, 3, AddressingType.IMM) with CPX_Base
object CPX_ZP extends Instruction(UByte(0xE4), 2, 3, AddressingType.ZP) with CPX_Base
object CPX_ABS extends Instruction(UByte(0xEC), 3, 4, AddressingType.ABS) with CPX_Base

/*
  CPY - 3 instructions
 */
trait CPY_Base extends Instruction{
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit =
    reg.updateCMP(reg.Y, Addressing(addressing, memory, reg, args))
}
object CPY_Imm extends Instruction(UByte(0xC0), 2, 3, AddressingType.IMM) with CPY_Base
object CPY_ZP extends Instruction(UByte(0xC4), 2, 3, AddressingType.ZP) with CPY_Base
object CPY_ABS extends Instruction(UByte(0xCC), 3, 4, AddressingType.ABS) with CPY_Base
