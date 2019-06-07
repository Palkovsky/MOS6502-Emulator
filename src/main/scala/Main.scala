import java.nio.file.{Files, Paths}

import cpu._
import io._
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
  val clock: Clock = Clock(cpu)

  memoryMap.mapMemory(IO_SEGMENT, screen.ports, UShort(0x0100))
  memoryMap.mapMemory(IO_SEGMENT, monitor.ports, UShort(0x0110))
  memoryMap.mapMemory(IO_SEGMENT, keyboard.ports, UShort(0x0120))
  memoryMap.mapMemory(IO_SEGMENT, clock.ports, UShort(0x0130))

  val screenThread = new Thread(screen)
  val keyboardThread = new Thread(keyboard)
  val monitorThread = new Thread(monitor)
  val clockThread = new Thread(clock)
  val cpuThread = new Thread(cpu)

  screenThread.setDaemon(true)
  keyboardThread.setDaemon(true)
  monitorThread.setDaemon(true)
  clockThread.setDaemon(true)

  screenThread.start()
  keyboardThread.start()
  monitorThread.start()
  clockThread.start()
  cpuThread.start()

  cpuThread.join()

  screenThread.interrupt()
  keyboardThread.interrupt()
  monitorThread.interrupt()
  clockThread.interrupt()
}
