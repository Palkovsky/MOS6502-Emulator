ORG $1000

/*
 * Screen ports
 */
define TTY_OUT $0100
define TTY_MODE $0101    

/*
 * Clock ports
 */
define CLOCK_HOUR $0130
define CLOCK_MINUTE $0131
define CLOCK_SECONDS $0132    
   
/*
 * Monitor ports
 */
define CPU_STOP $0110
define CPU_SLEEP_AMT $0111
define CPU_SLEEP_TRIG $0112    
    
DATA {
  l0: DB $20 "_     _ _   _ _ _   _ _ _   _ _ "  DB $0A DB $00
  l1: "| |   |   | |_   _| |_   _| |   |"  DB $0A DB $00
  l2: "| |_  | | |   | |     | |   | | |"  DB $0A DB $00
  l3: "|_ _| |_ _|   |_|     |_|   |_ _|"  DB $0A DB $00
  msg: "GENERATOR LICZB LOSOWYCH TOTOLOTKA" DB $0A DB $00
  sep: "----------------------------------" DB $0A DB $00
  NUM_COUNT: DB $5
}

    
CODE {
  SEI                           ; disable interrupts
  LDX #$FF                      ; setup stack
  TXS                           ; - || -

  SEG PRINT_WELCOME             ; print logo and welcome message

  LDA #1                        ; sleep for 1 sec
  JSR SLEEP                     ; - || -
    
  LDA CLOCK_SECONDS             ; load seed
  LDY NUM_COUNT                 ; how many numbers to print
RANDOM_LOOP:    
  CPY #00                       ; exit if randomized enough numbers
  BEQ *RANDOM_LOOP_EXIT         ; - || -
  DEY
  JSR NEXT_NUM                  ; generate next number in pseudo-random sequence
  JSR PRINT_NUM                 ; print newly generated number

  PHA                           ; sleep for 2 secs before printing next numbers
  LDA #2                        ; - || -
  JSR SLEEP                     ; - || -
  PLA                           ; - || -
    
  JMP RANDOM_LOOP 

RANDOM_LOOP_EXIT:
  JMP EXIT    

NEXT_NUM:
  ROL
  ROL
  ADC #7 
  RTS
    
/*
 * PRINT_NUM subroutine
 * Prints 8-bit number passed through accumlator.
 */
PRINT_NUM:
  PHA  
  SEG SWITCH_TO_NUM_MODE
  JSR PUT_CHAR
  PLA
  RTS
    
/*
 * PRINT_STRING subroutine
 * Prints null-terminated string. String ptr passed by stack. 
 */ 
PRINT_STRING:
  SEG SWITCH_TO_CHAR_MODE
  SEG SAVE_OFF
  LDY #00    
PRINT_STRING_LOOP:
  LDA ($00), Y                  ; load character indirectly
  INY                           ; increment Y
  CMP #0                        ; check if null-termninator reached
  BEQ *PRINT_STRING_EXIT        ; if reached - exit
  JSR PUT_CHAR
  JMP PRINT_STRING_LOOP         ; back to looping
PRINT_STRING_EXIT:
  SEG RETURN_OFF

/*
 * PUT_CHAR subroutine
 * Puts character in TTY buffer and waits for it being flushed.
 */
PUT_CHAR:
  STA TTY_OUT
PUT_CHAR_WAIT_FOR_ACK:
  LDA TTY_OUT
  CMP #0
  BNE *PUT_CHAR_WAIT_FOR_ACK  
  RTS

/*
 * SLEEP subroutine
 * Sleeps for amount of seconds passed in accumulator.
 */
SLEEP:
  STA CPU_SLEEP_AMT
  STA CPU_SLEEP_TRIG  
  RTS
    
EXIT:
  LDA #1
  STA CPU_STOP
  JMP EXIT
}

/*
 * ============================= MACROS
 */
PRINT_WELCOME {
  JSR PRINT_STRING
  OFF l0
  JSR PRINT_STRING
  OFF l1
  JSR PRINT_STRING
  OFF l2
  JSR PRINT_STRING
  OFF l3
  JSR PRINT_STRING
  OFF sep
  JSR PRINT_STRING
  OFF msg
  JSR PRINT_STRING
  OFF sep
}

SWITCH_TO_CHAR_MODE {
    LDX #0
    STX TTY_MODE
}

SWITCH_TO_NUM_MODE {
    LDX #1
    STX TTY_MODE
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
