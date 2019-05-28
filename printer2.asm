/*
* Cool trick with embedding data in code.
*/
ORG $1000

define TTY_OUT $0100
define CPU_STOP $0110

CODE {
  SEI                           ; disable interrupts

  LDX #$FF                      ; setup stack
	TXS

  JSR PRINT                     ; store next addres in memory
  "- Halo, halo co sie dzieje?" ; address of string will be pushed on stack
  DB $00                        ; null-terminator

  JSR PRINT
  "- Otwierac, Policja!"
  DB $00

  JSR PRINT
  "- Jest Pan aresztowany za zdrade Polski podziemnej!"
  DB $00

  JMP EXIT

PRINT:
  PLA                           ; get lower byte from stack
  STA $00                       ; store lower byte to ZP: 0x00
  PLA                           ; get upper byte from stack
  STA $01                       ; store upper byte to ZP: 0x01
  LDY #00                       ; reset Y reg
PRINT_LOOP:
  LDA ($00), Y                  ; load character indirectyle
  INY                           ; increment Y
  CMP #0                        ; check if null-termninator reached
  BEQ *PRINT_EXIT               ; if reached - exit

PRINT_PRINT:
  STA TTY_OUT                   ; if not reached - send byte to screen

PRINT_WAIT_FOR_ACK:             ; simple synchronization
  LDA TTY_OUT                   ; wait until byte consumed
  CMP #0                        ; - || -
  BNE *PRINT_WAIT_FOR_ACK       ; - || -

  JMP PRINT_LOOP                ; back to looping
PRINT_EXIT:
  STY $03                       ; store sting len at ZP: 0x03
  LDA $00                       ; load lower byte of string
  CLS                           ; clear carry flag
  ADC $03                       ; add strlen to lower byte
  STA $00                       ; save updated lower byte
  LDA $01                       ; propagate carry to next byte
  ADC #1                        ; - || -
  JMP ($00)                     ; jump to newly constructed adddres


EXIT:
  LDA #$01
  STA CPU_STOP
  JMP EXIT
}
