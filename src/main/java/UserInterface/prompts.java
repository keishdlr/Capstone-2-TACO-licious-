package UserInterface;

import java.util.*;
import models.*;
import Utilities.*;

public class prompts {
    // HOME SCREEN
    Scanner myScanner = new Scanner(System.in);
    private Order currentOrder;

    public void showHomeScreen() {
        while (true) {
            System.out.println("--💸---Home Screen---💸--");
            System.out.println("      1) New Order       ");
            System.out.println("      0) Exit            ");

            String selection = myScanner.nextLine().trim();
            switch (selection) {
                case "1":
                    showOrderMenu();
                    break;
                case "2":
                    System.out.println("Come again!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid input. Try again!");
            }
        }
    }
    // ORDER MENU
    public void showOrderMenu() {
        while (true) {
            System.out.println("-----Order Menu-----");
            System.out.println("    1) Add Taco           ");
            System.out.println("    2) Add Drink          ");
            System.out.println("    3) Add Chips          ");
            System.out.println("    4) Signature Menu     ");
            System.out.println("    5) Checkout           ");
            System.out.println("    0) Cancel Order       ");

            String selection = myScanner.nextLine().trim();
            switch (selection) {
                case "1":
                    promptForTaco();
                    break;
                case "2":
                    promptForDrinkSize(myScanner);
                    break;
                case "3":
                    promptForChips();
                    break;
                case "4":
                    promptForSignatureMenu();
                    break;
                case "":
                    checkout();
                    break;
                case "0":
                    System.out.println("Order Cancelled");
                    this.currentOrder = new Order();
                    return;
                default:
                    System.out.println("Invalid input. Try again");
            }
        }
    }
    // helper: build a taco by calling the prompts above and add to order
    public void promptForTaco() {
        TacoSize size = promptForSize();
        Taco taco = new Taco(size); // assuming constructor uses size
        String tortilla = promptForTortillaType();
        taco.addTopping(new Toppings(tortilla, 0.0, ToppingType.TORTILLA)); // add tortilla
        List<Toppings> meats = promptForMeats(size);
        for (Toppings t : meats) taco.addTopping(t);
        List<Toppings> cheeses = promptForCheeses(size);
        for (Toppings c : cheeses) taco.addTopping(c);
        List<Toppings> sauces = promptToRemoveSauces();
        for (Toppings s : sauces) taco.addTopping(s);
        boolean deepFried = promptForDeepFried();
        if (deepFried) taco.setDeepFried(true); // or add a topping if needed
        this.currentOrder.addTaco(taco);
        System.out.println("Added taco: " + taco.getSummary());
    }
    // PROMPT SIZE
    public TacoSize promptForSize() {
        System.out.println("🌮 Choose your taco size:");
        System.out.println("1) Single Taco ($" + String.format("%.2f", Pricing.meatBasePrice(TacoSize.SINGLE)) + ")");
        System.out.println("2) 3-Taco Plate ($" + String.format("%.2f", Pricing.meatBasePrice(TacoSize.THREE)) + ")");
        System.out.println("3) Burrito ($" + String.format("%.2f", Pricing.meatBasePrice(TacoSize.BURRITO)) + ")");
        while (true) {
            String input = myScanner.nextLine().trim();
            if (input.equals("1")) return TacoSize.SINGLE;
            if (input.equals("2")) return TacoSize.THREE;
            if (input.equals("3")) return TacoSize.BURRITO;
            System.out.println("Invalid choice. Enter 1, 2, or 3.");
        }
    }
    public String promptForTortillaType() {
        System.out.println("🌯 Choose your tortilla type:");
        System.out.println("1) Corn");
        System.out.println("2) Flour");
        System.out.println("3) Hard Shell");
        System.out.println("4) Bowl (no tortilla)");
        while (true) {
            String input = myScanner.nextLine().trim();
            switch (input) {
                case "1":
                case "corn":
                    return "Corn";
                case "2":
                case "flour":
                    return "Flour";
                case "3":
                case "hard shell":
                    return "Hard Shell";
                case "4":
                case "bowl":
                    return "Bowl";
                default:
                    System.out.println("Invalid choice. Please enter 1–4 or type the name.");
            }
        }
    }
    // MEATS (uses HashMap for menu)
    public List<Toppings> promptForMeats(TacoSize size) {
        List<Toppings> meats = new ArrayList<>();
        HashMap<Integer, String> meatMenu = new HashMap<Integer, String>();
        meatMenu.put(1, "Carnitas");
        meatMenu.put(2, "Al Pastor");
        meatMenu.put(3, "Carne Asada");
        meatMenu.put(4, "Chicken");
        meatMenu.put(5, "Chorizo");

        System.out.println("🥩 Choose meats (type number or name). Type 'done' when finished.");
        while (true) {
            // Show menu with meat name, price, cost for extra.
            for (Map.Entry<Integer, String> entry : meatMenu.entrySet()) {
                int key = entry.getKey();
                String name = entry.getValue();
                double base = Pricing.meatBasePrice(size);
                double extra = Pricing.meatExtraPrice(size);
                System.out.printf("%d) %s - base $%.2f, extra +$%.2f%n", key, name, base, extra);
            }
            // Get user input
            String input = myScanner.nextLine().trim();
            if (input.equalsIgnoreCase("done")) break; // if they type done, exit loop.
            int choice = Integer.parseInt(input); // otherwise take input if number parse to string
            String chosenName = meatMenu.get(choice);
            // Ask about extra
            System.out.print("Extra " + chosenName + "? (yes/no): "); // ask if they want extra of the meat
            String extraMeat = myScanner.nextLine().trim(); // take their answer
            boolean isExtra = extraMeat.equalsIgnoreCase("yes");
            // Calculate price
            double price = Pricing.meatBasePrice(size);
            if (isExtra) price += Pricing.meatExtraPrice(size); // if extra add the cost
            // Add topping
            meats.add(new Toppings(chosenName, price, ToppingType.MEAT));
            System.out.printf("Added: %s ($%.2f)%n", chosenName, price); // confirmation of added meat
        }
        return meats;
    }
    // CHEESES. using hashmap also for the cheese
    public List<Toppings> promptForCheeses(TacoSize size) {
        List<Toppings> cheeses = new ArrayList<>();
        HashMap<Integer, String> cheeseMenu = new HashMap<Integer, String>();
        cheeseMenu.put(1, "Queso Fresco");
        cheeseMenu.put(2, "Cheddar");
        cheeseMenu.put(3, "Monterey Jack");
        cheeseMenu.put(4, "Cotija");
        cheeseMenu.put(5, "Vegan Cheese");

        System.out.println("🧀 Choose cheeses (type number or name). Type 'done' when finished.");
        while (true) {
            // Show cheese menu
            for (Map.Entry<Integer, String> entry : cheeseMenu.entrySet()) {
                int key = entry.getKey();
                String name = entry.getValue();
                double base = Pricing.cheeseBasePrice(size);
                double extra = Pricing.cheeseExtraPrice(size);
                System.out.printf("%d) %s - base $%.2f, extra +$%.2f%n", key, name, base, extra);
            }
            // Get user input
            String input = myScanner.nextLine().trim();
            if (input.equalsIgnoreCase("done")) break;
            int choice = Integer.parseInt(input); // assumes valid number
            String chosenName = cheeseMenu.get(choice);
            // Ask about extra
            System.out.print("Extra " + chosenName + "? (yes/no): ");
            String extraResp = myScanner.nextLine().trim();
            boolean isExtra = extraResp.equalsIgnoreCase("yes");
            // Calculate price
            double price = Pricing.cheeseBasePrice(size);
            if (isExtra) price += Pricing.cheeseExtraPrice(size);
            // Add topping
            cheeses.add(new Toppings(chosenName, price, ToppingType.CHEESE));
            System.out.printf("Added: %s ($%.2f)%n", chosenName, price);
        }
        return cheeses;
    }
    // remove sauces
    public List<Toppings> promptToRemoveSauces() {
        // Default sauces included with every taco
        List<Toppings> sauces = new ArrayList<>();
        sauces.add(new Toppings("Salsa Verde", 0.0, ToppingType.SAUCE));
        sauces.add(new Toppings("Salsa Roja", 0.0, ToppingType.SAUCE));
        sauces.add(new Toppings("Chipotle", 0.0, ToppingType.SAUCE));
        sauces.add(new Toppings("Habanero (on the side)", 0.0, ToppingType.SAUCE));
        sauces.add(new Toppings("Mild (on the side)", 0.0, ToppingType.SAUCE));
        sauces.add(new Toppings("Extra hot (on the side)", 0.0, ToppingType.SAUCE));

        System.out.println("🌶️ These sauces come with your taco by default: do you want to remove any?");
        for (int i = 0; i < sauces.size(); i++) { // display sauces
            System.out.printf("%d) %s%n", i + 1, sauces.get(i).getName());
        }
        System.out.println("Type the number or name of any sauce you want to remove.");
        System.out.println("Type 'done' when finished.");
        while (true) {
            String input = myScanner.nextLine().trim();
            if (input.equalsIgnoreCase("done")) break; // exit loop if type done
            if (input.isEmpty()) {
                System.out.println("Please enter a sauce number or name."); // if input is empty prompt them again
                continue;
            }
            boolean removed = false;
            // Try to remove by number
            try {
                int idx = Integer.parseInt(input) - 1;
                if (idx >= 0 && idx < sauces.size()) {
                    Toppings removedSauce = sauces.remove(idx);
                    System.out.println("Removed: " + removedSauce.getName());
                    removed = true;
                }
            } catch (NumberFormatException e) {
                // Try to remove by name
                Iterator<Toppings> it = sauces.iterator();
                while (it.hasNext()) {
                    Toppings t = it.next();
                    if (t.getName().equalsIgnoreCase(input)) {
                        it.remove();
                        System.out.println("Removed: " + t.getName());
                        removed = true;
                        break;
                    }}
            }
            if (!removed) {
                System.out.println("No matching sauce found. Try again.");
            }
        }
        return sauces;
    }
    // Deep fried
    public boolean promptForDeepFried() {
        while (true) {
            System.out.println("Would you like your taco deep-fried? (yes/no)");
            String input = myScanner.nextLine().trim().toLowerCase();
            if (input.equals("yes") || input.equals("y")) return true;
            if (input.equals("no") || input.equals("n")) return false;
            System.out.println("Please enter 'yes' or 'no'.");
        }
    }
    public String promptForDrinkSize(Scanner myScanner) {
        System.out.println("🥤 Choose drink size: 1) Small ($2.00) 2) Medium ($2.50) 3) Large ($3.00)");
        String input = myScanner.nextLine().trim();
        switch (input.toLowerCase()) {
            case "1":
            case "small":
                return "Small";
            case "2":
            case "medium":
                return "Medium";
            case "3":
            case "large":
                return "Large";
            default:
                System.out.println("Invalid choice. Try again.");
                return promptForDrinkSize(myScanner); // recursive retry
        }
    }
    public void promptForChips () {
        System.out.print("Would you like chips and salsa on the side? (yes/no): ");
        String response = myScanner.nextLine().trim();
        if (response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("y")) {
            ChipsAndSalsa c = new ChipsAndSalsa("Chips and salsa", 1.50);
            this.currentOrder.addChips(c);
            System.out.println("Added: Chips and salsa ($1.50)");
        } else {
            System.out.println("No chips added.");
        }}
    public Taco promptForSignatureMenu() {
        System.out.println("🌮 Choose a Signature Taco:");
        for (int i = 0; i < SignatureMenuOptions.values().length; i++) {
            System.out.println((i + 1) + ") " + formatName(SignatureMenuOptions.values()[i]));
        }
        while (true) {
            String input = myScanner.nextLine().trim();
            try {
                int choice = Integer.parseInt(input);
                SignatureMenuOptions selected = SignatureMenuOptions.values()[choice - 1];
                return selected.build();
            } catch (Exception e) {
                System.out.println("Invalid choice. Try again.");
            }
        }
    }
    private String formatName(SignatureMenuOptions type) {
        return switch (type) {
            case STREET_TACOS -> "Street Tacos";
            case SUPER_BURRITO -> "Super Burrito";
        };
    }
    // checkout: show order summary and save receipt (if ReceiptWriter exists)
    public void checkout () {
        System.out.println("\n🧾 Order Summary:");
        System.out.println(this.currentOrder.getOrderSummary());
        while (true) {
            System.out.println("Would you like to:");
            System.out.println("1) Confirm Order and save receipt");
            System.out.println("2) Cancel and discard order");
            String input = myScanner.nextLine().trim();
            switch (input) {
                case "1":
                    try {
                        Utilities.ReceiptWriter.saveReceipt(this.currentOrder);
                        System.out.println("✅ Receipt saved. How would you like a copy? (print,text).");
                    } catch (Exception e) {
                        System.out.println("⚠️ Could not save receipt: " + e.getMessage());
                    }
                    this.currentOrder = new Order(); // reset for next customer
                    return; // back to home
                case "2":
                    System.out.println("❌ Order discarded. Returning to home screen.");
                    this.currentOrder = new Order(); // discard and reset
                    return;
                default:
                    System.out.println("Invalid input. Please enter 1 or 2.");
            }
        }
    }
}