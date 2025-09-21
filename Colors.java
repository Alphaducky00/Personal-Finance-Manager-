public class Colors {
    public static final String RESET = "\033[0m";
    public static final String RED = "\033[31m";     // Error messages
    public static final String GREEN = "\033[32m";   // Success messages  
    public static final String BLUE = "\033[34m";    // General messages
    public static final String YELLOW = "\033[33m";  // Warnings
    public static final String CYAN = "\033[36m";    // Info messages
    public static final String PURPLE = "\033[35m"; 

     public static void main(String[] args) {
        System.out.println(Colors.RED + "This is red text" + Colors.RESET);
        System.out.println(Colors.GREEN + "This is green text" + Colors.RESET);
        System.out.println(Colors.BLUE + "This is blue text" + Colors.RESET);
    }
}

