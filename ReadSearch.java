import java.util.*;
import java.io.*;

public class ReadSearch {
    
    public static List<String> newList = new ArrayList<>();
    public static List<String> foundParagraphs = new ArrayList<>();
    public static List<Integer> paragraphNumber = new ArrayList<>();

    // Reading file and searching by date:

    // Spliting lines into Paragraph:

    public void splitIntoParagraphs(String fileName){

        newList.clear();
    
    StringBuilder currentParagraph = new StringBuilder();

    try{
        File file = new File(fileName);
        Scanner sc = new Scanner(file);

        while(sc.hasNextLine()){
            String line = sc.nextLine();

            if(line.equals("")){
                
                if (currentParagraph.length() > 0){
                    newList.add(currentParagraph.toString());
                    currentParagraph = new StringBuilder();
                }
            }
            else{
                currentParagraph.append(line).append("\n");
            }
        }

        if (currentParagraph.length() > 0){
            newList.add(currentParagraph.toString());
        }

        sc.close();

    }
    catch(FileNotFoundException e){
        System.out.println(Colors.RED + "Error: file not found" + Colors.RESET);
        System.out.println(e);
    }
    }

    public int searchFunction(List<String> newList, String date){

        if (newList.isEmpty()){
            System.out.println(Colors.CYAN + "File is empty - No transactions found" + Colors.RESET);
            return 0;
        }

        foundParagraphs.clear();
        paragraphNumber.clear();

        for (int i = 0; i < newList.size(); i++){
            
            if(newList.get(i).contains(date)){
                System.out.println(Colors.CYAN + "Paragraph found\n" + Colors.RESET);
                foundParagraphs.add(newList.get(i));
                paragraphNumber.add(i + 1);
                
            }
        }

        if(foundParagraphs.isEmpty()){
            System.out.println(Colors.CYAN + "Either date format is wrong or paragraph is not there" + Colors.RESET);
        }
        
        return foundParagraphs.size();
    }

    public void searchParagraphs(String fileName){
        System.out.println(Colors.BLUE + "Enter a date strictly in the format: yyyy-MM-dd to find the paragraph" + Colors.RESET);

        Scanner sc = new Scanner(System.in);
        String userInputDate = sc.next().trim();

        splitIntoParagraphs(fileName);
        searchFunction(newList, userInputDate);
    }

}

