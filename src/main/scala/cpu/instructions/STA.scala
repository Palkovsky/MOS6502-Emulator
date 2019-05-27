package cpu.instructions

import cpu._
import spire.math.UByte

/**
  * STA - 8 instructions
  */
trait STA_Base extends Instruction{
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit =
    Addressing.write(addressing, memory, reg, args, reg.A)
}
object STA_ZP extends Instruction(UByte(0x85), 2, 3, AddressingType.ZP) with STA_Base
object STA_ZP_X extends Instruction(UByte(0x95), 2, 4, AddressingType.ZP_X) with STA_Base
object STA_ABS extends Instruction(UByte(0x8D), 3, 4, AddressingType.ABS) with STA_Base
object STA_ABS_X extends Instruction(UByte(0x9D), 3, 5, AddressingType.ABS_X) with STA_Base
object STA_ABS_Y extends Instruction(UByte(0x99), 3, 5, AddressingType.ABS_Y) with STA_Base
object STA_Indirect_X extends Instruction(UByte(0x81), 2, 6, AddressingType.INDIRECT_X) with STA_Base
object STA_Indirect_Y extends Instruction(UByte(0x91), 2, 6, AddressingType.INDIRECT_Y) with STA_Base

/**
  * STX - 3 instructions
  */
trait STX_Base extends Instruction{
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit =
    Addressing.write(addressing, memory, reg, args, reg.X)
}
object STX_ZP extends Instruction(UByte(0x86), 2, 3, AddressingType.ZP) with STX_Base
object STX_ZP_Y extends Instruction(UByte(0x96), 2, 4, AddressingType.ZP_Y) with STX_Base
object STX_ABS extends Instruction(UByte(0x8E), 3, 4, AddressingType.ABS) with STX_Base

/**
  * STY - 3 instructions
  */
trait STY_Base extends Instruction{
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit =
    Addressing.write(addressing, memory, reg, args, reg.Y)
}
object STY_ZP extends Instruction(UByte(0x84), 2, 3, AddressingType.ZP) with STY_Base
object STY_ZP_X extends Instruction(UByte(0x94), 2, 4, AddressingType.ZP_X) with STY_Base
object STY_ABS extends Instruction(UByte(0x8C), 3, 4, AddressingType.ABS) with STY_Base
