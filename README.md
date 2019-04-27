# MOS6502 Emulator
Emulator of MOS 6502 - an 8 bit processor used in Atari2600 and NES.

## TO DO's
- emulating clock frequency

## Memory Organization

| Region    | Type |
|:---------:|:---------:|
| 0000-00FF | RAM         |
| 0100-0FFF | I/O ports   |
| 1000-FFFF | ROM(Code)   | 
| FFFA-FFFB | NMI handler     | 
| FFFC-FFFD | RST handler     | 
| FFFE-FFFF | IRQ/BRK handler | 