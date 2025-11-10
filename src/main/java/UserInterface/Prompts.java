package UserInterface;

import java.time.LocalTime;
import java.util.Scanner;

public class Prompts {
    // declare a Scanner object for reading input from the console
    Scanner myScanner = new Scanner(System.in);

    int hour = LocalTime.now().getHour();

    String greeting;

        if (hour >= 5 && hour < 12) {
        greeting = "Good morning ☕🌄";
    } else if (hour >= 12 && hour < 17) {
        greeting = "Good afternoon ☀️";
    } else if (hour >= 17 && hour < 21) {
        greeting = "Good evening 🌇";
    } else {
        greeting = "Good night 🌙";
    }
        System.out.println(greeting);

    // constructor:

    // showHomeScreen:
    //   - print app title and welcome message
    System.out.println("Welcome to the Ichimaru shop");
    //   - print:
    System.out.println("");
    //       1) New Order
    //       0) Exit
    //   - prompt user for choice
    //   - return user input

    // showOrderMenu:
    //   - print:
    //       1) Add Sandwich
    //       2) Add Drink
    //       3) Add Chips
    //       4) Checkout
    //       0) Cancel Order
    //   - prompt user for choice
    //   - return user input

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
