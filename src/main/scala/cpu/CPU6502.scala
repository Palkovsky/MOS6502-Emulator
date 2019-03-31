package cpu

import spire.math.{UByte, UShort}

object CPU6502{
  def apply(memory: MemoryMap) = new CPU6502(memory)
}

class CPU6502 private(val memory: MemoryMap){
  val registers: Reg6502 = Reg6502()
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
          println(s"Executin ${instruction.opcode} Args: $args")
          instruction.execute(memory, registers, args:_*)
          cycles       = cycles + instruction.cycles
          registers.PC = registers.PC + UShort(instruction.size)
      }
    }
  }
}