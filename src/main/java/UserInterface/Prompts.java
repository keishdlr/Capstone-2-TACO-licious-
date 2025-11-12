package UserInterface;

import models.TacoSize;
import models.Toppings;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Prompts {
    // declare a Scanner object for reading input from the console
    Scanner myScanner = new Scanner(System.in);

    // showHomeScreen:
    //   - print app title and welcome message
    System.out.println("Welcome to the Seven Serpent Taco shop");
    //   - print:
      while(true){
        //Display menu options:
        //   - prompt user for choice
        //   - return user input
        System.out.println("---🌮🐍---Home Screen---🌮🐍---");
        System.out.println("          1) New Order       ");
        System.out.println("          0) Exit            ");

        String selection = myScanner.nextLine().toUpperCase();
        switch (selection) {
            case "1":
                // call order menu
                showOrderMenu
                break;
            case "0":
                System.exit(0);
            default:
                System.out.println("Invalid input. Try again");
        }
    }

    // showOrderMenu:
    public static showOrderMenu(){
      while (true) {
        //Display menu options:
        System.out.println("--🗒️---Order Menu--🗒️---");
        System.out.println("      1) Add Taco           ");
        System.out.println("      2) Add Drink          ");
        System.out.println("      3) Add Chips & Salsa  ");
        System.out.println("      4) Checkout           ");
        System.out.println("      0) Cancel Order       ");

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
    //   - ask user
    //   - return user input
    public TacoSize promptForSize() {
        System.out.println("🌮 Choose your taco size:");
        System.out.println("   1) Single Taco");
        System.out.println("   2) 3-Taco Plate");
        System.out.println("   3) Burrito");

        while (true) {
            String input = myScanner.nextLine().trim();
            return switch (input) {
                //choice calls to enum class TacoSize
                case "1" -> TacoSize.SINGLE;
                case "2" -> TacoSize.THREE;
                case "3" -> TacoSize.BURRITO;
                default -> {
                    System.out.println("Invalid choice. Try again.");
                    yield null; //
                }
            };
        }
    }
    // promptForMeats:
    //   - possible loop (one or multiple meats?):
    //       - ask user to type of meat (or 'done' to finish)
    //       - ask if they want Extra meat? (yes or no)
    //   - return list of meats and extras
    // Example price chart embedded in each menu (adjust values to your canonical chart)
    public List<Toppings> promptForMeats() {
        List<Toppings> meats = new ArrayList<>();
        // Choices: name -> price when extra (base meat price is 0, extra applies)
        // If you want base meat premium (e.g., steak charged even if not extra) change price handling
        Map<Integer, String> meatNames = Map.of(
                1, "Carnitas",
                2, "Al Pastor",
                3, "Carne Asada",
                4, "Chicken",
                5, "Chorizo"
        );
        Map<String, Double> meatExtraPrice = Map.of(
                "Carnitas", 1.50,
                "Al Pastor", 1.50,
                "Carne Asada", 1.50,
                "Chicken", 1.50,
                "Chorizo", 1.50
        );

        System.out.println("🥩 Choose meats (type number or name). Type 'done' when finished.");
        while (true) {
            System.out.println("-- Meats --");
            meatNames.forEach((k, v) -> System.out.printf("%d) %s (+$%.2f if extra)%n", k, v, meatExtraPrice.get(v)));
            System.out.print("> ");
            String input = myScanner.nextLine().trim();
            if (input.equalsIgnoreCase("done")) break;
            if (input.isEmpty()) {
                System.out.println("Please enter a choice.");
                continue;
            }

            String chosenName = null;
            try {
                int idx = Integer.parseInt(input);
                chosenName = meatNames.get(idx);
            } catch (NumberFormatException ignored) {
                // try match by name
                for (String name : meatNames.values()) {
                    if (name.equalsIgnoreCase(input)) {
                        chosenName = name;
                        break;
                    }
                }
            }
            if (chosenName == null) {
                System.out.println("Invalid choice. Try again.");
                continue;
            }

            // ask for extra
            System.out.print("Extra " + chosenName + " (yes/no)? ");
            String extraResp = myScanner.nextLine().trim();
            boolean isExtra = extraResp.equalsIgnoreCase("yes") || extraResp.equalsIgnoreCase("y");

            double price = isExtra ? meatExtraPrice.getOrDefault(chosenName, 1.50) : 0.0;
            List<String> tags = new ArrayList<>();
            tags.add("meat");
            if (isExtra) tags.add("extra");

            Toppings topping = new Toppings(chosenName, isExtra, price, ToppingType.MEAT, tags);
            meats.add(topping);
            System.out.println("Added: " + topping);
        }
        return meats;
    }

    public List<Toppings> promptForCheeses() {
        List<Toppings> cheeses = new ArrayList<>();
        Map<Integer, String> cheeseNames = Map.of(
                1, "Queso Fresco",
                2, "Cheddar",
                3, "Monterey Jack",
                4, "Cotija",
                5, "Vegan Cheese"
                6, "Oaxaca"
        );
        Map<String, Double> cheeseExtraPrice = Map.of(
                "Queso Fresco", 0.75,
                "Cheddar", 0.75,
                "Monterey Jack", 0.75,
                "Cotija", 0.75,
                "Vegan Cheese", 1.00
                "oaxaca", .30
        );

        System.out.println("🧀 Choose cheeses (type number or name). Type 'done' when finished.");
        while (true) {
            System.out.println("-- Cheeses --");
            cheeseNames.forEach((k, v) -> System.out.printf("%d) %s (+$%.2f for extra)%n", k, v, cheeseExtraPrice.get(v)));
            System.out.print("> ");
            String input = myScanner.nextLine().trim();
            if (input.equalsIgnoreCase("done")) break;
            if (input.isEmpty()) {
                System.out.println("Please enter a choice.");
                continue;
            }

            String chosenName = null;
            try {
                int idx = Integer.parseInt(input);
                chosenName = cheeseNames.get(idx);
            } catch (NumberFormatException ignored) {
                for (String name : cheeseNames.values()) {
                    if (name.equalsIgnoreCase(input)) {
                        chosenName = name;
                        break;
                    }
                }
            }
            if (chosenName == null) {
                System.out.println("Invalid choice. Try again.");
                continue;
            }

            System.out.print("Extra " + chosenName + " (yes/no)? ");
            String extraResp = myScanner.nextLine().trim();
            boolean isExtra = extraResp.equalsIgnoreCase("yes") || extraResp.equalsIgnoreCase("y");

            double price = isExtra ? cheeseExtraPrice.getOrDefault(chosenName, 0.30) : 0.0;
            List<String> tags = new ArrayList<>();
            tags.add("cheese");
            if (isExtra) tags.add("extra");

            Toppings topping = new Toppings(chosenName, isExtra, price, ToppingType.CHEESE, tags);
            cheeses.add(topping);
            System.out.println("Added: " + topping);
        }
        return cheeses;
    }

    public List<Toppings> promptForSauces() {
        List<Toppings> sauces = new ArrayList<>();
        Map<Integer, String> sauceNames = Map.of(
                1, "Salsa Verde",
                2, "Salsa Roja",
                3, "Chipotle Mayo",
                4, "Crema",
                5, "Pico de Gallo"
        );
        Map<String, Double> saucePrice = Map.of(
                "Salsa Verde", 0.0,
                "Salsa Roja", 0.0,
                "Chipotle Mayo", 0.25,
                "Crema", 0.25,
                "Pico de Gallo", 0.0
        );

        System.out.println("🌶️ Choose sauces (type number or name). Type 'done' when finished.");
        while (true) {
            System.out.println("-- Sauces --");
            sauceNames.forEach((k, v) -> System.out.printf("%d) %s (+$%.2f)%n", k, v, saucePrice.get(v)));
            System.out.print("> ");
            String input = myScanner.nextLine().trim();
            if (input.equalsIgnoreCase("done")) break;
            if (input.isEmpty()) {
                System.out.println("Please enter a choice.");
                continue;
            }

            String chosenName = null;
            try {
                int idx = Integer.parseInt(input);
                chosenName = sauceNames.get(idx);
            } catch (NumberFormatException ignored) {
                for (String name : sauceNames.values()) {
                    if (name.equalsIgnoreCase(input)) {
                        chosenName = name;
                        break;
                    }
                }
            }
            if (chosenName == null) {
                System.out.println("Invalid choice. Try again.");
                continue;
            }

            double price = saucePrice.getOrDefault(chosenName, 0.0);
            List<String> tags = List.of("sauce");

            Toppings topping = new Toppings(chosenName, false, price, ToppingType.SAUCE, new ArrayList<>(tags));
            sauces.add(topping);
            System.out.println("Added: " + topping);
        }
        return sauces;
    }

    public List<Toppings> promptForSides() {
        List<Toppings> sides = new ArrayList<>();
        // Using Toppings for sides keeps types unified; alternatively create Side class if preferred
        Map<Integer, String> sideNames = Map.of(
                1, "Chips (small)",
                2, "Chips (large)",
                3, "Mexican Rice",
                4, "Refried Beans",
                5, "Soda"
        );
        Map<String, Double> sidePrices = Map.of(
                "Chips (small)", 1.50,
                "Chips (large)", 2.50,
                "Mexican Rice", 2.00,
                "Refried Beans", 2.00,
                "Soda", 1.75
        );

        System.out.println("🍟 Choose sides (type number or name). Type 'done' when finished.");
        while (true) {
            System.out.println("-- Sides --");
            sideNames.forEach((k, v) -> System.out.printf("%d) %s ($%.2f)%n", k, v, sidePrices.get(v)));
            System.out.print("> ");
            String input = myScanner.nextLine().trim();
            if (input.equalsIgnoreCase("done")) break;
            if (input.isEmpty()) {
                System.out.println("Please enter a choice.");
                continue;
            }

            String chosenName = null;
            try {
                int idx = Integer.parseInt(input);
                chosenName = sideNames.get(idx);
            } catch (NumberFormatException ignored) {
                for (String name : sideNames.values()) {
                    if (name.equalsIgnoreCase(input)) {
                        chosenName = name;
                        break;
                    }
                }
            }
            if (chosenName == null) {
                System.out.println("Invalid choice. Try again.");
                continue;
            }

            double price = sidePrices.getOrDefault(chosenName, 0.0);
            List<String> tags = List.of("side");

            // Use EXTRA type for sides or create a Side-specific model; here we reuse ToppingType.EXTRA
            Toppings side = new Toppings(chosenName, false, price, ToppingType.EXTRA, new ArrayList<>(tags));
            sides.add(side);
            System.out.println("Added: " + side);
        }
        return sides;
    }}
