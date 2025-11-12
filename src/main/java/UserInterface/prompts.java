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
                System.out.println("    4) Checkout           ");
                System.out.println("    0) Cancel Order       ");

                String selection = myScanner.nextLine().trim();
                switch (selection) {
                    case "1":
                        promptForTaco();
                        break;
                    case "2":
                        promptForDrink();
                        break;
                    case "3":
                        promptForChips();
                        break;
                    case "4":
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
        Taco taco = new Taco(); // assumes Taco constructor sets base price from size
        String tortilla = promptForTortillaType();
        List<Toppings> meats = promptForMeats(size);
        for (Toppings t : meats) taco.addTopping(t);
        List<Toppings> cheeses = promptForCheeses(size);
        for (Toppings c : cheeses) taco.addTopping(c);
        List<Toppings> sauces = removeSauces();
        for (Toppings s : sauces) taco.addTopping(s);
        List<Toppings> extras = promptForSides(); // reuse sides prompt for extras if wanted
        for (Toppings e : extras) taco.addTopping(e);
        boolean deepFried = promptForDeepFried();

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
        // PROMPT FOR MEATS (uses HashMap for menu)
        public List<Toppings> promptForMeats(TacoSize size) {
            List<Toppings> meats = new ArrayList<>();
            HashMap<Integer, String> meatMenu = new HashMap<Integer, String>();
            meatMenu.put(1, "Carnitas");
            meatMenu.put(2, "Al Pastor");
            meatMenu.put(3, "Carne asada");
            meatMenu.put(4, "Chicken");
            meatMenu.put(5, "Chorizo");

            System.out.println("🥩 Choose meats (type number or name). Type 'done' when finished.");
            while (true) {
                System.out.println("\n-- Meats --");
                for (Integer k : meatMenu.keySet()) {
                    String name = meatMenu.get(k);
                    System.out.printf("%d) %s (base $%.2f; extra +$%.2f)%n", k, name,
                            Pricing.meatBasePrice(size), Pricing.meatExtraPrice(size));
                }
                System.out.print("> ");
                String input = myScanner.nextLine().trim();
                if (input.equalsIgnoreCase("done")) break;
                if (input.isEmpty()) {
                    System.out.println("Please enter a choice.");
                    continue;
                }

                String chosenName = null;
                // try parse number
                try {
                    int idx = Integer.parseInt(input);
                    chosenName = meatMenu.get(idx);
                } catch (NumberFormatException e) {
                    // match by name
                    for (Integer k : meatMenu.keySet()) {
                        String v = meatMenu.get(k);
                        if (v.equalsIgnoreCase(input)) {
                            chosenName = v;
                            break;
                        }
                    }
                }

                if (chosenName == null) {
                    System.out.println("Invalid choice. Try again.");
                    continue;
                }

                System.out.print("Add " + chosenName + " (yes/no)? ");
                String addResp = myScanner.nextLine().trim();
                if (!addResp.equalsIgnoreCase("yes") && !addResp.equalsIgnoreCase("y")) {
                    System.out.println("Skipped.");
                    continue;
                }

                System.out.print("Extra " + chosenName + " (yes/no)? ");
                String extraResp = myScanner.nextLine().trim();
                boolean isExtra = extraResp.equalsIgnoreCase("yes") || extraResp.equalsIgnoreCase("y");

                double price = Pricing.meatBasePrice(size) + (isExtra ? Pricing.meatExtraPrice(size) : 0.0);
                List<String> tags = new ArrayList<String>();
                tags.add("meat");
                if (isExtra) tags.add("extra");

                Toppings topping = new Toppings(chosenName, isExtra, price, ToppingType.MEAT, tags);
                meats.add(topping);
                System.out.println("Added: " + chosenName + " ($" + String.format("%.2f", price) + ")");
            }
            return meats;
        }

        // PROMPT FOR CHEESES. using hashmap also for the cheese
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
                System.out.println("\n-- Cheeses --");
                for (Integer k : cheeseMenu.keySet()) {
                    String name = cheeseMenu.get(k);
                    System.out.printf("%d) %s (base $%.2f; extra +$%.2f)%n", k, name,
                            Pricing.cheeseBasePrice(size), Pricing.cheeseExtraPrice(size));
                }
                System.out.print("> ");
                String input = myScanner.nextLine().trim();
                if (input.equalsIgnoreCase("done")) break;
                if (input.isEmpty()) {
                    System.out.println("Please enter a choice.");
                    continue;
                }

                String chosen = null;
                try {
                    int idx = Integer.parseInt(input);
                    chosen = cheeseMenu.get(idx);
                } catch (NumberFormatException e) {
                    for (Integer k : cheeseMenu.keySet()) {
                        String v = cheeseMenu.get(k);
                        if (v.equalsIgnoreCase(input)) {
                            chosen = v;
                            break;
                        }
                    }
                }

                if (chosen == null) {
                    System.out.println("Invalid choice. Try again.");
                    continue;
                }

                System.out.print("Add " + chosen + " (yes/no)? ");
                String addResp = myScanner.nextLine().trim();
                if (!addResp.equalsIgnoreCase("yes") && !addResp.equalsIgnoreCase("y")) {
                    System.out.println("Skipped.");
                    continue;
                }

                System.out.print("Extra " + chosen + " (yes/no)? ");
                String extraResp = myScanner.nextLine().trim();
                boolean isExtra = extraResp.equalsIgnoreCase("yes") || extraResp.equalsIgnoreCase("y");

                double price = Pricing.cheeseBasePrice(size) + (isExtra ? Pricing.cheeseExtraPrice(size) : 0.0);
                List<String> tags = new ArrayList<String>();
                tags.add("cheese");
                if (isExtra) tags.add("extra");

                Toppings topping = new Toppings(chosen, isExtra, price, ToppingType.CHEESE, tags);
                cheeses.add(topping);
                System.out.println("Added: " + chosen + " ($" + String.format("%.2f", price) + ")");
            }
            return cheeses;
        }

        // PROMPT to remove sauces
        public List<Toppings> removeSauces() {
            List<Toppings> sauces = new ArrayList<Toppings>();
            HashMap<Integer, String> sauceMenu = new HashMap<Integer, String>();
            sauceMenu.put(1, "Salsa Verde");
            sauceMenu.put(2, "Salsa Roja");
            sauceMenu.put(3, "Chipotle");
            sauceMenu.put(4, "Habanero (on the side)");
            sauceMenu.put(5, "Mild (on the side");
            sauceMenu.put(6, "Extra hot (on the side");

            System.out.println("🌶️ Choose sauces. Type number or name to add. Type 'remove' to remove an added sauce. Type 'done' when finished.");

            while (true) {
                System.out.println("\n-- Sauces --");
                for (Integer k : sauceMenu.keySet()) {
                    System.out.printf("%d) %s%n", k, sauceMenu.get(k));
                }

                if (sauces.isEmpty()) {
                    System.out.println("Selected: (none)");
                } else {
                    System.out.print("Selected: ");
                    for (int i = 0; i < sauces.size(); i++) {
                        System.out.print((i + 1) + ":" + sauces.get(i).getName());
                        if (i < sauces.size() - 1) System.out.print(", ");
                    }
                    System.out.println();
                }

                System.out.print("> ");
                String input = myScanner.nextLine().trim();
                if (input.equalsIgnoreCase("done")) break;

                if (input.equalsIgnoreCase("remove")) {
                    if (sauces.isEmpty()) {
                        System.out.println("No sauces to remove.");
                        continue;
                    }
                    System.out.print("Type the number or name of the sauce to remove (or 'cancel'): ");
                    String rem = myScanner.nextLine().trim();
                    if (rem.equalsIgnoreCase("cancel")) {
                        System.out.println("Remove cancelled.");
                        continue;
                    }
                    boolean removed = false;
                    try {
                        int idx = Integer.parseInt(rem) - 1;
                        if (idx >= 0 && idx < sauces.size()) {
                            Toppings removedT = sauces.remove(idx);
                            System.out.println("Removed: " + removedT.getName());
                            removed = true;
                        }
                    } catch (NumberFormatException e) {
                    }
                    if (!removed) {
                        Iterator<Toppings> it = sauces.iterator();
                        while (it.hasNext()) {
                            Toppings t = it.next();
                            if (t.getName().equalsIgnoreCase(rem)) {
                                it.remove();
                                System.out.println("Removed: " + t.getName());
                                removed = true;
                                break;
                            }
                        }
                    }
                    if (!removed) System.out.println("No matching sauce found to remove.");
                    continue;
                }

                if (input.isEmpty()) {
                    System.out.println("Please enter a choice.");
                    continue;
                }

                String chosen = null;
                try {
                    int idx = Integer.parseInt(input);
                    chosen = sauceMenu.get(idx);
                } catch (NumberFormatException e) {
                    for (Integer k : sauceMenu.keySet()) {
                        String v = sauceMenu.get(k);
                        if (v.equalsIgnoreCase(input)) {
                            chosen = v;
                            break;
                        }
                    }
                }
                if (chosen == null) {
                    System.out.println("Invalid choice. Try again.");
                    continue;
                }

                boolean already = false;
                for (Toppings t : sauces) {
                    if (t.getName().equalsIgnoreCase(chosen)) {
                        already = true;
                        break;
                    }
                }

                if (already) {
                    System.out.print(chosen + " is already selected. Remove it? (yes/no): ");
                    String y = myScanner.nextLine().trim();
                    if (y.equalsIgnoreCase("yes") || y.equalsIgnoreCase("y")) {
                        Iterator<Toppings> it = sauces.iterator();
                        while (it.hasNext()) {
                            Toppings t = it.next();
                            if (t.getName().equalsIgnoreCase(chosen)) {
                                it.remove();
                                System.out.println("Removed: " + chosen);
                                break;
                            }
                        }
                    } else {
                        System.out.println("Kept: " + chosen);
                    }
                    continue;
                }

                // sauces are included (price 0)
                Toppings topping = new Toppings(chosen, false, 0.0, ToppingType.SAUCE, new ArrayList<String>());
                sauces.add(topping);
                System.out.println("Added: " + chosen);
            }
            return sauces;
        }

        // PROMPT FOR SIDES (uses Toppings as side items)
        public List<Toppings> promptForSides() {
            List<Toppings> sides = new ArrayList<Toppings>();
            HashMap<Integer, String> sideMenu = new HashMap<Integer, String>();
            sideMenu.put(1, "Chips");
            sideMenu.put(2, "Soda");

            HashMap<String, Double> sidePrices = new HashMap<String, Double>();
            sidePrices.put("Chips", 1.50);
            sidePrices.put("Soda ", 2.00);

            System.out.println("🥤🥔 Choose sides (type number or name). Type 'done' when finished.");
            while (true) {
                System.out.println("\n-- Sides --");
                for (Integer k : sideMenu.keySet()) { // method to see the all the "keys" in the map
                    String name = sideMenu.get(k);
                    System.out.printf("%d) %s ($%.2f)%n", k, name, sidePrices.get(name));
                }
                String input = myScanner.nextLine().trim();
                if (input.equalsIgnoreCase("done")) break;
                if (input.isEmpty()) {
                    System.out.println("Please enter a choice.");
                    continue;
                }

                String chosenName = null;
                try {
                    int idx = Integer.parseInt(input);
                    chosenName = sideMenu.get(idx);
                } catch (NumberFormatException e) {
                    for (Integer k : sideMenu.keySet()) {
                        String v = sideMenu.get(k);
                        if (v.equalsIgnoreCase(input)) {
                            chosenName = v;
                            break;
                        }
                    }
                }

                if (chosenName == null) {
                    System.out.println("Invalid choice. Try again.");
                    continue;
                }

                double price = sidePrices.getOrDefault(chosenName, 0.0);
                List<String> tags = new ArrayList<String>();
                tags.add("side");
                Toppings side = new Toppings(chosenName, false, price, ToppingType.EXTRA, tags);
                sides.add(side);
                System.out.println("Added: " + chosenName + " ($" + String.format("%.2f", price) + ")");
            }
            return sides;
        }

        // PROMPT FOR DRINK SIZE
        public String promptForDrinkSize() {
            System.out.println("🥤 Choose drink size: 1) Small ($2.00) 2) Medium ($2.50) 3) Large ($3.00)");
            while (true) {
                String input = myScanner.nextLine().trim();
                if (input.equals("1") || input.equalsIgnoreCase("small")) return "Small";
                if (input.equals("2") || input.equalsIgnoreCase("medium")) return "Medium";
                if (input.equals("3") || input.equalsIgnoreCase("large")) return "Large";
                System.out.println("Invalid choice. Try again.");
            }
        }

        // prompt for drink and add to order
        public void promptForDrink() {
            String sizeLabel = promptForDrinkSize();
            System.out.print("Enter drink name (e.g., Soda): ");
            String name = myScanner.nextLine().trim();
            double price = Pricing.drinkPrice(sizeLabel);
            Drink d = new Drink(name, price);
            this.currentOrder.addDrink(d);
            System.out.println("Added drink: " + name + " ($" + String.format("%.2f", price) + ")");
        }

        // prompt for chips and Salsa add to order
        public void promptForChips() {
            System.out.println("Choose chips size: 1) Small ($1.50) 2) Large ($2.50)");
            while (true) {
                String input = myScanner.nextLine().trim();
                if (input.equals("1")) {
                    ChipsAndSalsa c = new ChipsAndSalsa("Chips and salsa ", 1.50);
                    this.currentOrder.addChips(c);
                    System.out.println("Added: Chips and salsa");
                    return;
                } else {
                    System.out.println("Invalid choice. Enter 1 or 2.");
                }
            }
        }

        public boolean promptForDeepFried() {
        System.out.println("Would you like your taco deep-fried? (yes/no)");

        while (true) {
            String input = myScanner.nextLine().trim().toLowerCase();
            if (input.equals("yes") || input.equals("y")) return true;
            if (input.equals("no") || input.equals("n")) return false;
            System.out.println("Please enter 'yes' or 'no'.");
        }
    }

        // checkout: show order summary and save receipt (if ReceiptWriter exists)
        public void checkout() {
            System.out.println("\n🧾 Order Summary:");
            System.out.println(this.currentOrder.getOrderSummary());

            while (true) {
                System.out.println("Would you like to:");
                System.out.println("1) Confirm and save receipt");
                System.out.println("2) Cancel and discard order");
                System.out.print("> ");
                String input = myScanner.nextLine().trim();

                switch (input) {
                    case "1":
                        try {
                            Utilities.ReceiptWriter.saveReceipt(this.currentOrder);
                            System.out.println("✅ Receipt saved. Returning to home screen.");
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