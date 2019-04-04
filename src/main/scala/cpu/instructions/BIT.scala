package cpu.instructions

import cpu._
import spire.math.UByte

object BIT_ZP extends Instruction(UByte(0x24), 2, 3){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    val value: UByte = Addressing(AddressingType.ZP, memory, reg, args)
    reg.ZF = (value & reg.A) == UByte(0)
    reg.NF = (value & UByte(0x80)) == UByte(0x80) // NF = 7th bit
    reg.OF = (value & UByte(0x40)) == UByte(0x40) // OF = 6th bit
  }
}

object BIT_ABS extends Instruction(UByte(0x24), 3, 4){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    val value: UByte = Addressing(AddressingType.ABS, memory, reg, args)
    reg.ZF = (value & reg.A) == UByte(0)
    reg.NF = (value & UByte(0x80)) == UByte(0x80)
    reg.OF = (value & UByte(0x40)) == UByte(0x40)
  }
}