ORG $1000

/*
 * Screen ports
 */
define TTY_OUT $0100

/*
 * Monitor ports
 */
define CPU_STOP $0110
define CPU_SLEEP_AMT $0111
define CPU_SLEEP_TRIG $0112    
    
DATA {
  txt1:
    "- Halo, halo co sie dzieje?"
    DB $0A
    DB $00
  txt2:
    "- Otwierac, Policja!"
    DB $0A
    DB $00
  txt3:
    "- Jest Pan aresztowany za zdrade Polski podziemnej!"
    DB $0A
    DB $00
}
    
CODE {
  SEI                           ; disable interrupts
  LDX #$FF                      ; setup stack
  TXS

  JSR PRINT
  OFF txt1

  JSR SLEEP
    
  JSR PRINT
  OFF txt2

  JSR SLEEP
    
  JSR PRINT
  OFF txt3
    
  JSR SLEEP

  JMP EXIT    

SLEEP:
  LDA #2
  STA CPU_SLEEP_AMT
  STA CPU_SLEEP_TRIG  
  RTS

PRINT:
  SEG SAVE_OFF
  LDY #00    
PRINT_LOOP:
  LDA ($00), Y                  ; load character indirectly
  INY                           ; increment Y
  CMP #0                        ; check if null-termninator reached
  BEQ *PRINT_EXIT               ; if reached - exit
  STA TTY_OUT
FLUSH_CHAR_WAIT_FOR_ACK:
  LDA TTY_OUT
  CMP #0
  BNE *FLUSH_CHAR_WAIT_FOR_ACK
  JMP PRINT_LOOP                ; back to looping
PRINT_EXIT:
  SEG RETURN_OFF

EXIT:
  LDA #1
  STA CPU_STOP
  JMP EXIT
}
  
SAVE_OFF {
  PLA                           ; get lower byte from stack
  STA $02                       ; store lower byte to ZP: 0x02
  PLA                           ; get upper byte from stack
  STA $03                       ; store upper byte to ZP: 0x03
  LDY #00                       ; reset Y reg
    
  LDA ($02), Y                  ; get lower addr of data segment
  STA $00                       ; move it to ZP: 0x00
  INY                           
  LDA ($02), Y                  ; get higher byte
  STA $01                       ; move it to ZP: 0x01
}
    
RETURN_OFF {
  LDA $02                       ; load lower byte of offset
  CLS                           ; clear carry flag
  ADC #2                        ; add strlen to lower byte
  STA $02                       ; save updated lower byte
  LDA $03                       ; propagate carry to next byte
  ADC #0                        ; - || -
  JMP ($02)                     ; jump to newly constructed adddres
}
