package cpu

import spire.math.{UByte, UShort}

import scala.collection.mutable

abstract class SegmentType()
case object CODE_SEGMENT extends SegmentType
case object DATA_SEGMENT extends SegmentType

case class MemorySegment(segmentType: SegmentType, start: UShort, end: UShort){
  def size: UShort = end - start + UShort(1)
}

class MemoryMap {
  private val memMap: mutable.Map[MemorySegment, Array[UByte]] = mutable.Map.empty

  def mapMemory(segmentType: SegmentType, writable: Array[UByte], start: UShort): Unit = {
    if(writable.isEmpty) throw new IllegalArgumentException("Unable to map empty sequence.")
    val region = MemorySegment(segmentType, start, start + UShort(writable.length) - UShort(1))
    memMap.put(region, writable)
  }

  def writeTo(addr: UShort, value: UByte): Unit = findByAddr(addr) match {
    case (region, bytes) =>
      val relativeAddr = addr-region.start
      if (relativeAddr >= region.size) throw new IllegalAccessException(s"Tried accessing unavailable memory region at: $addr")
      bytes.update(relativeAddr.toInt, value)
  }

  def writeTo(addr: UByte, value: UByte): Unit = writeTo(UShort(addr.toInt), value)

  def readFrom(addr: UShort): UByte = findByAddr(addr) match {
    case (region, bytes) =>
      val relativeAddr = addr-region.start
      if (relativeAddr >= region.size) throw new IllegalAccessException(s"Tried accessing unavailable memory region at: $addr")
      bytes(relativeAddr.toInt)
  }

  def readFrom(addr: UByte): UByte = readFrom(UShort(addr.toInt))

  private def findByAddr(addr: UShort): (MemorySegment, Array[UByte]) = {
    val filtered = memMap.filterKeys(region => addr >= region.start && addr <= region.end)
    if (filtered.isEmpty) throw new IllegalAccessException(s"Tried accessing unavailable memory region at: ${addr.signed.toHexString}")
    filtered.head
  }
}