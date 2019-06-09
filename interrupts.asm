ORG $1000

define TTY_OUT $0100
define TTY_IN $0120
define CPU_STOP $0110

DATA {
INTERRUPT_COUNT:
  DB $0
INTERRUPT_MAX:
  DB $5
}

CODE {
  LDX #$FF                        ; setup stack
	TXS                             ; - || -

MAIN:
  LDA INTERRUPT_COUNT           ; how many interrupts handled
  CMP INTERRUPT_MAX             ; check if served maximum allowed number of interrupts
  BCC *MAIN                     ; if not, wait for it

  JMP EXIT                      ; if yes - start EXIT subroutine

EXIT:
  LDA #1                        ; send non-zero value to monitor register
  STA CPU_STOP                  ; - || -
  JMP EXIT                      ; this will prevent executing data in short period when processor not stopped
}

IRQ {                                ; Interrupt segment. Defining it will result in overriding IVT before CODE execution.
  LDA TTY_IN                    ; load input char from device register
  STA TTY_OUT                   ; send it to screen

WAIT_FOR_ACK:                   ; wait for monitor until it flushes byte
  LDA TTY_OUT                   ; - || -
  CMP #0                        ; - || -
  BNE *WAIT_FOR_ACK             ; - || -

  INC INTERRUPT_COUNT
  RTI
}
