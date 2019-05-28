package cpu

import instructions.BRK
import helpers.{Bitwise, Logger}
import spire.math.{UByte, UShort}

object MOS6502{
  val IRQ_IVT: UShort = UShort(0xFFFE)
  val RST_IVT: UShort = UShort(0xFFFC)
  val NMI_IVT: UShort = UShort(0xFFFA)

  def apply(memory: MemoryMap, codePtr: UShort) = new MOS6502(memory, codePtr)
}

class MOS6502 private(memory: MemoryMap, codePtr: UShort) extends Runnable{

  private val reg: Reg6502 = Reg6502()
  reg.PC = codePtr

  private var cycles: Long = 0
  private var irqFlag: Boolean = false
  private var nmiFlag: Boolean = false
  private var rstFlag: Boolean = false
  private var haltFlag: Boolean = false

  private val logger: Logger = Logger.empty()

  def run(): Unit = {
    while(true){
      if(!haltFlag){
        val opcode: UByte = memory.readFrom(reg.PC)
        val maybeInstruction: Option[Instruction] = InstructionSet.lookup(opcode)

        maybeInstruction match {
          case None              => throw new InvalidOpcodeException(s"Invalid opcode 0x${opcode.toInt.toHexString} at 0x${reg.PC.toInt.toHexString}")
          case Some(instruction) =>
            val args: Seq[UByte] = for(i <- 1 until instruction.size) yield memory.readFrom(reg.PC + UShort(i))

            logger(s"Executing ${instruction.opcode.toInt.toHexString}(0x${reg.PC.toInt.toHexString}) Args: $args")

            instruction.execute(memory, reg, args:_*)
            cycles = cycles + instruction.cycles
            reg.PC = reg.PC + UShort(instruction.size)
        }

        // Check for non-maskable interrupt
        if(nmiFlag){
          handleInterrupt(MOS6502.NMI_IVT)
          nmiFlag = false
        }

        // Check for interrupt request
        if(irqFlag){
          logger(s"IRQ!")
          handleInterrupt(MOS6502.IRQ_IVT)
          irqFlag = false
        }

        // Check if reset requested
        if(rstFlag){
          logger(s"Reset signal. Stopping...")
          return
          /*
          reg.reset()
          reg.ID = true
          val newPcLower: UByte = memory.readFrom(MOS6502.RST_IVT)
          val newPcUpper: UByte = memory.readFrom(MOS6502.RST_IVT + UShort(1))
          reg.PC = Bitwise.word(newPcLower, newPcUpper)
          cycles += 6
          rstFlag = false
           */
        }
      }
    }
  }

  def irq(): Unit = {
    if(reg.ID) return
    irqFlag = true
  }

  def nmi(): Unit = {
    nmiFlag = true
  }

  def reset(): Unit = {
    rstFlag = true
  }

  def halt(): Unit = {
    haltFlag = true
  }

  /*
   * Pushes old status register and old PC onto the stack.
   * Loads new PC from ivtAddr.
   */
  private def handleInterrupt(ivtAddr: UShort): Unit = {
    val status: UByte = reg.status()
    val pcLower: UByte = Bitwise.lower(reg.PC)
    val pcUpper: UByte = Bitwise.upper(reg.PC)
    val newPcLower: UByte = memory.readFrom(ivtAddr)
    val newPcUpper: UByte = memory.readFrom(ivtAddr + UShort(1))

    memory.writeTo(reg.SP, pcUpper)
    memory.writeTo(reg.SP - UByte(1), pcLower)
    memory.writeTo(reg.SP - UByte(2), status)
    reg.SP -= UByte(3)

    reg.PC = Bitwise.word(newPcLower, newPcUpper)
  }
}
