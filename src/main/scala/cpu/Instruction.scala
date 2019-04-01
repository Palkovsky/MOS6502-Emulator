package cpu

import cpu.instructions._
import spire.math.UByte

class InvalidOpcodeException(msg: String) extends Exception(msg)

object InstructionSet {
  val instructions: Seq[Instruction] = List(
    ADC_Imm, ADC_ZP, ADC_ZP_X, ADC_ABS, ADC_ABS_X, ADC_ABS_Y, ADC_Indirect_X, ADC_Indirect_Y,
    AND_Imm, AND_ZP, AND_ZP_X, AND_ABS, AND_ABS_X, AND_ABS_Y, AND_Indirect_X, AND_Indirect_Y,
    ASL_Acc, ASL_ZP, ASL_ZP_X, ASL_ABS, ASL_ABS_X,
    BRK,
    BCC, BCS, BNE, BEQ, BPL, BMI, BVC, BVS
  )

  val instructionsMap: Map[UByte, Instruction] = (instructions.map(inst => inst.opcode) zip instructions).toMap

  def lookup(opcode: UByte): Option[Instruction] = instructionsMap.get(opcode)
}

trait Executable {
  def execute(memory: MemoryMap, reg: Reg6502, args:UByte*): Unit
}

abstract class Instruction protected(val opcode: UByte, val size: Int, val cycles: Int) extends Executable



