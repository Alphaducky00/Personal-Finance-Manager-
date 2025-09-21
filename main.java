import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class main {

    public static void clearFile(String fileName){
        try{
            PrintWriter writer = new PrintWriter(fileName);
            writer.print("");
            writer.close();
        }
        catch(FileNotFoundException e){
            System.out.println(Colors.RED + "File doesnot exist" + Colors.RESET);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        boolean flag = true;

        System.out.println(Colors.BLUE + "\nWelcome to the Personal Finance Manager App!" + Colors.RESET);

        while (flag) {

            System.out.println(Colors.BLUE + "How can we help you regarding your finances today?\n\n Select any of the following options(1-4):\n 1. Read your transaction files      2. Add a transaction file\n 3. Edit a transaction file      4. Clear a file\n 5. Exit the program" + Colors.RESET);

            Scanner sc = new Scanner(System.in);
            int user = sc.nextInt();
            sc.nextLine();

            try{
                if (user == 1){
                System.out.println(Colors.BLUE + "Type 1 to read the income file and 2 to read the expense file: " + Colors.RESET);

                int fileSelectToRead = sc.nextInt();
                sc.nextLine();

                try{
                    if (fileSelectToRead == 1){
                    System.out.println(Colors.GREEN + "Income file in reading mode opened successfully!\n" + Colors.RESET);
                    Reading.readIncomeFiles();
                }

                else if (fileSelectToRead == 2){
                    System.out.println(Colors.GREEN + "Expense file in reading mode opened successfully:\n" + Colors.RESET);
                    Reading.readExpenseFiles();
                }

                else{
                    throw new InvalidInputException();
                }
                }
                catch(InvalidInputException | InputMismatchException e){
                    System.out.println(Colors.RED + "Invalid input" + Colors.RESET);
                    System.out.println(e);
                    continue;
                }
            }

            else if (user == 2){
                System.out.println(Colors.BLUE + "You are now in the Add transaction file section: " + Colors.RESET);
                Adding.addTransaction(sc);
            }

            else if (user == 3){
                System.out.println(Colors.BLUE + "You are now in the Edit transaction files section:\n Which file do you wish to edit? Type 1 to edit the income file and 2 to edit the expense file" + Colors.RESET);

                

                try{
                    int fileSelectToEdit = sc.nextInt();
                    if (fileSelectToEdit == 1){
                    ReadSearch rs = new ReadSearch();
                    rs.searchParagraphs("income.txt");

                    FindEdit fe = new FindEdit();
                    fe.displayFoundParagraphs();
                    fe.editFoundParagraph();
                }

                else if (fileSelectToEdit == 2){
                    ReadSearch rs = new ReadSearch();
                    rs.searchParagraphs("expense.txt");

                    FindEdit fe = new FindEdit();
                    fe.displayFoundParagraphs();
                    fe.editFoundParagraph();
                }
                else{
                    throw new InvalidInputException();
                }
                }
                catch(InvalidInputException | InputMismatchException e){
                    System.out.println(Colors.RED + "Invalid input" + Colors.RESET);
                    System.out.println(e);
                    continue;
                }
                
                
            }

            else if(user == 4){
                System.out.println(Colors.BLUE + "Which file do you wish to clear? Type 1 for income file and 2 for the expense file: " + Colors.RESET);

                int deleteFile = sc.nextInt();

                try{
                    if (deleteFile == 1){
                    try{
                        System.out.println(Colors.YELLOW + "Are you sure you want to clear income file content? Type 1 for yes and 2 for no: " + Colors.YELLOW);

                    int deleteConfirmation = sc.nextInt();

                    if (deleteConfirmation == 2){
                        System.out.println(Colors.YELLOW + "File deletion operation cancelled" + Colors.RESET);
                        continue;
                    }

                    else if(deleteConfirmation == 1){
                        clearFile("income.txt");
                        System.out.println(Colors.GREEN + "Your income file's content has been cleared successfully\n" + Colors.RESET);
                        continue;
                    }

                    else{
                        throw new InvalidInputException();
                    }
                    }
                    catch(InvalidInputException | InputMismatchException e){
                        System.out.println(Colors.RED + "Invalid input" + Colors.RESET);
                        System.out.println(e);
                        continue;
                    }

                }

                else if (deleteFile == 2){
                    try{
                        System.out.println(Colors.YELLOW + "Are you sure you want to clear expense file content? Type 1 for yes and 2 for no: " + Colors.RESET);

                    int deleteConfirmation = sc.nextInt();

                    if (deleteConfirmation == 2){
                        System.out.println(Colors.CYAN + "File deletion operation cancelled" + Colors.RESET);
                        continue;
                    }

                    else if(deleteConfirmation == 1){
                        clearFile("expense.txt");
                        System.out.println(Colors.GREEN + "Your expense file's content has been cleared successfully\n" + Colors.RESET);
                        continue;
                    }

                    else{
                        throw new InvalidInputException();
                    }
                    }
                    catch(InvalidInputException | InputMismatchException e){
                        System.out.println(Colors.RED + "Invalid input" + Colors.RESET);
                        System.out.println(e);
                        continue;
                    }
                }

                else{
                    throw new InvalidInputException();
                }
                }
                catch(InvalidInputException | InputMismatchException e){
                    System.out.println(Colors.RED + "Invalid input" + Colors.RESET);
                    System.out.println(e);
                    continue;
                }
            }
            else if (user == 5){
                System.out.println(Colors.BLUE + "Thank you for using our app!" + Colors.RESET);
                flag = false;
            }
            else{
                throw new InvalidInputException();
            }

            }
            catch(InvalidInputException | InputMismatchException e){
                System.out.println(Colors.RED + "Invalid input" + Colors.RESET);
                System.out.println(e);
                continue;
            }
            
        }


    }
}
