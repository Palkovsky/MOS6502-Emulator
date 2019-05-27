package cpu.instructions

import cpu._
import spire.math.UByte

/**
  * NOP - 1 instruction
  */
object NOP extends Instruction(UByte(0xEA), 1, 2, AddressingType.IMPL){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {

  }
}
