package cpu.instructions

import cpu._
import spire.math.UByte

/**
  * AND - 8 instructions
  */
trait AND_Base extends Instruction{
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    reg.A = reg.A &  Addressing(addressing, memory, reg, args)
    reg.updateZN(reg.A)
  }
}
object AND_Imm extends Instruction(UByte(0x29), 2, 2, AddressingType.IMM) with AND_Base
object AND_ZP extends Instruction(UByte(0x25), 2, 3, AddressingType.ZP) with AND_Base
object AND_ZP_X extends Instruction(UByte(0x35), 2, 4, AddressingType.ZP_X) with AND_Base
object AND_ABS extends Instruction(UByte(0x2D), 3, 4, AddressingType.ABS) with AND_Base
object AND_ABS_X extends Instruction(UByte(0x3D), 3, 4, AddressingType.ABS_X) with AND_Base
object AND_ABS_Y extends Instruction(UByte(0x39), 3, 4, AddressingType.ABS_Y) with AND_Base
object AND_Indirect_X extends Instruction(UByte(0x21), 2, 6, AddressingType.INDIRECT_X) with AND_Base
object AND_Indirect_Y extends Instruction(UByte(0x31), 2, 5, AddressingType.INDIRECT_Y) with AND_Base
