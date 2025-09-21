import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class FindEdit {

    private boolean flag = true;
    private String updatedParagraph;
    private String fileName;
    private String paragraphToEdit;
    public static List<String> paragraph;
    public static List<Integer> paragraphNo;
    
    
    public void displayFoundParagraphs(){
        
        paragraph = ReadSearch.foundParagraphs;
        paragraphNo = ReadSearch.paragraphNumber;

        for (int i = 0; i < paragraph.size(); i++){
            System.out.println(Colors.CYAN + "Option " + (i + 1) + ": Original paragraph: " + paragraphNo.get(i) + Colors.RESET);
            System.out.println(paragraph.get(i));
        }
    }

    public void editFoundParagraph(){

        flag = true;

        if (ReadSearch.foundParagraphs == null ||ReadSearch.foundParagraphs.isEmpty()){
            System.out.println(Colors.CYAN + "No paragraphs found to edit. Please search first\n" + Colors.RESET);
            return;
        }

        while (flag){
            
            try{
                System.out.println(Colors.YELLOW + "Do you want to make edit? Type 1 for yes, 2 for no..." + Colors.RESET);

            Scanner sc = new Scanner(System.in);

            int editCofirmation = sc.nextInt();
            sc.nextLine();

            if (editCofirmation != 1 && editCofirmation != 2){
                throw new InvalidInputException();
            }

            else if (editCofirmation == 2){

                System.out.println(Colors.CYAN + "Your edit request has been cancelled" + Colors.RESET);
                break;
            }

            else if(editCofirmation == 1){
                
                while (flag){
                    try{
                        System.out.println(Colors.BLUE + "Type 1 to change the transcation amount and 2 to change the date: " + Colors.RESET);

                int userSelect = sc.nextInt();
                sc.nextLine();

                if (userSelect != 1 && userSelect != 2){
                    throw new InvalidInputException();
                }

                else if (userSelect == 1){
                    System.out.println(Colors.CYAN + "Take a look at your found paragraph: " + Colors.RESET);
                    displayFoundParagraphs();

                    System.out.println(Colors.YELLOW + "Which option number you wish for editing? (Avaliable options: 1 to " + paragraph.size() + ")" + Colors.RESET);
                    int selectedParagraphNum = sc.nextInt() - 1;
                    sc.nextLine();

                    if (selectedParagraphNum < 0 || selectedParagraphNum >= paragraph.size()){
                        System.out.println(Colors.RED + "Invalid option no. Please select between 1 and " + paragraph.size() + Colors.RESET);
                        continue;
                    }

                    paragraphToEdit = ReadSearch.foundParagraphs.get(selectedParagraphNum);
                    System.out.println(Colors.YELLOW + "You are editing the paragraph:\n" + paragraphToEdit + Colors.RESET);

                    String fileName = "";
                    if (paragraphToEdit.contains("Income")){
                        fileName = "income.txt";
                    }

                    else if(paragraphToEdit.contains("Expense")){
                        fileName = "expense.txt";
                    }


                    System.out.println(Colors.BLUE + "Enter the transcation amount you wish to replace in the selected paragraph: " + Colors.RESET);

                    double newTransactionAmount = sc.nextDouble();
                    String newLine = String.format("%.2f", newTransactionAmount);

                    String newTransString = "Transaction amount: " + newLine;

                    String lines [] = paragraphToEdit.split("\n");
                    
                    if (lines.length >= 3){
                        lines[1] = newTransString;
                    }

                    else{
                        System.out.println(Colors.RED + "Paragraph length is insufficient for editing" + Colors.RESET);
                        continue;
                    }

                    updatedParagraph = String.join("\n", lines);

                    System.out.println(Colors.YELLOW + "To confirm the changes type 1, and to undo the changes type 2: " + Colors.RESET);

                    int confirmation = sc.nextInt();
                    sc.nextLine();

                    try{
                        if (confirmation != 1 && confirmation != 2){
                            throw new InvalidInputException();
                        }

                        else if(confirmation == 1){
                            ReadSearch.foundParagraphs.set(selectedParagraphNum, updatedParagraph);
                            paragraph.set(selectedParagraphNum, updatedParagraph);

                            writeUpdatedParagraphsToFile(fileName);

                            System.out.println(Colors.CYAN + "Your updated details look like this: " + Colors.RESET);
                            System.out.println(updatedParagraph);
                            flag = false;
                        }

                        else if (confirmation == 2){

                            System.out.println(Colors.CYAN + "Changes undone as per your request: " + Colors.RESET);
                            flag = false;
                        }

                    }
                    catch(InvalidInputException e){
                        System.out.println(e.toString());
                        System.out.println(e.getMessage());
                        System.out.println(e);
                        continue;
                    }
                    catch(InputMismatchException e){
                        System.out.println(e.getMessage());
                        System.out.println(e);
                        continue;
                    }

                }

                else if (userSelect == 2){
                    System.out.println(Colors.BLUE + "Take a look at your found paragraph: " + Colors.RESET);
                    displayFoundParagraphs();

                    System.out.println(Colors.YELLOW + "Which option number you wish to edit? (Avaliable options: 1 to " + paragraph.size() + ")" + Colors.RESET);
                    int selectedParagraphNum = sc.nextInt() - 1;
                    sc.nextLine();

                    if (selectedParagraphNum < 0 || selectedParagraphNum >= paragraph.size()){
                        System.out.println(Colors.RED + "Invalid option no. Please select between 1 and " + paragraph.size() + Colors.RESET);
                        continue;
                    }

                    this.paragraphToEdit = paragraph.get(selectedParagraphNum);
                    System.out.println(Colors.CYAN + "You are editing the paragraph:\n" + paragraphToEdit + Colors.RESET);

                    fileName = "";
                    if (paragraphToEdit.contains("Income")){
                        fileName = "income.txt";
                    }

                    else if(paragraphToEdit.contains("Expense")){
                        fileName = "expense.txt";
                    }

                    System.out.println(Colors.BLUE + "Enter the date strictly in the format yyyy-MM-dd to replace with in the selected paragraph: " + Colors.RESET);

                    String newLine = sc.nextLine().trim();
                    if(newLine.matches("\\d{4}-\\d{2}-\\d{2}")){

                        String newDate = "Transaction date: " + newLine;

                        String lines [] = paragraphToEdit.split("\n");
                        
                        if (lines.length >= 3){
                            lines[2] = newDate;
                        }

                        else{
                            System.out.println(Colors.RED + "Paragraph length is insufficient for editing" + Colors.RESET);
                            continue;
                        }
                        this.updatedParagraph = String.join("\n", lines);

                        System.out.println(Colors.YELLOW + "To confirm the changes type 1, and to undo the changes type 2: " + Colors.RESET);

                        int confirmation = sc.nextInt();
                        sc.nextLine();

                    try{
                        if (confirmation != 1 && confirmation != 2){
                            throw new InvalidInputException();
                        }

                        else if(confirmation == 1){
                            ReadSearch.foundParagraphs.set(selectedParagraphNum, updatedParagraph);
                            paragraph.set(selectedParagraphNum, updatedParagraph);

                            writeUpdatedParagraphsToFile(fileName);

                            System.out.println(Colors.CYAN + "Your updated details look like this: " + Colors.RESET);
                            System.out.println(updatedParagraph);
                            flag = false;
                        }

                        else if (confirmation == 2){

                            System.out.println(Colors.CYAN + "Changes undone as per your request: " + Colors.RESET);
                            flag = false;
                        }

                    }
                    catch(InvalidInputException e){
                        System.out.println(e.toString());
                        System.out.println(e.getMessage());
                        System.out.println(e);
                        continue;
                    }
                    catch(InputMismatchException e){
                        System.out.println(e.getMessage());
                        System.out.println(e);
                        continue;
                    }

                    }
                    else{
                        System.out.println(Colors.RED + "The date format is wrong please try again" + Colors.RESET);
                        continue;
                    }
                }
                    }
                    catch(InvalidInputException e){
                        System.out.println(e.toString());
                        System.out.println(e.getMessage());
                        System.out.println(e);
                        continue;
                    }
                    catch(InputMismatchException e){
                        System.out.println(e.getMessage());
                        System.out.println(e);
                        continue;
                    }
                }

            }

            }
            catch(InvalidInputException e){
                System.out.println(e.toString());
                System.out.println(e.getMessage());
                System.out.println(e);
                continue;
            }
            catch(InputMismatchException e){
                System.out.println(e.getMessage());
                System.out.println(e);
                continue;
            }

        }
    }

    public void writeUpdatedParagraphsToFile(String fileName){

        List<String> allFileLines = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String line;
            while((line = reader.readLine()) != null){
                allFileLines.add(line);
             }

             List<Integer> matchingIndices = new ArrayList<>();
             String firstLineOfOriginal = paragraphToEdit.split("\n")[0];

             for (int i = 0; i < allFileLines.size(); i++){
                if (allFileLines.get(i).equals(firstLineOfOriginal)){
                    matchingIndices.add(i);
                }
             }

             int correctIndex = -1;
             for (int startIndex: matchingIndices){
                StringBuilder fileParaBuilder = new StringBuilder();
                for(int j = 0; j < 3; j++){
                    if((startIndex + j) < allFileLines.size()){
                        fileParaBuilder.append(allFileLines.get(startIndex + j));
                        if (j < 2) fileParaBuilder.append("\n");
                    }
                }

                String fileParagraph = fileParaBuilder.toString().trim();
                    String originalParagraph = paragraphToEdit.trim();

                    if (fileParagraph.equals(originalParagraph)){
                        correctIndex = startIndex;
                        break;
                    }
             }


             if (correctIndex == -1){
                System.out.println(Colors.RED + "Couldnot find the exact paragraph to edit in the file" + Colors.RESET);
                return;
             }


             String [] paragraphLines = updatedParagraph.split("\n");
             int endIndex = correctIndex + paragraphLines.length - 1;

             for (int i = endIndex; i >= correctIndex; i--){
                allFileLines.remove(i);
             }

             String newParagraphLines [] = updatedParagraph.split("\n");
             for (int i = 0; i < newParagraphLines.length; i++){
                allFileLines.add(correctIndex + i, newParagraphLines[i]);
             }

             try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))){
                for(String element: allFileLines){
                    writer.write(element);
                    writer.newLine();
                }
             }
             catch(IOException e){
                System.out.println(Colors.RED + "Error writing to file" + Colors.RESET);
             }


        }
        catch(IOException e){
            System.out.println(Colors.RED + "File not found" + Colors.RESET);
        }
        
    }
}
