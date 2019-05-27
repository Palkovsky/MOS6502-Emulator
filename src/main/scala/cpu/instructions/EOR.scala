package cpu.instructions

import cpu._
import spire.math.UByte

/**
  * EOR - 8 instructions
  */
trait EOR_Base extends Instruction{
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    val value: UByte = Addressing(addressing, memory, reg, args)
    reg.A = reg.A ^ value
    reg.updateZN(reg.A)
  }
}
object EOR_Imm extends Instruction(UByte(0x49), 2, 2, AddressingType.IMM) with EOR_Base
object EOR_ZP extends Instruction(UByte(0x45), 2, 3, AddressingType.ZP) with EOR_Base
object EOR_ZP_X extends Instruction(UByte(0x55), 2, 4, AddressingType.ZP_X) with EOR_Base
object EOR_ABS extends Instruction(UByte(0x4D), 3, 4, AddressingType.ABS) with EOR_Base
object EOR_ABS_X extends Instruction(UByte(0x5D), 3, 4, AddressingType.ABS_X) with EOR_Base
object EOR_ABS_Y extends Instruction(UByte(0x59), 3, 4, AddressingType.ABS_Y) with EOR_Base
object EOR_Indirect_X extends Instruction(UByte(0x41), 2, 6, AddressingType.INDIRECT_X) with EOR_Base
object EOR_Indirect_Y extends Instruction(UByte(0x51), 2, 5, AddressingType.INDIRECT_Y) with EOR_Base
