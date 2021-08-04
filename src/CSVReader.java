import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class CSVReader {
    private static final String DELIMINATOR = ",";
    private Scanner fileScanner;

    public CSVReader(String file, boolean skipHead){

            try{
                fileScanner = new Scanner(new File(file));
                if(skipHead){
                    fileScanner.nextLine();
                }
            }catch(IOException ex) {
                System.err.printf("File %s not found%n", file);
                System.exit(1);
            }
    }

    public String[] getNext(){
        boolean hasNextLine = fileScanner.hasNext();
        if(hasNextLine){
            String line = fileScanner.nextLine();
            if(line.isEmpty()){
                return null;
            }
            else{
                return line.split(",");
            }
        }
        return null;
    }

    public boolean hasNext(){
        if(fileScanner.hasNext()){
            return true;
        }
        else{
            return false;
        }
    }

    public static void main(String[] args){
        CSVReader test = new CSVReader("./test.csv", false);
        System.out.print(test.getNext()[0]);

    }
}
