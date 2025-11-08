package models;

import Utilities.TacoCan;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Taco implements TacoCan {
    // store tortilla type (corn, flour, hard shell, or bowl).
    // store taco size Single Taco, 3-Taco Plate, Burrito).
    // store meats, cheeses, toppings, sauces
    // keep track of total price

    //PROPERTIES
    String size;
    String tortilla;
    String meats;
    String cheese;
    String sauce;
    double totalPrice;
    boolean deepFried;

    //constructor: takes tortilla, size,deep fried
     public Taco(String size, String tortilla, String meats, double totalPrice, boolean deepFried) {
         //   - initialize properties
         this.size = size;
         this.tortilla = tortilla;
         this.meats = meats;
         this.totalPrice = totalPrice;
         this.deepFried = deepFried;
         this.totalPrice = size.getBasePrice; // enum logic applied here

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
    List<String> cheeses = new ArrayList<>();
    List<String> toppings = new ArrayList<>();
    List<String> sauces = new ArrayList<>();

    // addMeat method:
    @Override
    public void addMeat(String meats){
        //   - add meat(s)
        //   - what is the price based on size and whether it's extra?

    }

    // addCheese method:
    @Override
    public void addCheese(String cheese){
        //   - add cheese(s)
        //   - add price depending on size and whether it's extra
    }

    // addTopping method:
    // streams/collections for lists
    @Override
    public void addToppings(Toppings toppings){
        //   - add topping(s)
        //   - no charge
        toppings.add(toppings);
        if (toppings.isPremium()) {
            totalPrice += toppings.getPrice();
        }
    }

    // addSauce method:
    @Override
    public void addSauce(String sauce){
        //   - add sauce(s)
        //   - no charge
    }


    // getPrice method:
    @Override
    public void getPrice(double totalPrice){
        //   - return current total price
    }


    // getSummary method:
    @Override
    public String getSummary(){
        //   - return a string with all taco details and price

        StringBuilder summary = new StringBuilder();

        // append to build by layers
            summary.append("🌮 Taco Summary:\n");
            summary.append("- Size: ").append(size.name()).append("\n");
            summary.append("- Tortilla: ").append(tortilla).append("\n");
            summary.append("- Meats: ").append(meats).append("\n");
            summary.append("- Deep Fried: ").append(deepFried ? "Yes" : "No").append("\n");
            summary.append("- Cheeses: ").append(cheeses.isEmpty() ? "None" : String.join(", ", cheeses)).append("\n");
            summary.append("- Toppings: ");
             if (toppings.isEmpty()) {
            summary.append("None\n");
            } else {
            summary.append(toppings.stream()
                            .map(Topping::toString) // Convert each topping to its display string
                            .collect(Collectors.joining(", "))) // Join all toppings with commas
                    .append("\n");
            }
            summary.append("- Sauces: ").append(sauces.isEmpty() ? "None" : String.join(", ", sauces)).append("\n");
            summary.append("- Base Price: $").append(String.format("%.2f", size.getBasePrice())).append("\n");
            summary.append("- Calories: ").append(size.getCalories()).append(" kcal\n");
            summary.append("- Total Price: $").append(String.format("%.2f", totalPrice)).append("\n");

            return summary.toString();
    }


        //getters and setters
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
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
