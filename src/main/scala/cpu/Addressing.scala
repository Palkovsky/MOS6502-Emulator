package cpu

import spire.math.{UByte, UShort}

object AddressingType extends Enumeration {
  val ZP, ZP_X, ZP_Y, ABS, ABS_X, ABS_Y, INDIRECT, INDIRECT_X, INDIRECT_Y = Value
}

object Addressing {

  def apply(t: AddressingType.Value, memory: MemoryMap, reg: Reg6502, args: Seq[UByte]): UByte = get(t, memory, reg, args)

  def get(t: AddressingType.Value, memory: MemoryMap, reg: Reg6502, args: Seq[UByte]): UByte = t match {
    case AddressingType.ZP   => memory.readFrom(args.head)
    case AddressingType.ZP_X => memory.readFrom(args.head + reg.X)
    case AddressingType.ZP_Y => memory.readFrom(args.head + reg.Y)
    case AddressingType.ABS =>
      val addr: UShort = (UShort(args(1).toInt) << 8) + UShort(args.head.toInt)
      memory.readFrom(addr)
    case AddressingType.ABS_X =>
      val addr: UShort = (UShort(args(1).toInt) << 8) + UShort(args.head.toInt) + UShort(reg.X.toInt)
      memory.readFrom(addr)
    case AddressingType.ABS_Y =>
      val addr: UShort = (UShort(args(1).toInt) << 8) + UShort(args.head.toInt) + UShort(reg.Y.toInt)
      memory.readFrom(addr)
    case AddressingType.INDIRECT_X =>
      val lower: UByte = memory.readFrom(reg.X + args.head)
      val upper: UByte = memory.readFrom(reg.X + args.head + UByte(1))
      val addr:  UShort = (UShort(upper.toInt) << 8) + UShort(lower.toInt)
      memory.readFrom(addr)
    case AddressingType.INDIRECT_Y =>
      val lower: UByte  = memory.readFrom(args.head)
      val upper: UByte  = memory.readFrom(args.head + UByte(1))
      val addr:  UShort = (UShort(upper.toInt) << 8) + UShort(lower.toInt) + UShort(reg.Y.toInt)
      memory.readFrom(addr)
  }

  def write(t: AddressingType.Value, memory: MemoryMap, reg: Reg6502, args: Seq[UByte], value: UByte):Unit = t match {
    case AddressingType.ZP => memory.writeTo(args.head, value)
    case AddressingType.ZP_X => memory.writeTo(args.head + reg.X, value)
    case AddressingType.ZP_Y => memory.writeTo(args.head + reg.Y, value)
    case AddressingType.ABS =>
      val addr: UShort = (UShort(args(1).toInt) << 8) + UShort(args.head.toInt)
      memory.writeTo(addr, value)
    case AddressingType.ABS_X =>
      val addr: UShort = (UShort(args(1).toInt) << 8) + UShort(args.head.toInt) + UShort(reg.X.toInt)
      memory.writeTo(addr, value)
    case AddressingType.ABS_Y =>
      val addr: UShort = (UShort(args(1).toInt) << 8) + UShort(args.head.toInt) + UShort(reg.Y.toInt)
      memory.writeTo(addr, value)
    case AddressingType.INDIRECT_X =>
      val lower: UByte = memory.readFrom(reg.X + args.head)
      val upper: UByte = memory.readFrom(reg.X + args.head + UByte(1))
      val addr:  UShort = (UShort(upper.toInt) << 8) + UShort(lower.toInt)
      memory.writeTo(addr, value)
    case AddressingType.INDIRECT_Y =>
      val lower: UByte  = memory.readFrom(args.head)
      val upper: UByte  = memory.readFrom(args.head + UByte(1))
      val addr:  UShort = (UShort(upper.toInt) << 8) + UShort(lower.toInt) + UShort(reg.Y.toInt)
      memory.writeTo(addr, value)
  }
}
