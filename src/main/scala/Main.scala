import hardware.MemoryMap

object Main extends App {
  val memoryMap = new MemoryMap()

  // 128 bytes of RAM
  memoryMap.mapMemory(Array.fill(128)(0), 0x4000)

  memoryMap.writeTo(0x4000 + 0x21, 1)
  println(memoryMap.readFrom(0x4000 + 0x21))
}
