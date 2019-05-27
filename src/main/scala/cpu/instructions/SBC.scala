package cpu.instructions

import cpu._
import spire.math.UByte

/**
  * SBC - 8 instructions
  */
trait SBC_Base extends Instruction{
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit =
    reg.performSBC(Addressing(addressing, memory, reg, args))
}
object SBC_Imm extends Instruction(UByte(0xE9), 2, 2, AddressingType.IMM) with SBC_Base
object SBC_ZP extends Instruction(UByte(0xE5), 2, 3, AddressingType.ZP) with SBC_Base
object SBC_ZP_X extends Instruction(UByte(0xF5), 2, 4, AddressingType.ZP_X) with SBC_Base
object SBC_ABS extends Instruction(UByte(0xED), 3, 4, AddressingType.ABS) with SBC_Base
object SBC_ABS_X extends Instruction(UByte(0xFD), 3, 4, AddressingType.ABS_X) with SBC_Base
object SBC_ABS_Y extends Instruction(UByte(0xF9), 3, 4, AddressingType.ABS_Y) with SBC_Base
object SBC_Indirect_X extends Instruction(UByte(0xE1), 2, 6, AddressingType.INDIRECT_X) with SBC_Base
object SBC_Indirect_Y extends Instruction(UByte(0xF1), 2, 5, AddressingType.INDIRECT_Y) with SBC_Base
