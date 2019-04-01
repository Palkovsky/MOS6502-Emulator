package cpu.instructions

import cpu._
import spire.math.UByte

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
