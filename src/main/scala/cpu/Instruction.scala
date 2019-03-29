package cpu

class InvalidOpcodeException(msg: String) extends Exception(msg)

object InstructionSet {
  val instructions: Seq[Instruction] = List(
    ADC_Imm,
    ADC_ZP,
    ADC_ZP_X,
    ADC_ABS,
    ADC_ABS_X,
    ADC_ABS_Y,
    ADC_Indirect_X,
    ADC_Indirect_Y,

    BRK
  )

  val instructionsMap: Map[Byte, Instruction] = (instructions.map(inst => inst.opcode) zip instructions).toMap

  def lookup(opcode: Byte): Option[Instruction] = instructionsMap.get(opcode)
}

trait Executable {
  def execute(memory: MemoryMap, reg: Reg6502, args:Byte*): Unit
}

abstract class Instruction protected(val opcode: Byte, val size: Int, val cycles: Int) extends Executable

/**
  * ADC - 8 different instructions
  */
object ADC_Imm extends Instruction(0x69, 2, 2){
  override def execute(memory: MemoryMap, reg: Reg6502, args: Byte*): Unit = reg.A = reg.A + args(0)
}
object ADC_ZP extends Instruction(0x65, 2, 3){
  override def execute(memory: MemoryMap, reg: Reg6502, args: Byte*): Unit = reg.A = reg.A + memory.readFrom(args(0))
}
object ADC_ZP_X extends Instruction(0x75, 2, 4){
  override def execute(memory: MemoryMap, reg: Reg6502, args: Byte*): Unit = reg.A = reg.A + memory.readFrom(args(0) + reg.X)
}
object ADC_ABS extends Instruction(0x6D, 3, 4){
  override def execute(memory: MemoryMap, reg: Reg6502, args: Byte*): Unit = {
    val addr: Int = args(1) << 8 + args(0)
    reg.A = reg.A + memory.readFrom(addr)
  }
}
object ADC_ABS_X extends Instruction(0x7D, 3, 4){
  override def execute(memory: MemoryMap, reg: Reg6502, args: Byte*): Unit = {
    val addr: Int = args(1) << 8 + args(0) + reg.X
    reg.A = reg.A + memory.readFrom(addr)
  }
}
object ADC_ABS_Y extends Instruction(0x79, 3, 4){
  override def execute(memory: MemoryMap, reg: Reg6502, args: Byte*): Unit = {
    val addr: Int = args(1) << 8 + args(0) + reg.Y
    reg.A = reg.A + memory.readFrom(addr)
  }
}
object ADC_Indirect_X extends Instruction(0x61, 2, 6){
  override def execute(memory: MemoryMap, reg: Reg6502, args: Byte*): Unit = {
    val lower: Byte = memory.readFrom(reg.X + args(0))
    val upper: Byte = memory.readFrom(reg.X + args(0) + 1)
    val addr:  Int  = upper << 8 + lower
    reg.A = reg.A + memory.readFrom(addr)
  }
}
object ADC_Indirect_Y extends Instruction(0x71, 2, 5){
  override def execute(memory: MemoryMap, reg: Reg6502, args: Byte*): Unit = {
    val lower: Byte = memory.readFrom(args(0))
    val upper: Byte = memory.readFrom(args(0) + 1)
    val addr:  Int  = upper << 8 + lower + reg.Y
    reg.A = reg.A + memory.readFrom(addr)
  }
}

object BRK extends Instruction(0x00, 1, 7){
  override def execute(memory: MemoryMap, reg: Reg6502, args: Byte*): Unit = Unit
}

