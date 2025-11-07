package models;

import java.util.ArrayList;
import java.util.List;

public class Taco {
    // store tortilla type (corn, flour, hard shell, or bowl).
    // store taco size Single Taco, 3-Taco Plate, Burrito).
    // store meats, cheeses, toppings, sauces
    // keep track of total price

    //PROPERTIES
    String size;
    String tortilla;
    String meats;
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
         //enum in TacoSize class
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
    List<String> sauces = new ArrayList<>()

    // addMeat method:
    public void addMeat(){
        //   - add meat(s)
        //   - what is the price based on size and whether it's extra?

    }

    // addCheese method:
    public void addCheese(){
        //   - add cheese(s)
        //   - add price depending on size and whether it's extra
    }

    // addTopping method:
    public void addToppings(){
        //   - add topping(s)
        //   - no charge

    }

    // addSauce method:
    public void addSauce(){
        //   - add sauce(s)
        //   - no charge
    }


    // getPrice method:
    public void getPrice(){
        //   - return current total price
    }


    // getSummary method:
    public void getSummary(){
        //   - return a string with all taco details and price
            StringBuilder summary = new StringBuilder();

            summary.append("🌮 Taco Summary:\n");
            summary.append("- Size: ").append(size.name()).append("\n");
            summary.append("- Tortilla: ").append(tortilla).append("\n");
            summary.append("- Meats: ").append(meats).append("\n");
            summary.append("- Deep Fried: ").append(deepFried ? "Yes" : "No").append("\n");
            summary.append("- Cheeses: ").append(cheeses.isEmpty() ? "None" : String.join(", ", cheeses)).append("\n");
            summary.append("- Toppings: ").append(toppings.isEmpty() ? "None" : String.join(", ", toppings)).append("\n");
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
