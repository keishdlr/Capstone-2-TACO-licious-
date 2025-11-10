package UserInterface;

import java.util.Scanner;

public class Prompts {
    // declare a Scanner object for reading input from the console
    Scanner myScanner = new Scanner(System.in);

    // constructor:

    // showHomeScreen:
    //   - print app title and welcome message
    System.out.println("Welcome to the Ichimaru shop");
    //   - print:
      while (true) {
        //Display menu options:

        System.out.println("--💸---Home Screen---💸--");
        System.out.println("      1) New Order       ");
        System.out.println("      0) Exit            ");

        //       1) New Order
        //       0) Exit
        //   - prompt user for choice
        //   - return user input

        String selection = myScanner.nextLine().toUpperCase();
        switch (selection) {
            case "1":
                // call order menu
                break;
            case "0":
                System.exit(0);
            default:
                System.out.println("Invalid input. Try again");
        }
    }


    // showOrderMenu:
      while (true) {
        //Display menu options:

        System.out.println("-----Order Menu-----");
        System.out.println("    1) Add Sandwich       ");
        System.out.println("    2) Add Drink          ");
        System.out.println("    3) Add Chips          ");
        System.out.println("    4) Checkout           ");
        System.out.println("    0) Cancel Order       ");

        String selection = myScanner.nextLine().toUpperCase();
        switch (selection) {
            case "1":
                     // if D is selected then it will run add deposit method
                break;
            case "2":
                     // if p is selected then it will run make payment method
                break;
            case "3":
                    // if L is selected then it will run Ledger method and open the ledger screen
                break;
            case "4":
                    // if X is selected then it will exit the program
                break;
            case "0":
                System.exit(0);
                break;
            default:
                System.out.println("Invalid input. Try again");
        }
    }


    // promptForTortillaType:
    //   - print list of bread types
    //   - ask user to type one in (e.g., "white")
    //   - return as user input

    // promptForSize:
    //   - ask user: "What size? (4, 8, or 12 inches)"
    //   - return user input

    // promptForMeats:
    //   - possible loop (one or multiple meats?):
    //       - ask user to type of meat (or 'done' to finish)
    //       - ask if they want Extra meat? (yes or no)"
    //   - return list of meats and extras

    // prompt for cheese

    //prompt for topping

}
