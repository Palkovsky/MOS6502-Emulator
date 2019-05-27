package cpu.instructions

import cpu._
import spire.math.UByte

/**
  * ORA - 8 instructions
  */
trait ORA_Base extends Instruction{
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    val value: UByte = Addressing(addressing, memory, reg, args)
    reg.A = reg.A | value
    reg.updateZN(reg.A)
  }
}
object ORA_Imm extends Instruction(UByte(0x09), 2, 2, AddressingType.IMM) with ORA_Base
object ORA_ZP extends Instruction(UByte(0x05), 2, 3, AddressingType.ZP) with ORA_Base
object ORA_ZP_X extends Instruction(UByte(0x15), 2, 4, AddressingType.ZP_X) with ORA_Base
object ORA_ABS extends Instruction(UByte(0x0D), 3, 4, AddressingType.ABS) with ORA_Base
object ORA_ABS_X extends Instruction(UByte(0x1D), 3, 4, AddressingType.ABS_X) with ORA_Base
object ORA_ABS_Y extends Instruction(UByte(0x19), 3, 4, AddressingType.ABS_Y) with ORA_Base
object ORA_Indirect_X extends Instruction(UByte(0x01), 2, 6, AddressingType.INDIRECT_X) with ORA_Base
object ORA_Indirect_Y extends Instruction(UByte(0x11), 2, 5, AddressingType.INDIRECT_Y) with ORA_Base
