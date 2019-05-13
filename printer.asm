ORG $1000

define TTY_OUT $0100

DATA {
buff:                           ; String buffer
	DB $44                        ; D
  DB $41                        ; A
  DB $57                        ; W
  DB $49                        ; I
  DB $44                        ; D
  DB $00                        ; NULL
}

CODE {
  LDX #$FF                    ; Init stack ptr to $FF
	TXS                         ; - || -

  JSR PRINT
  JMP EXIT

PRINT:
  LDY #0
PRINT_LOOP:
  LDA buff, Y
  CMP #0
  BEQ *PRINT_END
  STA TTY_OUT
  INY
	JMP PRINT_LOOP
PRINT_END:
  RTS

EXIT:
  BRK
}
