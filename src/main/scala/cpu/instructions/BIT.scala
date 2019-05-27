package cpu.instructions

import cpu._
import spire.math.UByte

/*
 * BIT - 2 instructions
 */
trait BIT_Base extends Instruction{
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    val value: UByte = Addressing(addressing, memory, reg, args)
    reg.ZF = (value & reg.A) == UByte(0)
    reg.NF = (value & UByte(0x80)) == UByte(0x80) // NF = 7th bit
    reg.OF = (value & UByte(0x40)) == UByte(0x40) // OF = 6th bit
  }
}
object BIT_ZP extends Instruction(UByte(0x24), 2, 3, AddressingType.ZP) with BIT_Base
object BIT_ABS extends Instruction(UByte(0x2C), 3, 4, AddressingType.ABS) with BIT_Base
