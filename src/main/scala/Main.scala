import cpu.{CODE_SEGMENT, CPU6502, DATA_SEGMENT, MemoryMap}

object Main extends App {
  val memoryMap = new MemoryMap()

  // 128 bytes of RAM
  val ram: Array[Byte] = Array.fill(0x80)(0x00)
  ram(0x21) = 0x69
  memoryMap.mapMemory(DATA_SEGMENT, ram, 0x00)

  // Code segment
  val code: Array[Byte] = Array.fill(0x80)(0x00)
  code(0x00) = 0x69 // ADC imm8
  code(0x01) = 0x21 // imm8
  code(0x02) = 0x65 // ADC ZP
  code(0x03) = 0x21 // ZP-addr
  memoryMap.mapMemory(CODE_SEGMENT, code, 0x80)

  val cpu: CPU6502 = CPU6502(memoryMap)
  cpu.registers.PC = 0x80
  cpu.run()
  println(cpu.registers)
}
