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
        String newInstructionType = "Error";
        //Syscall is always the same, but the opcode is the same as R format
        if(hexString.equals("0000000c")){
            newInstructionType = SYSCALL;
        }
        //000000 - R Format instructions
        else if(opcode.equals("00")){
            newInstructionType = RTYPE;
        }
        //Only one j type instruction, and opcode will be 02
        else if(opcode.equals("02")){
            newInstructionType = JTYPE;
        }
        else{
            newInstructionType = ITYPE;
        }
        return newInstructionType;
    }

    //Use known opcode, funct and instructionType to convert mnemonic
    private String convertMnemonic(){
        String newMnemonic = "Error";
        //R type and syscall instructions need to use funct
        if(instructionType.equals(RTYPE) || instructionType.equals(SYSCALL)){
            switch(funct){
                case "20": //add - 100000
                    newMnemonic = "add";
                    break;
                case "24": //and - 100100
                    newMnemonic = "and";
                    break;
                case "25": //or - 100101
                    newMnemonic = "or";
                    break;
                case "2a": //slt - 101010
                    newMnemonic = "slt";
                    break;
                case "22"://sub - 100010
                    newMnemonic = "sub";
                    break;
                case "0c": //syscall - 001100
                    newMnemonic = "syscall";
                    break;
            }
        }
        //everything else uses opcode
        else{
            switch(opcode){
                case "09": //addiu - 001001
                    newMnemonic = "addiu";
                    break;
                case "0c": //andi - 001100
                    newMnemonic = "andi";
                    break;
                case "04": //beq - 000100
                    newMnemonic = "beq";
                    break;
                case "05": //bne - 000101
                    newMnemonic = "bne";
                    break;
                case "02": //j - 000010
                    newMnemonic = "j";
                    break;
                case "0f": //lui - 001111
                    newMnemonic = "lui";
                    break;
                case "23": //lw - 100011
                    newMnemonic = "lw";
                    break;
                case "0d": //ori - 001101
                    newMnemonic = "ori";
                    break;
                case "2b": //sw - 101011
                    newMnemonic = "sw";
                    break;
            }
        }
        return newMnemonic;
    }
}
