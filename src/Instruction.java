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
        System.out.println(opcode);
    }

    //Return the opcode associated with a given hex string.
    //Opcode is bits 1 to 6
    private String convertOpcode(String hexString){
        int decimalOpcode = Integer.parseInt(hexString, 16) >> 26;
        return String.format("%02x", decimalOpcode);
    }
}
