import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class InvalidInputException extends Exception{

    public String toString(){
        return Colors.RED + "Invalid input" + Colors.RESET;
    }

    public String getMessage(){
        return Colors.RED + "Please enter a valid input in the field" + Colors.RESET;
    }
}

class InvalidDateException extends Exception{

    public String toString(){
        return Colors.RED + "Invalid date entity" + Colors.RESET;
    }

    public String getMessage(){
        return Colors.RED + "Please enter a valid date entity\n" + Colors.RESET;
    }
}

class Adding{

    private static boolean flag = true;
    private double transaction; 
    public String finalAmount;
    public String userSetDate;
    private static ArrayList<String> transactionArray = new ArrayList<>();  
    private static ArrayList<String> transactionArray2 = new ArrayList<>();

    public double getTransaction(){
        return transaction;
    }

    public void setTransaction(double transaction){
        this.transaction = transaction;
    }

    public static void addTransaction(Scanner sc){

        flag = true;
        transactionArray.clear();
        transactionArray2.clear();
     
       while(flag){
        try{

        System.out.println(Colors.BLUE + "Enter the amount of the transaction: " + Colors.RESET);

        double amount = sc.nextDouble();
        amount = Math.round(amount * 100.0) / 100.0;

        DecimalFormat dfFormat = new DecimalFormat("#.00");
        String formattedAmount = dfFormat.format(amount);

        if(amount <= 0){
            System.out.println(Colors.CYAN + "Transcation amount cannot be zero, please enter a valid amount" + Colors.RESET);
            throw new InvalidInputException();
        }

            System.out.println(Colors.BLUE + "Enter the date the ranging 1- 31 when the transaction was made: " + Colors.RESET);
            int date = sc.nextInt();
            sc.nextLine();
            if(date > 31 || date < 1){
                throw new InvalidDateException();
            }

            System.out.println(Colors.BLUE + "Enter the month of the year (1-12) in which the transaction was made: " + Colors.RESET);
            int month = sc.nextInt();
            sc.nextLine();
            if(month > 12 || month < 1){
                throw new InvalidDateException();
            }

            System.out.println(Colors.BLUE + "Enter a year when the transaction amount was made: " + Colors.RESET);
            int year = sc.nextInt();
            sc.nextLine();
            String userSetDate;

            if(year < 0){
                throw new InvalidDateException();
            }

            try{
                LocalDate dl = LocalDate.of(year, month, date);

                DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                userSetDate = dl.format(df);
            }
            catch(DateTimeException e){
                throw new InvalidDateException();
            }

            System.out.println(Colors.YELLOW + "Do you want to save this transaction\n Type 1 for yes and 2 for no? " + Colors.RESET);
            
            int confirmation = sc.nextInt();
            sc.nextLine();

            if (confirmation == 2){

                System.out.println(Colors.GREEN + "Your transaction adding request has been cancelled\n" + Colors.RESET);
                break;
            }
            else if(confirmation != 2 && confirmation != 1){
                throw new InvalidInputException();
            }

            System.out.println(Colors.YELLOW + "Is this transaction in the form of income or expense?\n Type 1 for income or 2 for expense" + Colors.RESET);

            int user = sc.nextInt();
            sc.nextLine();

            if (user == 1){

                transactionArray.add("Income");
                transactionArray.add(formattedAmount);
                transactionArray.add(userSetDate);

                transactionArray2.add("Transaction type: ");
                transactionArray2.add("Transaction amount: ");
                transactionArray2.add("Transaction date: ");
            
                try(BufferedWriter writer = new BufferedWriter(new FileWriter("income.txt", true))){
                    for(int i = 0; i < transactionArray.size(); i++){
                        writer.write(transactionArray2.get(i) + transactionArray.get(i));
                        writer.newLine();
                    }
                    writer.newLine();
                }

                System.out.println(Colors.GREEN + "Your transaction history has been saved successfully\n" + Colors.RESET);
                flag = false;

            }
            else if(user == 2){

                transactionArray.add("Expense");
                transactionArray.add(formattedAmount);
                transactionArray.add(userSetDate);

                transactionArray2.add("Transaction type: ");
                transactionArray2.add("Transaction amount: ");
                transactionArray2.add("Transaction date: ");
            
                try(BufferedWriter writer = new BufferedWriter(new FileWriter("expense.txt", true))){
                    for(int i = 0; i < transactionArray.size(); i++){
                        writer.write(transactionArray2.get(i) + transactionArray.get(i));
                        writer.newLine();
                    }
                    writer.newLine();
                }

                System.out.println(Colors.GREEN + "Your transaction history has been saved successfully\n" + Colors.RESET);
                flag = false;
            }
            else{
                throw new InvalidInputException();
            }
        }
        catch(InvalidInputException e){
        System.out.println(e.toString());
        System.out.println(e.getMessage());
        continue;
    }
    catch(InvalidDateException e){
        System.out.println(e.toString());
        System.out.println(e.getMessage());
        continue;
    }
    catch(IOException e){
        System.out.println(Colors.RED + "Some error occurred with the file" + Colors.RESET);
        System.out.println(e);
        continue;
    }
    catch(InputMismatchException e){
        System.out.println(Colors.RED + "Invalid form of input please try again\n" + Colors.RESET);
        sc.nextLine();
        continue;
    }
       }

    }
}




