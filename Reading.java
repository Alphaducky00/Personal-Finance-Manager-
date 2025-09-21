import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Reading {
    
    public static void readIncomeFiles(){
        try (BufferedReader reader = new BufferedReader(new FileReader("income.txt"))){
            boolean hasContent = false;
            String line;

            while((line = reader.readLine()) != null){
                System.out.println(line);
                hasContent = true;
            }

            if(!hasContent){
                System.out.println(Colors.CYAN + "File is empty\n" + Colors.RESET);
            }

        }
        catch(IOException e){
            System.out.println(Colors.RED + "Some error occurred" + Colors.RESET);
            System.out.println(e);
        }
    }

    public static void readExpenseFiles(){
        try (BufferedReader reader = new BufferedReader(new FileReader("expense.txt"))){
            String line;

            while((line = reader.readLine()) != null){
                System.out.println(line);
            }

        }
        catch(IOException e){
            System.out.println(Colors.RED + "Some error occurred" + Colors.RESET);
            System.out.println(e);
        }
    }

}
