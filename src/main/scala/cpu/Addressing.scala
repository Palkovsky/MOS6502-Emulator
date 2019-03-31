package cpu

import spire.math.{UByte, UShort}

object AddressingType extends Enumeration {
  val ZP, ZP_X, ZP_Y, ABS, ABS_X, ABS_Y, INDIRECT_X, INDIRECT_Y = Value
}

object Addressing {

  def apply(t: AddressingType.Value, memory: MemoryMap, reg: Reg6502, args: Seq[UByte]): UByte = resolve(t, memory, reg, args.toArray)

  def resolve(t: AddressingType.Value, memory: MemoryMap, reg: Reg6502, args: Array[UByte]): UByte = t match {
    case AddressingType.ZP   => memory.readFrom(args(0))
    case AddressingType.ZP_X => memory.readFrom(args(0) + reg.X)
    case AddressingType.ZP_Y => memory.readFrom(args(0) + reg.Y)
    case AddressingType.ABS =>
      val addr: UShort = (UShort(args(1).toInt) << 8) + UShort(args(0).toInt)
      memory.readFrom(addr)
    case AddressingType.ABS_X =>
      val addr: UShort = (UShort(args(1).toInt) << 8) + UShort(args(0).toInt) + UShort(reg.X.toInt)
      memory.readFrom(addr)
    case AddressingType.ABS_Y =>
      val addr: UShort = (UShort(args(1).toInt) << 8) + UShort(args(0).toInt) + UShort(reg.Y.toInt)
      memory.readFrom(addr)
    case AddressingType.INDIRECT_X =>
      val lower: UByte = memory.readFrom(reg.X + args(0))
      val upper: UByte = memory.readFrom(reg.X + args(0) + UByte(1))
      val addr:  UShort = (UShort(upper.toInt) << 8) + UShort(lower.toInt)
      memory.readFrom(addr)
    case AddressingType.INDIRECT_Y =>
      val lower: UByte  = memory.readFrom(args(0))
      val upper: UByte  = memory.readFrom(args(0) + UByte(1))
      val addr:  UShort = (UShort(upper.toInt) << 8) + UShort(lower.toInt) + UShort(reg.Y.toInt)
      memory.readFrom(addr)
  }
}
