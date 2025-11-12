package models;

import java.util.ArrayList;
import java.util.List;

 //Represents a taco topping with optional premium pricing and descriptive tags.
 public class Toppings {
     protected String name;
     protected boolean isPremium;
     protected double price;
     protected ToppingType type;
     protected List<String> tags;

     // Constructor
     public Toppings(String name, boolean isPremium, double price, ToppingType type, List<String> tags) {
         this.name = name;
         this.isPremium = isPremium;
         this.price = price;
         this.type = type;
         this.tags = tags;
     }

     //regular toppings with tags
    public Toppings(String name, List<String> tags) {
        this.name = name;
        this.isPremium = false;
        this.price = 0.0;
        this.tags = tags;
    }

     // premium toppings with tags.
    public Toppings(String name, double price, List<String> tags) {
        this.name = name;
        this.isPremium = true;
        this.price = price;
        this.tags = tags;
    }
    public static meatLogic() {
        while (true) {
            System.out.println("\n-- Meats --");
            for (Integer k : meatMenu.keySet()) {
                String name = meatMenu.get(k);
                System.out.printf("%d) %s (base $%.2f; extra +$%.2f)%n", k, name,
                        Taco.baseMeatPrice(size), Pricing.extraMeatPrice(size));
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

            double price = Pricing.baseMeatPrice(size) + (isExtra ? Pricing.extraMeatPrice(size) : 0.0);
            List<String> tags = new ArrayList<String>();
            tags.add("meat");
            if (isExtra) tags.add("extra");

            Toppings topping = new Toppings(chosenName, isExtra, price, ToppingType.MEAT, tags);
            meats.add(topping);
            System.out.println("Added: " + chosenName + " ($" + String.format("%.2f", price) + ")");
        }
        return meats;
    }

     // helper: build a taco by calling the prompts above and add to order
     public void promptForTaco() {
         TacoSize size = promptForSize();
         Taco taco = new Taco(size); // assumes Taco constructor sets base price from size
         List<Toppings> meats = promptForMeats(size);
         for (Toppings t : meats) taco.addTopping(t);
         List<Toppings> cheeses = promptForCheeses(size);
         for (Toppings c : cheeses) taco.addTopping(c);
         List<Toppings> sauces = promptForSaucesWithRemove();
         for (Toppings s : sauces) taco.addTopping(s);
         List<Toppings> extras = promptForSides(); // reuse sides prompt for extras if wanted
         for (Toppings e : extras) taco.addTopping(e);

         this.currentOrder.addTaco(taco);
         System.out.println("Added taco: " + taco.getSummary());
     }

    public static cheese cheeseLogic(){
        while (true) {
            System.out.println("\n-- Cheeses --");
            for (Integer k : cheeseMenu.keySet()) {
                String name = cheeseMenu.get(k);
                System.out.printf("%d) %s (base $%.2f; extra +$%.2f)%n", k, name,
                        Pricing.baseCheesePrice(size), Pricing.extraCheesePrice(size));
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

            double price = Pricing.baseCheesePrice(size) + (isExtra ? Pricing.extraCheesePrice(size) : 0.0);
            List<String> tags = new ArrayList<String>();
            tags.add("cheese");
            if (isExtra) tags.add("extra");

            Toppings topping = new Toppings(chosen, isExtra, price, ToppingType.CHEESE, tags);
            cheeses.add(topping);
            System.out.println("Added: " + chosen + " ($" + String.format("%.2f", price) + ")");
        }
        return cheeses;
    }
    //getters and setter
    public String getName() {
        return name;
    }

    public boolean isPremium() {
        return isPremium;
    }

    public double getPrice() {
        return price;
    }

    public List<String> getTags() {
        return tags;
    }

    @Override
    public String toString() {
        String tagString = tags.isEmpty() ? "" : " (" + String.join(", ", tags) + ")";
        return name + tagString + (isPremium ? " [+ $" + String.format("%.2f", price) + "]" : "");
    }
}
