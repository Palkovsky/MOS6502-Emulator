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
    BCC, BCS, BNE, BEQ, BPL, BMI, BVC, BVS,
    CLS, CLD, CLV, CLD,
    CMP_Imm, CMP_ZP, CMP_ZP_X, CMP_ABS, CMP_ABS_X, CMP_ABS_Y, CMP_Indirect_X, CMP_Indirect_Y,
    CPX_Imm, CPX_ZP, CPX_ABS,
    CPY_Imm, CPY_ZP, CPY_ABS,
    DEC_ZP, DEC_ZP_X, DEC_ABS, DEC_ABS_X, DEX, DEY,
    INC_ZP, INC_ZP_X, INC_ABS, INC_ABS_X, INX, INY,
    EOR_Imm, EOR_ZP, EOR_ZP_X, EOR_ABS, EOR_ABS_X, EOR_ABS_Y, EOR_Indirect_X, EOR_Indirect_Y
  )

  val instructionsMap: Map[UByte, Instruction] = (instructions.map(inst => inst.opcode) zip instructions).toMap

  def lookup(opcode: UByte): Option[Instruction] = instructionsMap.get(opcode)
}

trait Executable {
  def execute(memory: MemoryMap, reg: Reg6502, args:UByte*): Unit
}

abstract class Instruction protected(val opcode: UByte, val size: Int, val cycles: Int) extends Executable



