import cpu.{CODE_SEGMENT, CPU6502, DATA_SEGMENT, MemoryMap}
import spire.math.{UByte, UShort}

object Main extends App {
  val memoryMap = new MemoryMap()

  val data_ptr: UShort = UShort(0x00)
  val code_ptr: UShort = UShort(0x80)

  // 128 bytes of RAM
  val ram: Array[UByte] = Array.fill(0x80)(UByte(0x00))
  ram(0x21) = UByte(0x69)
  ram(0x22) = UByte(0x20)
  memoryMap.mapMemory(DATA_SEGMENT, ram, data_ptr)

  // Code segment
  val code: Array[UByte] = Array.fill(0x80)(UByte(0x00))
  code(0x00) = UByte(0x69) // ADC imm8
  code(0x01) = UByte(0x21) // imm8
  code(0x02) = UByte(0x75) // ADC ZP_X
  code(0x03) = UByte(0x20) // ZP-addr
  memoryMap.mapMemory(CODE_SEGMENT, code, code_ptr)

  val cpu: CPU6502 = CPU6502(memoryMap)
  cpu.registers.PC = code_ptr
  cpu.registers.X  = UByte(0x01)
  cpu.run()
  println(cpu.registers)
}
