package cpu

import cpu.instructions.BRK
import spire.math.{UByte, UShort}

object MOS6502{
  def apply(memory: MemoryMap, codePtr: UShort) = new MOS6502(memory, codePtr)
}

class MOS6502 private(val memory: MemoryMap, val codePtr: UShort) extends Runnable{

  val registers: Reg6502 = Reg6502()
  registers.PC = codePtr

  private var cycles: Long = 0

  def run(): Unit = {
    while(true){
      val opcode:           UByte = memory.readFrom(registers.PC)
      val maybeInstruction: Option[Instruction] = InstructionSet.lookup(opcode)

      maybeInstruction match {
        case None              => throw new InvalidOpcodeException(s"Invalid opcode $opcode at $registers.PC")
        case Some(BRK)         => return
        case Some(instruction) =>
          val args: Seq[UByte] = for(i <- 1 until instruction.size) yield memory.readFrom(registers.PC + UShort(i))
          println(s"Executing ${instruction.opcode.toInt.toHexString} Args: $args")
          instruction.execute(memory, registers, args:_*)
          cycles       = cycles + instruction.cycles
          registers.PC = registers.PC + UShort(instruction.size)
      }
    }
  }
}