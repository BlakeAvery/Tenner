import ufoproducts.util.*;
import java.util.*;

public class Main {
    private static Scanner input = new Scanner(System.in);
    private static final float VER = 0.5F;
    private static POS pos = new POS();
    private static ArrayList<Employee> employees = new ArrayList<Employee>();
    private static void loginPrompt() {
        while(true) {
            System.out.print("login: ");
            try {
                int employee = input.nextInt();
                input.nextLine();
                for(int x = 0; x < employees.size(); x++) {
                    if(employees.get(x).id == employee) {
                        OrderScreen oS = new OrderScreen(employees.get(x), pos);
                        oS.start();
                    }
                }
            } catch(InputMismatchException e) {
                input.nextLine();
                break;
            }
        }
    }
    private static void tennerAbout() {
        System.out.println("Tenner Point-of-Sale");
        System.out.println("Version " + VER);
        System.out.println("©UFO Products 2018");
        System.out.println("Tenner is licensed under the MIT License.");
    }
    public static void main(String[] args) {
        System.out.println("Java utils init");
        employees.add(new Employee("Admin", 0));
        System.out.println("Welcome to Tenner v" + VER);
        while(true) {
            System.out.print("tenner> ");
            String in = input.nextLine();
            switch(in) {
                case "login":
                    loginPrompt();
                    break;
                case "info":
                    tennerAbout();
                    break;
                case "exit":
                    System.out.println("Terminating Tenner.");
                    System.exit(0);
                default:
                    System.out.println("Invalid command.");
                    break;
            }
        }
    }
}
