package cpu.instructions

import cpu._
import spire.math.UByte

/**
  * ADC - 8 instructions
  */
object ADC_Imm extends Instruction(UByte(0x69), 2, 2){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit =
    reg.addA(args(0))
}
object ADC_ZP extends Instruction(UByte(0x65), 2, 3){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit =
    reg.addA(Addressing(AddressingType.ZP_Y, memory, reg, args))
}
object ADC_ZP_X extends Instruction(UByte(0x75), 2, 4){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit =
    reg.addA(Addressing(AddressingType.ZP_X, memory, reg, args))
}
object ADC_ABS extends Instruction(UByte(0x6D), 3, 4){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit =
    reg.addA(Addressing(AddressingType.ABS, memory, reg, args))
}
object ADC_ABS_X extends Instruction(UByte(0x7D), 3, 4){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit =
    reg.addA(Addressing(AddressingType.ABS_X, memory, reg, args))
}
object ADC_ABS_Y extends Instruction(UByte(0x79), 3, 4){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit =
    reg.addA(Addressing(AddressingType.ABS_Y, memory, reg, args))
}
object ADC_Indirect_X extends Instruction(UByte(0x61), 2, 6){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit =
    reg.addA(Addressing(AddressingType.INDIRECT_X, memory, reg, args))
}
object ADC_Indirect_Y extends Instruction(UByte(0x71), 2, 5){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit =
    reg.addA(Addressing(AddressingType.INDIRECT_Y, memory, reg, args))
}