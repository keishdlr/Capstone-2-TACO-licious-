package UserInterface;

import models.TacoSize;
import models.Toppings;

import java.util.*;

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
    public List<Toppings> promptForMeats(TacoSize size) {
        List<Toppings> meats = new ArrayList<>();
        System.out.println("🥩 Choose meats (type number or name). Type 'done' when finished.");

        LinkedHashMap<Integer, String> meatMenu = new LinkedHashMap<>();
        meatMenu.put(1, "Carnitas");
        meatMenu.put(2, "Al Pastor");
        meatMenu.put(3, "Carne Asada");
        meatMenu.put(4, "Chicken");
        meatMenu.put(5, "Chorizo");

        while (true) {
            System.out.println("-- Meats --");
            meatMenu.forEach((k, v) -> System.out.printf("%d) %s (base $%.2f; extra +$%.2f)%n",
                    k, v, meatBasePrice(size), meatExtraPrice(size)));
            System.out.print("> ");
            String input = myScanner.nextLine().trim();
            if (input.equalsIgnoreCase("done")) break;
            if (input.isEmpty()) { System.out.println("Please enter a choice."); continue; }

            String chosenName = null;
            try {
                int idx = Integer.parseInt(input);
                chosenName = meatMenu.get(idx);
            } catch (NumberFormatException ignored) {
                for (String name : meatMenu.values()) {
                    if (name.equalsIgnoreCase(input)) { chosenName = name; break; }
                }
            }
            if (chosenName == null) { System.out.println("Invalid choice. Try again."); continue; }

            System.out.print("Add " + chosenName + " (yes/no)? ");
            String addResp = myScanner.nextLine().trim();
            if (!addResp.equalsIgnoreCase("yes") && !addResp.equalsIgnoreCase("y")) {
                System.out.println("Skipped.");
                continue;
            }

            System.out.print("Extra " + chosenName + " (yes/no)? ");
            String extraResp = myScanner.nextLine().trim();
            boolean isExtra = extraResp.equalsIgnoreCase("yes") || extraResp.equalsIgnoreCase("y");

            double price = meatBasePrice(size) + (isExtra ? meatExtraPrice(size) : 0.0);
            List<String> tags = new ArrayList<>(); tags.add("meat"); if (isExtra) tags.add("extra");

            Toppings topping = new Toppings(chosenName, isExtra, price, ToppingType.MEAT, tags);
            meats.add(topping);
            System.out.println("Added: " + topping);
        }
        return meats;
    }
    }
    // prompt for cheese
    public List<Toppings> promptForCheeses(TacoSize size) {
        List<Toppings> cheeses = new ArrayList<>();
        LinkedHashMap<Integer, String> cheeseMenu = new LinkedHashMap<>();
        cheeseMenu.put(1, "Queso Fresco");
        cheeseMenu.put(2, "Cheddar");
        cheeseMenu.put(3, "Monterey Jack");
        cheeseMenu.put(4, "Cotija");
        cheeseMenu.put(5, "Vegan Cheese");

        System.out.println("🧀 Choose cheeses (type number or name). Type 'done' when finished.");
        while (true) {
            cheeseMenu.forEach((k, v) -> System.out.printf("%d) %s (base $%.2f; extra +$%.2f)%n",
                    k, v, cheeseBasePrice(size), cheeseExtraPrice(size)));
            System.out.print("> ");
            String input = myScanner.nextLine().trim();
            if (input.equalsIgnoreCase("done")) break;
            if (input.isEmpty()) { System.out.println("Please enter a choice."); continue; }

            String chosen = null;
            try { chosen = cheeseMenu.get(Integer.parseInt(input)); }
            catch (Exception ignored) {
                for (String name : cheeseMenu.values()) if (name.equalsIgnoreCase(input)) { chosen = name; break; }
            }
            if (chosen == null) { System.out.println("Invalid choice. Try again."); continue; }

            System.out.print("Extra " + chosen + " (yes/no)? ");
            boolean isExtra = myScanner.nextLine().trim().equalsIgnoreCase("yes");
            double price = cheeseBasePrice(size) + (isExtra ? cheeseExtraPrice(size) : 0.0);
            List<String> tags = new ArrayList<>(); tags.add("cheese"); if (isExtra) tags.add("extra");
            cheeses.add(new Toppings(chosen, isExtra, price, ToppingType.CHEESE, tags));
            System.out.println("Added: " + cheeses.get(cheeses.size()-1));
        }
        return cheeses;
    }
    // prompt for sauce
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
    // prompt for deletion of toppings if desired.



        return sides;
        // prompt for drinks
        public String promptForDrinkSize() {
            System.out.println("🥤 Choose drink size: 1) Small ($2.00) 2) Medium ($2.50) 3) Large ($3.00)");
            while (true) {
                String input = myScanner.nextLine().trim();
                switch (input) {
                    case "1", "small" -> { return "Small"; }
                    case "2", "medium" -> { return "Medium"; }
                    case "3", "large" -> { return "Large"; }
                    default -> System.out.println("Invalid choice. Try again.");
                }
            }
        }
    }}
