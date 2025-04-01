public class Instruction {
    //Used primarily to determine what variables to use in toString
    private final String RTYPE = "RFormat";
    private final String ITYPE = "IFormat";
    private final String JTYPE = "JFormat";
    private final String SYSCALL = "Syscall";

    private String instructionType;
    private String mnemonic;
    private String opcode;
    private String rs;
    private String rt;
    private String rd;
    private String shmt;
    private String funct;
    private String immediate;
    private String index;

    public Instruction(String hexString){
        disassemble(hexString);
    }

    private void disassemble(String hexString){
        opcode = convertOpcode(hexString);
        instructionType = convertInstructionType(hexString);
        switch(instructionType){
            case RTYPE:
                break;

            case ITYPE:
                mnemonic = convertMnemonic();
                break;

            case JTYPE:
                mnemonic = convertMnemonic();
                break;

            case SYSCALL:
                mnemonic = convertMnemonic();
                break;
        }
        System.out.println(mnemonic);
    }

    //Return the opcode associated with a given hex string.
    //Opcode is bits 1 to 6
    private String convertOpcode(String hexString){
        int decimalOpcode = Integer.parseInt(hexString, 16) >> 26;
        return String.format("%02x", decimalOpcode);
    }

    //Use known opcode to return Instruction Type. Hex string used for syscall
    private String convertInstructionType(String hexString){
        //Syscall is always the same, but the opcode is the same as R format
        if(hexString.equals("0000000c")){
            return SYSCALL;
        }
        //000000 - R Format instructions
        else if(opcode.equals("00")){
            return RTYPE;
        }
        //Only one j type instruction, and opcode will be 02
        else if(opcode.equals("02")){
            return JTYPE;
        }
        else{
            return ITYPE;
        }
    }

    //Use known opcode, funct and instructionType to convert mnemonic
    private String convertMnemonic(){
        //R type and syscall instructions need to use funct
        if(instructionType.equals(RTYPE) || instructionType.equals(SYSCALL)){
            //add - 100000
            if(funct.equals("20")){
                return "add";
            }
            //and - 100100
            else if(funct.equals("24")){
                return "add";
            }
            //or - 100101
            else if(funct.equals("25")){
                return "or";
            }
            //slt - 101010
            else if(funct.equals("2a")){
                return "slt";
            }
            //sub - 100010
            else if(funct.equals("22")){
                return "sub";
            }
            //syscall - 001100
            else if(funct.equals("0c")){
                return "syscall";
            }
        }
        //everything else uses opcode
        else{
            //addiu - 001001
            if(opcode.equals("09")){
                return "addiu";
            }
            //andi - 001100
            else if(opcode.equals("0c")){
                return "andi";
            }
            //beq - 000100
            else if(opcode.equals("04")){
                return "beq";
            }
            //bne - 000101
            else if(opcode.equals("05")){
                return "bne";
            }
            //j - 000010
            else if(opcode.equals("02")){
                return "j";
            }
            //lui - 001111
            else if(opcode.equals("0f")){
                return "lui";
            }
            //lw - 100011
            else if(opcode.equals("23")){
                return "lw";
            }
            //ori - 001101
            else if(opcode.equals("0d")){
                return "ori";
            }
            //sw - 101011
            else if(opcode.equals("2b")){
                return "sw";
            }
        }
        //No mnemonic was found
        return "Error";
    }
}
