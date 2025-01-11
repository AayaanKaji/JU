.MODEL SMALL
.STACK 100H
.DATA
VAR1 DB "BEFORE SWAP", 10, 13, "$"
VAR2 DB "AFTER SWAP", 10, 13, "$"
NUM1 DB 9
NUM2 DB 5
.CODE
MAIN PROC
 MOV AX, @DATA
 MOV DS, AX
 ; Define a macro to swap the values of NUM1 and NUM2
 SWAP MACRO NUM1, NUM2
 MOV BL, NUM1
 MOV CL, NUM2
 XCHG BL, CL
 MOV NUM1, BL
 MOV NUM2, CL
 ENDM
 ; Display "BEFORE SWAP"
 MOV DX, OFFSET VAR1
 MOV AH, 09H
 INT 21H
 ; Display the value of NUM1
 MOV DL, NUM1
 ADD DL, 30H
 MOV AH, 02H
 INT 21H
 ; Display a space
 MOV DL, 32
 MOV AH, 02H
 INT 21H
 ; Display the value of NUM2
 MOV DL, NUM2
 ADD DL, 30H
 MOV AH, 02H
 INT 21H
 ; Display newline characters
 MOV DL, 10
 MOV AH, 02H
 INT 21H
 MOV DL, 13
 MOV AH, 02H
 INT 21H
 ; Use the SWAP macro to swap the values of NUM1 and NUM2
 SWAP NUM1, NUM2
 ; Display "AFTER SWAP"
 MOV DX, OFFSET VAR2
 MOV AH, 09H
 INT 21H
 ; Display the value of NUM1 (previously NUM2)
 MOV DL, NUM1
 ADD DL, 30H
 MOV AH, 02H
 INT 21H
 ; Display a space
 MOV DL, 32
 MOV AH, 02H
 INT 21H
 ; Display the value of NUM2 (previously NUM1)
 MOV DL, NUM2
 ADD DL, 30H
 MOV AH, 02H
 INT 21H
 ; Display newline characters
 MOV DL, 10
 MOV AH, 02H
 INT 21H
 MOV DL, 13
 MOV AH, 02H
 INT 21H
 ; Exit the program
 MOV AH, 4CH
 INT 21H
MAIN ENDP
END MAIN
