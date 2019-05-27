package cpu.instructions

import cpu._
import spire.math.UByte

/**
  * ADC - 8 instructions
  */
abstract trait ADC_Base extends Instruction{
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit =
    reg.performADC(Addressing(addressing, memory, reg, args))
}
object ADC_Imm extends Instruction(UByte(0x69), 2, 2, AddressingType.IMM) with ADC_Base
object ADC_ZP extends Instruction(UByte(0x65), 2, 3, AddressingType.ZP) with ADC_Base
object ADC_ZP_X extends Instruction(UByte(0x75), 2, 4, AddressingType.ZP_X) with ADC_Base
object ADC_ABS extends Instruction(UByte(0x6D), 3, 4, AddressingType.ABS) with ADC_Base
object ADC_ABS_X extends Instruction(UByte(0x7D), 3, 4, AddressingType.ABS_X) with ADC_Base
object ADC_ABS_Y extends Instruction(UByte(0x79), 3, 4, AddressingType.ABS_Y) with ADC_Base
object ADC_Indirect_X extends Instruction(UByte(0x61), 2, 6, AddressingType.INDIRECT_X) with ADC_Base
object ADC_Indirect_Y extends Instruction(UByte(0x71), 2, 5, AddressingType.INDIRECT_Y) with ADC_Base
