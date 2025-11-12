package models;

import Utilities.TacoCan;
import Utilities.Valuable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Taco implements TacoCan, Valuable {
    // store tortilla type (corn, flour, hard shell, or bowl).
    // store taco size Single Taco, 3-Taco Plate, Burrito).
    // store meats, cheeses, toppings, sauces
    // keep track of total price

    //PROPERTIES
    TacoSize size;
    String tortilla;
    List<Toppings> meats;
    double totalPrice;
    boolean deepFried;

    //constructor: takes tortilla, size,deep fried
     public Taco() {
         //   - initialize properties
         this.size = size;
         this.tortilla = tortilla;
         this.meats = meats;
         this.deepFried = deepFried;
         this.totalPrice = size.getBasePrice(); // enum logic applied here

         //   - set base price based on size
         // cleaning this to improve logic and avoid typos using enum.
         //enum is in TacoSize class
//         if (size.equals("single")) {
//             this.totalPrice = 3.50;
//         } else if (size.equals("three")) {
//             this.totalPrice = 9.00;
//         } else if (size.equals("burrito")) {
//             this.totalPrice = 8.50;
//         } else {
//             this.totalPrice = 0.0; // default fallback
//         }
     }
    List<Toppings> cheeses = new ArrayList<>();
    List<Toppings> toppings = new ArrayList<>();
    List<Toppings> sauces = new ArrayList<>();
    List<Toppings> meats = new ArrayList<>();

    public static void addTopping(Toppings topping) {
        switch (topping.getType()) {
            case meats -> addMeat(topping);
            case cheeses -> addCheese(topping);
            case sauces -> addSauce(topping);
            default -> toppings.add(topping); // fallback or EXTRA
        }

        if (topping.isPremium()) {
            totalPrice += topping.getPrice();
        }
    }
    // getPrice method:
    @Override
    public double getPrice(){
        //   - return current total price
        return totalPrice;
    }

    // getSummary method:
    @Override
    public String getSummary(){
        //   - return a string with all taco details and price
        StringBuilder summary = new StringBuilder();
        // append to build by layers
            summary.append("🌮 Taco Summary:\n");
            summary.append("- Size: ").append(getSize()).append("\n");
            summary.append("- Tortilla: ").append(tortilla).append("\n");
            summary.append("- Deep Fried: ").append(deepFried ? "Yes" : "No").append("\n");
            summary.append("- Cheeses: ");
            summary.append(cheeses.isEmpty() ? "None" :
                cheeses.stream().map(Toppings::toString).collect(Collectors.joining(", "))).append("\n");
            summary.append("- Toppings: ");
            summary.append(toppings.isEmpty() ? "None" :
                toppings.stream().map(Toppings::toString).collect(Collectors.joining(", "))).append("\n");
            summary.append("- Sauces: ");
            summary.append(sauces.isEmpty() ? "None" :
                sauces.stream().map(Toppings::toString).collect(Collectors.joining(", "))).append("\n");
            summary.append("- Meats: ");
            summary.append(meats.isEmpty() ? "None" :
                meats.stream().map(Toppings::toString).collect(Collectors.joining(", "))).append("\n");
            summary.append("- Base Price: $").append(String.format("%.2f", size.getBasePrice())).append("\n");
            summary.append("- Calories: ").append(size.getCalories()).append(" kcal\n");
            summary.append("- Total Price: $").append(String.format("%.2f", totalPrice)).append("\n");

            return summary.toString();
    }

    //getters and setters
    public TacoSize getSize() {
        return size;
    }

    public void setSize(TacoSize size) {
        this.size = size;
    }

    public String getTortilla() {
        return tortilla;
    }

    public void setTortilla(String tortilla) {
        this.tortilla = tortilla;
    }

    public String getMeats() {
        return meats;
    }

    public void setMeats(String meats) {
        this.meats = meats;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isDeepFried() {
        return deepFried;
    }

    public void setDeepFried(boolean deepFried) {
        this.deepFried = deepFried;
    }
}
