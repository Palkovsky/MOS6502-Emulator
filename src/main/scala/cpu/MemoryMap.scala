package cpu

import scala.collection.mutable

abstract class SegmentType()
case object CODE_SEGMENT extends SegmentType
case object DATA_SEGMENT extends SegmentType

case class MemorySegment(segmentType: SegmentType, start: Int, end: Int){
  def size: Int = end - start + 1
}

class MemoryMap {
  private val memMap: mutable.Map[MemorySegment, Array[Byte]] = mutable.Map.empty

  def mapMemory(segmentType: SegmentType, writable: Array[Byte], start: Int): Unit = {
    if(start < 0) throw new IllegalArgumentException("Only positive mounting points.")
    if(writable.isEmpty) throw new IllegalArgumentException("Unable to map empty sequence.")
    val region = MemorySegment(segmentType, start, start + writable.length - 1)
    memMap.put(region, writable)
  }

  def writeTo(addr: Int, value: Byte): Unit = findByAddr(addr) match {
    case (region, bytes) =>
      val relativeAddr = addr-region.start
      if (relativeAddr >= region.size) throw new IllegalAccessException(s"Tried accessing unavailable memory region at: $addr")
      bytes.update(relativeAddr, value)
  }

  def readFrom(addr: Int): Byte = findByAddr(addr) match {
    case (region, bytes) =>
      val relativeAddr = addr-region.start
      if (relativeAddr >= region.size) throw new IllegalAccessException(s"Tried accessing unavailable memory region at: $addr")
      bytes(relativeAddr)
  }

  private def findByAddr(addr: Int): (MemorySegment, Array[Byte]) = {
    val filtered = memMap.filterKeys(region => addr >= region.start && addr <= region.end)
    if (filtered.isEmpty) throw new IllegalAccessException(s"Tried accessing unavailable memory region at: ${addr}")
    filtered.head
  }
}