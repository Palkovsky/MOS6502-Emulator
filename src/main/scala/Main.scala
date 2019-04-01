import cpu.{CODE_SEGMENT, CPU6502, DATA_SEGMENT, MemoryMap}
import spire.math.{UByte, UShort}

object Main extends App {
  val memoryMap = new MemoryMap()

  val data_ptr: UShort = UShort(0x00)
  val code_ptr: UShort = UShort(0x80)

  // 128 bytes of RAM
  val ram: Array[UByte] = Array.fill(0x80)(UByte(0x00))
  ram(0x21) = UByte(0x60)
  ram(0x22) = UByte(0x00)
  memoryMap.mapMemory(DATA_SEGMENT, ram, data_ptr)

  // Code segment
  val code: Array[UByte] = Array.fill(0x80)(UByte(0x00))
  code(0x00) = UByte(0x69) // ADC imm8
  code(0x01) = UByte(0x79) // imm8 = 0x79
  code(0x02) = UByte(0x75) // ADC ZP_X
  code(0x03) = UByte(0x20) // ZP-addr
  code(0x04) = UByte(0x25) // AND ZP
  code(0x05) = UByte(0x22)


  code(0x06) = UByte(0xD0) // if ZF == 1
  code(0x07) = UByte(0x08) // GO TO 0x10

  code(0x10) = UByte(0x69) // ADC imm8
  code(0x11) = UByte(0x10) // imm8 = 0x10

  memoryMap.mapMemory(CODE_SEGMENT, code, code_ptr)

  val cpu: CPU6502 = CPU6502(memoryMap)
  cpu.registers.PC = code_ptr
  cpu.registers.X  = UByte(0x01)
  cpu.run()
  println(cpu.registers)
}
