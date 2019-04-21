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
    SEI, SED, SEC,
    CMP_Imm, CMP_ZP, CMP_ZP_X, CMP_ABS, CMP_ABS_X, CMP_ABS_Y, CMP_Indirect_X, CMP_Indirect_Y,
    CPX_Imm, CPX_ZP, CPX_ABS,
    CPY_Imm, CPY_ZP, CPY_ABS,
    DEC_ZP, DEC_ZP_X, DEC_ABS, DEC_ABS_X, DEX, DEY,
    INC_ZP, INC_ZP_X, INC_ABS, INC_ABS_X, INX, INY,
    ORA_Imm, ORA_ZP, ORA_ZP_X, ORA_ABS, ORA_ABS_X, ORA_ABS_Y, ORA_Indirect_X, ORA_Indirect_Y,
    EOR_Imm, EOR_ZP, EOR_ZP_X, EOR_ABS, EOR_ABS_X, EOR_ABS_Y, EOR_Indirect_X, EOR_Indirect_Y,
    JMP_ABS, JMP_Indirect,
    JSR, RTS, RTI,
    PHA, PLA, PHP,
    NOP,
    TAX, TXA, TAY, TYA, TXS, TSX,
    LDA_Imm, LDA_ZP, LDA_ZP_X, LDA_ABS, LDA_ABS_X, LDA_ABS_Y, LDA_Indirect_X, LDA_Indirect_Y,
    LDX_Imm, LDX_ZP, LDX_ZP_Y, LDX_ABS, LDX_ABS_Y,
    LDY_Imm, LDY_ZP, LDY_ZP_X, LDY_ABS, LDY_ABS_X,
    STA_ZP, STA_ZP_X, STA_ABS, STA_ABS_X, STA_ABS_Y, STA_Indirect_X, STA_Indirect_Y,
    STX_ZP, STX_ZP_Y, STX_ABS,
    STY_ZP, STY_ZP_X, STY_ABS
  )

  val instructionsMap: Map[UByte, Instruction] = (instructions.map(inst => inst.opcode) zip instructions).toMap

  def lookup(opcode: UByte): Option[Instruction] = instructionsMap.get(opcode)
}

trait Executable {
  def execute(memory: MemoryMap, reg: Reg6502, args:UByte*): Unit
}

abstract class Instruction protected(val opcode: UByte, val size: Int, val cycles: Int) extends Executable



