package cpu.instructions

import cpu._
import spire.math.{UByte, UShort}

/**
  * JMP - 2 instructions
  */
object JMP_ABS extends Instruction(UByte(0x4c), 3, 3){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    val addr: UShort = (UShort(args(1).toInt) << 8) + UShort(args.head.toInt)
    reg.PC = addr - UShort(size)
  }
}

object JMP_Indirect extends Instruction(UByte(0x6c), 3, 5){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    val indirectAddr: UShort = (UShort(args(1).toInt) << 8) + UShort(args.head.toInt)
    val lower: UByte = memory.readFrom(indirectAddr)
    val upper: UByte = memory.readFrom(indirectAddr + UShort(1))
    val addr:  UShort = (UShort(upper.toInt) << 8) + UShort(lower.toInt)
    reg.PC = addr - UShort(size)
  }
}
