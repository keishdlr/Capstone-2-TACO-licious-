package UserInterface;

import models.TacoSize;
import models.Toppings;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Prompts {
    // declare a Scanner object for reading input from the console
    Scanner myScanner = new Scanner(System.in);

    // constructor:

    // showHomeScreen:
    //   - print app title and welcome message
    System.out.println("Welcome to the Seven Serpent Taco shop");
    //   - print:
      while (true) {
        //Display menu options:
        //   - prompt user for choice
        //   - return user input
        System.out.println("---🌮🐍---Home Screen---🌮🐍---");
        System.out.println("           1) New Order       ");
        System.out.println("           0) Exit            ");

        String selection = myScanner.nextLine().toUpperCase();
        switch (selection) {
            case "1":
                // call order menu
                break;
            case "0":
                System.exit(0);
            default:
                System.out.println("Invalid input. Try again");
        }}

    // showOrderMenu:
    public orderMenu(){
      while (true) {
        //Display menu options:

        System.out.println("-----Order Menu-----");
        System.out.println("    1) Add Taco       ");
        System.out.println("    2) Add Drink          ");
        System.out.println("    3) Add Chips & Salsa         ");
        System.out.println("    4) Checkout           ");
        System.out.println("    0) Cancel Order       ");

        String selection = myScanner.nextLine().toUpperCase();
        switch (selection) {
            case "1":
                     // taco screen, shell-size-toppings-deepfried -back to menu
                break;
            case "2":
                     // drink size and flavor
                break;
            case "3":
                    // salsa type
                break;
            case "4":
                    //display order details and price-confirm(create receipt file /delete(delete back to home screen)
                break;
            case "0":
                System.exit(0);
                break;
            default:
                System.out.println("Invalid input. Try again");
        }
    }}

    // promptForSize:
    //   - ask user: "What size? (4, 8, or 12 inches)"
    //   - return user input
    public TacoSize promptForSize() {
        System.out.println("🌮 Choose your taco size:");
        System.out.println("1) Single Taco");
        System.out.println("2) 3-Taco Plate");
        System.out.println("3) Burrito");

        while (true) {
            String input = myScanner.nextLine().trim();
            return switch (input) {
                case "1" -> TacoSize.SINGLE;
                case "2" -> TacoSize.THREE;
                case "3" -> TacoSize.BURRITO;
                default -> {
                    System.out.println("Invalid choice. Try again.");
                    yield null;
                }
            };
        }
    }
    // promptForMeats:
    //   - possible loop (one or multiple meats?):
    //       - ask user to type of meat (or 'done' to finish)
    //       - ask if they want Extra meat? (yes or no)"
    //   - return list of meats and extras
    public List<Toppings> promptForMeats() {
        List<Toppings> meats = new ArrayList<>();
        System.out.println("🥩 Let's add some meats to your taco!");

        while (true) {
            System.out.print("Enter a meat (or type 'done' to finish): ");
            String input = myScanner.nextLine().trim();

            if (input.equalsIgnoreCase("done")) break;
            if (input.isBlank()) {
                System.out.println("⚠️ Please enter a valid meat name.");
                continue;
            }

            System.out.print("Do you want extra " + input + "? (yes/no): ");
            String extra = myScanner.nextLine().trim().toLowerCase();
            boolean isExtra = extra.equals("yes");

            double price = isExtra ? 1.50 : 0.0;
            List<String> tags = isExtra ? List.of("extra", "premium") : List.of("standard");

            Toppings meat = new Toppings(input, isExtra, price, ToppingType.MEAT, tags);
            meats.add(meat);

            System.out.println("✅ Added: " + meat);
        }
        return meats;
    }

    // prompt for cheese
    public List<Toppings> promptForCheeses() {
        List<Toppings> cheeses = new ArrayList<>();
        System.out.println("🧀 Add cheeses (type 'done' to finish):");

        while (true) {
            System.out.print("Cheese name: ");
            String input = myScanner.nextLine().trim();
            if (input.equalsIgnoreCase("done")) break;

            System.out.print("Extra cheese? (yes/no): ");
            boolean isExtra = myScanner.nextLine().trim().equalsIgnoreCase("yes");
            double price = isExtra ? 0.75 : 0.0;
            List<String> tags = isExtra ? List.of("extra") : List.of("standard");

            cheeses.add(new Toppings(input, isExtra, price, ToppingType.CHEESE, tags));
        }
        return cheeses;
    }
}
