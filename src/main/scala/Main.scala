import java.nio.file.{Files, Paths}

import cpu._
import io.Terminal
import spire.math.{UByte, UShort}

/*
 * Memory architecture:
 * 0x0000-0x00FF - RAM(256 bytes)
 * 0x0100-Ox0FFF - I/O REGISTERS
 * 0x1000-0xFFFF - Code segment
 */

object Main extends App {
  if(args.length != 1)
    throw new IllegalArgumentException("Usage: . [code_path]")

  val path: String = args(0)
  val code: Array[UByte] = Files.readAllBytes(Paths.get(path)).map(byte => UByte(byte))
  if(code.length > 0xEFFF)
    throw new IllegalArgumentException("Program won't fit in 16-bit address space!")

  val memoryMap = new MemoryMap()
  val ramBasePtr: UShort = UShort(0x0000)
  val codeBasePtr: UShort = UShort(0x1000)

  // 256 bytes of RAM
  val ram: Array[UByte] = Array.fill(256)(UByte(0x00))
  memoryMap.mapMemory(RAM_SEGMENT, ram, ramBasePtr)

  // Code segment
  memoryMap.mapMemory(CODE_SEGMENT, code, codeBasePtr)

  // Terminal device
  val tty: Terminal = Terminal()
  memoryMap.mapMemory(IO_SEGMENT, tty.ports, UShort(0x0100))

  val ttyThread = new Thread(tty)
  ttyThread.setDaemon(true)
  ttyThread.start()

  val cpu = MOS6502(memoryMap, codeBasePtr)
  val cpuThread = new Thread(cpu)
  cpuThread.start()
  cpuThread.join(1*1000)
  Thread.sleep(100)
}
