package cpu.instructions

import cpu._
import spire.math.UByte

/*
 * ASL - Left Shift - 5 instructions
 */
object ASL_Acc extends Instruction(UByte(0x0A), 1, 2){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    val data: UByte = reg.A
    reg.CF = (data & UByte(0x80)) == UByte(0x80)
    reg.A = reg.A << 1
    reg.updateZN(reg.A)
  }
}

object ASL_ZP extends Instruction(UByte(0x06), 2, 5){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    val data    = Addressing(AddressingType.ZP, memory, reg, args)
    val updated = data << 1
    Addressing.write(AddressingType.ZP, memory, reg, args, updated)
    reg.CF = (data & UByte(0x80)) == UByte(0x80)
    reg.updateZN(updated)
  }
}

object ASL_ZP_X extends Instruction(UByte(0x16), 2, 6){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    val data    = Addressing(AddressingType.ZP_X, memory, reg, args)
    val updated = data << 1
    Addressing.write(AddressingType.ZP_X, memory, reg, args, updated)
    reg.CF = (data & UByte(0x80)) == UByte(0x80)
    reg.updateZN(updated)
  }
}

object ASL_ABS extends Instruction(UByte(0x0E), 3, 6){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    val data    = Addressing(AddressingType.ABS, memory, reg, args)
    val updated = data << 1
    Addressing.write(AddressingType.ABS, memory, reg, args, updated)
    reg.CF = (data & UByte(0x80)) == UByte(0x80)
    reg.updateZN(updated)
  }
}

object ASL_ABS_X extends Instruction(UByte(0x1E), 3, 7){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit ={
    val data    = Addressing(AddressingType.ABS_X, memory, reg, args)
    val updated = data << 1
    Addressing.write(AddressingType.ABS_X, memory, reg, args, updated)
    reg.CF = (data & UByte(0x80)) == UByte(0x80)
    reg.updateZN(updated)
  }
}

/*
 * LSR - Right shift - 5 instructions
 */
object LSR_Acc extends Instruction(UByte(0x4A), 1, 2){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    val data: UByte = reg.A
    reg.CF = (data & UByte(0x01)) == UByte(0x01)
    reg.A = reg.A >> 1
    reg.updateZN(reg.A)
  }
}

object LSR_ZP extends Instruction(UByte(0x46), 2, 5){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    val data    = Addressing(AddressingType.ZP, memory, reg, args)
    reg.CF = (data & UByte(0x01)) == UByte(0x01)
    val updated = data >> 1
    Addressing.write(AddressingType.ZP, memory, reg, args, updated)
    reg.updateZN(updated)
  }
}

object LSR_ZP_X extends Instruction(UByte(0x56), 2, 6){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    val data = Addressing(AddressingType.ZP_X, memory, reg, args)
    reg.CF = (data & UByte(0x01)) == UByte(0x01)
    val updated = data >> 1
    Addressing.write(AddressingType.ZP_X, memory, reg, args, updated)
    reg.updateZN(updated)
  }
}

object LSR_ABS extends Instruction(UByte(0x4E), 3, 6){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    val data = Addressing(AddressingType.ABS, memory, reg, args)
    reg.CF = (data & UByte(0x01)) == UByte(0x01)
    val updated = data >> 1
    Addressing.write(AddressingType.ABS, memory, reg, args, updated)
    reg.updateZN(updated)
  }
}

object LSR_ABS_X extends Instruction(UByte(0x5E), 3, 7) {
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    val data = Addressing(AddressingType.ABS_X, memory, reg, args)
    reg.CF = (data & UByte(0x01)) == UByte(0x01)
    val updated = data >> 1
    Addressing.write(AddressingType.ABS_X, memory, reg, args, updated)
    reg.updateZN(updated)
  }
}

/*
 * ROL - Rotate left - 5 instructions
 */
object ROL_Acc extends Instruction(UByte(0x2A), 1, 2){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    val value = reg.A
    val updated = (value << 1) | (value >> 7)
    reg.CF = (value & UByte(0x80)) == UByte(0x80)
    reg.A = updated
    reg.updateZN(updated)
  }
}

object ROL_ZP extends Instruction(UByte(0x26), 2, 5){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    val value = Addressing(AddressingType.ZP, memory, reg, args)
    val updated = (value << 1) | (value >> 7)
    reg.CF = (value & UByte(0x80)) == UByte(0x80)
    Addressing.write(AddressingType.ZP, memory, reg, args, updated)
    reg.updateZN(updated)
  }
}

object ROL_ZP_X extends Instruction(UByte(0x36), 2, 6){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    val value = Addressing(AddressingType.ZP_X, memory, reg, args)
    val updated = (value << 1) | (value >> 7)
    reg.CF = (value & UByte(0x80)) == UByte(0x80)
    Addressing.write(AddressingType.ZP_X, memory, reg, args, updated)
    reg.updateZN(updated)
  }
}

object ROL_ABS extends Instruction(UByte(0x2E), 3, 6){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    val value = Addressing(AddressingType.ABS, memory, reg, args)
    val updated = (value << 1) | (value >> 7)
    reg.CF = (value & UByte(0x80)) == UByte(0x80)
    Addressing.write(AddressingType.ABS, memory, reg, args, updated)
    reg.updateZN(updated)
  }
}

object ROL_ABS_X extends Instruction(UByte(0x3E), 3, 7) {
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    val value = Addressing(AddressingType.ABS_X, memory, reg, args)
    val updated = (value << 1) | (value >> 7)
    reg.CF = (value & UByte(0x80)) == UByte(0x80)
    Addressing.write(AddressingType.ABS_X, memory, reg, args, updated)
    reg.updateZN(updated)
  }
}

/*
 * ROR - Rotate right - 5 instructions
 */
object ROR_Acc extends Instruction(UByte(0x6A), 1, 2){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    val value = reg.A
    val updated = (value >> 1) | (value << 7)
    reg.CF = (value & UByte(0x01)) == UByte(0x01)
    reg.A = updated
    reg.updateZN(updated)
  }
}

object ROR_ZP extends Instruction(UByte(0x66), 2, 5){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    val value = Addressing(AddressingType.ZP, memory, reg, args)
    val updated = (value >> 1) | (value << 7)
    reg.CF = (value & UByte(0x01)) == UByte(0x01)
    Addressing.write(AddressingType.ZP, memory, reg, args, updated)
    reg.updateZN(updated)
  }
}

object ROR_ZP_X extends Instruction(UByte(0x76), 2, 6){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    val value = Addressing(AddressingType.ZP_X, memory, reg, args)
    val updated = (value >> 1) | (value << 7)
    reg.CF = (value & UByte(0x01)) == UByte(0x01)
    Addressing.write(AddressingType.ZP_X, memory, reg, args, updated)
    reg.updateZN(updated)
  }
}

object ROR_ABS extends Instruction(UByte(0x6E), 3, 6){
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    val value = Addressing(AddressingType.ABS, memory, reg, args)
    val updated = (value >> 1) | (value << 7)
    reg.CF = (value & UByte(0x01)) == UByte(0x01)
    Addressing.write(AddressingType.ABS, memory, reg, args, updated)
    reg.updateZN(updated)
  }
}

object ROR_ABS_X extends Instruction(UByte(0x7E), 3, 7) {
  override def execute(memory: MemoryMap, reg: Reg6502, args: UByte*): Unit = {
    val value = Addressing(AddressingType.ABS_X, memory, reg, args)
    val updated = (value >> 1) | (value << 7)
    reg.CF = (value & UByte(0x01)) == UByte(0x01)
    Addressing.write(AddressingType.ABS_X, memory, reg, args, updated)
    reg.updateZN(updated)
  }
}