package cpu

import spire.math.UByte

class InvalidOpcodeException(msg: String) extends Exception(msg)

object InstructionSet {
  val instructions: Seq[Instruction] = List(
    ADC_Imm, ADC_ZP, ADC_ZP_X, ADC_ABS, ADC_ABS_X, ADC_ABS_Y, ADC_Indirect_X, ADC_Indirect_Y,
    BRK
  )

  val instructionsMap: Map[UByte, Instruction] = (instructions.map(inst => inst.opcode) zip instructions).toMap

  def lookup(opcode: UByte): Option[Instruction] = instructionsMap.get(opcode)
}

trait Executable {
  def execute(memory: MemoryMap, reg: Reg6502, args:UByte*): Unit
}

abstract class Instruction protected(val opcode: UByte, val size: Int, val cycles: Int) extends Executable

/**
  * ADC - 8 instructions
  */
object ADC_Imm extends Instruction(UByte(0x69), 2, 2){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = reg.A = reg.A + args(0)
}
object ADC_ZP extends Instruction(UByte(0x65), 2, 3){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = reg.A = reg.A + Addressing(AddressingType.ZP_Y, memory, reg, args)
}
object ADC_ZP_X extends Instruction(UByte(0x75), 2, 4){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = reg.A = reg.A + Addressing(AddressingType.ZP_X, memory, reg, args)
}
object ADC_ABS extends Instruction(UByte(0x6D), 3, 4){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = reg.A = reg.A + Addressing(AddressingType.ABS, memory, reg, args)
}
object ADC_ABS_X extends Instruction(UByte(0x7D), 3, 4){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = reg.A = reg.A + Addressing(AddressingType.ABS_X, memory, reg, args)
}
object ADC_ABS_Y extends Instruction(UByte(0x79), 3, 4){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = reg.A = reg.A + Addressing(AddressingType.ABS_Y, memory, reg, args)
}
object ADC_Indirect_X extends Instruction(UByte(0x61), 2, 6){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = reg.A = reg.A + Addressing(AddressingType.INDIRECT_X, memory, reg, args)
}
object ADC_Indirect_Y extends Instruction(UByte(0x71), 2, 5){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = reg.A = reg.A + Addressing(AddressingType.INDIRECT_Y, memory, reg, args)
}

object BRK extends Instruction(UByte(0x00), 1, 7){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = Unit
}

