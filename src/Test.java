import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Test {
    public void comparisonTest(){
        File testInput = new File("test_instructions_prog2.txt");

        try{
            Scanner testReader = new Scanner(testInput);
            while(testReader.hasNextLine()){
                String fullLine = testReader.nextLine();
                int spaceSeparation = fullLine.indexOf(" ");
                String hexString = fullLine.substring(0, spaceSeparation);
                String testOutput = fullLine.substring(spaceSeparation + 1);
                Instruction instr = new Instruction(hexString);
                String instructionReturn = instr.toString();
                if(!instructionReturn.equals(testOutput)){
                   System.out.println(hexString + "Expected " + testOutput + ", got " + instructionReturn);
                }
            }
        }
        catch(FileNotFoundException e){
            System.out.println("Test file not found");
        }
        System.out.println("Test complete");
    }
}
