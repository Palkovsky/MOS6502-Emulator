import java.nio.file.{Files, Paths}

import cpu._
import io.{Monitor, Screen, Keyboard}
import spire.math.{UByte, UShort}

/*
 * Memory architecture:
 * 0x0000-0x00FF - RAM(256 bytes)
 * 0x0100-Ox0FFF - I/O REGISTERS
 * 0x1000-0xFFFF - Code segment
 * 0xFFFA-0xFFFB - NMI IV
 * 0xFFFC-0xFFFD - RST IV
 * 0xFFFE-0xFFFF - IRQ/BRK IV
 */

object Main extends App {
  if(args.length != 1){
    println("ERROR! Usage: 6502 [code_path]")
    System.exit(0)
  }

  val path: String = args(0)
  val code: Array[UByte] = Files.readAllBytes(Paths.get(path)).map(byte => UByte(byte))

  val memoryMap = new MemoryMap()
  val ramBasePtr: UShort = UShort(0x0000)
  val codeBasePtr: UShort = UShort(0x1000)

  // 256 bytes of RAM
  val ram: Array[UByte] = Array.fill(256)(UByte(0x00))
  memoryMap.mapMemory(RAM_SEGMENT, ram, ramBasePtr)

  // Code segment
  memoryMap.mapMemory(CODE_SEGMENT, code, codeBasePtr)

  // IVT
  memoryMap.mapMemory(CODE_SEGMENT, Array.fill(6)(UByte(0x00)), UShort(0xFFFA))

  // Initialize devices
  val cpu = MOS6502(memoryMap, codeBasePtr)
  val screen: Screen = Screen(cpu)
  val monitor: Monitor = Monitor(cpu)
  val keyboard: Keyboard = Keyboard(cpu)

  memoryMap.mapMemory(IO_SEGMENT, screen.ports, UShort(0x0100))
  memoryMap.mapMemory(IO_SEGMENT, monitor.ports, UShort(0x0110))
  memoryMap.mapMemory(IO_SEGMENT, keyboard.ports, UShort(0x0120))

  val screenThread = new Thread(screen)
  screenThread.setDaemon(true)
  screenThread.start()

  val keyboardThread = new Thread(keyboard)
  keyboardThread.setDaemon(true)
  keyboardThread.start()

  val monitorThread = new Thread(monitor)
  monitorThread.setDaemon(true)
  monitorThread.start()

  val cpuThread = new Thread(cpu)
  cpuThread.start()

  cpuThread.join()
  screenThread.interrupt()
  keyboardThread.interrupt()
  monitorThread.interrupt()
}

/*
  ========== STACK INITIALIZATION =========
  code(0) = UByte(0xA2) // LDX Imm
  code(1) = UByte(0xFF)
  code(2) = UByte(0x9A) // TXS == X=>SP
  code(3) = UByte(0x00) // BRK

  ========== READING FROM TERMINAL AND OUTPUTTING ==========
  memoryMap.writeTo(UShort(0x2000), UByte(0xAD)) // LDA 0x0101
  memoryMap.writeTo(UShort(0x2001), UByte(0x01))
  memoryMap.writeTo(UShort(0x2002), UByte(0x01))
  memoryMap.writeTo(UShort(0x2003), UByte(0x8D)) // STA 0x0100
  memoryMap.writeTo(UShort(0x2004), UByte(0x00))
  memoryMap.writeTo(UShort(0x2005), UByte(0x01))
  memoryMap.writeTo(UShort(0x2006), UByte(0x40)) // Return from interrupt
 */
