package cpu.instructions

import cpu._
import spire.math.UByte

/*
 * ASL - Left Shift - 5 instructions
 */
trait ASL_Base extends Instruction{
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    val data    = Addressing(addressing, memory, reg, args)
    val updated = data << 1
    Addressing.write(addressing, memory, reg, args, updated)
    reg.CF = (data & UByte(0x80)) == UByte(0x80)
    reg.updateZN(updated)
  }
}
object ASL_Acc extends Instruction(UByte(0x0A), 1, 2, AddressingType.ACC) with ASL_Base
object ASL_ZP extends Instruction(UByte(0x06), 2, 5, AddressingType.ZP) with ASL_Base
object ASL_ZP_X extends Instruction(UByte(0x16), 2, 6, AddressingType.ZP_X) with ASL_Base
object ASL_ABS extends Instruction(UByte(0x0E), 3, 6, AddressingType.ABS) with ASL_Base
object ASL_ABS_X extends Instruction(UByte(0x1E), 3, 7, AddressingType.ABS_X) with ASL_Base

/*
 * LSR - Right shift - 5 instructions
 */

trait LSR_Base extends Instruction{
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    val data    = Addressing(addressing, memory, reg, args)
    reg.CF = (data & UByte(0x01)) == UByte(0x01)
    val updated = data >> 1
    Addressing.write(addressing, memory, reg, args, updated)
    reg.updateZN(updated)
  }
}
object LSR_Acc extends Instruction(UByte(0x4A), 1, 2, AddressingType.ACC) with LSR_Base
object LSR_ZP extends Instruction(UByte(0x46), 2, 5, AddressingType.ZP) with LSR_Base
object LSR_ZP_X extends Instruction(UByte(0x56), 2, 6, AddressingType.ZP_X) with LSR_Base
object LSR_ABS extends Instruction(UByte(0x4E), 3, 6, AddressingType.ABS) with LSR_Base
object LSR_ABS_X extends Instruction(UByte(0x5E), 3, 7, AddressingType.ABS_X) with LSR_Base

/*
 * ROL - Rotate left - 5 instructions
 */
trait ROL_Base extends Instruction{
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    val value = Addressing(addressing, memory, reg, args)
    val updated = (value << 1) | (value >> 7)
    reg.CF = (value & UByte(0x80)) == UByte(0x80)
    Addressing.write(addressing, memory, reg, args, updated)
    reg.updateZN(updated)
  }
}
object ROL_Acc extends Instruction(UByte(0x2A), 1, 2, AddressingType.ACC) with ROL_Base
object ROL_ZP extends Instruction(UByte(0x26), 2, 5, AddressingType.ZP) with ROL_Base
object ROL_ZP_X extends Instruction(UByte(0x36), 2, 6, AddressingType.ZP_X) with ROL_Base
object ROL_ABS extends Instruction(UByte(0x2E), 3, 6, AddressingType.ABS) with ROL_Base
object ROL_ABS_X extends Instruction(UByte(0x3E), 3, 7, AddressingType.ABS_X) with ROL_Base

/*
 * ROR - Rotate right - 5 instructions
 */
trait ROR_Base extends Instruction {
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    val value = Addressing(addressing, memory, reg, args)
    val updated = (value >> 1) | (value << 7)
    reg.CF = (value & UByte(0x01)) == UByte(0x01)
    Addressing.write(addressing, memory, reg, args, updated)
    reg.updateZN(updated)
  }
}
object ROR_Acc extends Instruction(UByte(0x6A), 1, 2, AddressingType.ACC) with ROR_Base
object ROR_ZP extends Instruction(UByte(0x66), 2, 5, AddressingType.ZP) with ROR_Base
object ROR_ZP_X extends Instruction(UByte(0x76), 2, 6, AddressingType.ZP_X) with ROR_Base
object ROR_ABS extends Instruction(UByte(0x6E), 3, 6, AddressingType.ABS) with ROR_Base
object ROR_ABS_X extends Instruction(UByte(0x7E), 3, 7, AddressingType.ABS_X) with ROR_Base
