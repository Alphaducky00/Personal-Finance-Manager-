import java.util.InputMismatchException;
import java.util.Scanner;

public class Search {
    
    public void searchTransactions(){

        try{
        System.out.println(Colors.BLUE + "Which file do you wish to search? Type 1 for income file or 2 for expense file: " + Colors.RESET);

        Scanner sc = new Scanner(System.in);
        int fileChoice = sc.nextInt();
        sc.nextLine();

        if (fileChoice != 1 && fileChoice != 2){
            throw new InvalidInputException();
        }

        String fileName = "";
        if (fileChoice == 1){
            fileName = "income.txt";
            System.out.println(Colors.BLUE + "Searching income file..."  + Colors.RESET);
        }
        else{
            fileName = "expense.txt";
            System.out.println(Colors.BLUE + "Searching expense file..."  + Colors.RESET);
        }

        ReadSearch rs = new ReadSearch();
        rs.searchParagraphs(fileName);


        if (!ReadSearch.foundParagraphs.isEmpty()){
            System.out.println(Colors.CYAN + "=== Search Results ==="  + Colors.RESET);
            displaySearchResults();
        }
    }
        catch(InvalidInputException e){
            System.out.println(Colors.RED + "Invalid input! please select 1 or 2"  + Colors.RESET);
        }
        catch(InputMismatchException e){
            System.out.println(Colors.RED + "Please enter a number"  + Colors.RESET);
        }
    

    }

    private void displaySearchResults(){

        for (int i = 0; i < ReadSearch.foundParagraphs.size(); i++){
            System.out.println(Colors.CYAN + "Result: " + (i + 1) + ": Original Paragraph: " + ReadSearch.foundParagraphs.get(i)  + Colors.RESET);
            System.out.println(ReadSearch.foundParagraphs.get(i));
            System.out.println();
        }

        System.out.println(Colors.CYAN + "Total Results found: " + ReadSearch.foundParagraphs.size() + "\n"  + Colors.RESET);
    }
}
