package models;

import Utilities.TacoCan;
import Utilities.Valuable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Taco implements TacoCan, Valuable {
    //properties
    private Tortilla tortilla;
    private TacoSize size;
    private List<Toppings> meats = new ArrayList<>();
    private List<Toppings> cheeses = new ArrayList<>();
    private List<Toppings> sauces = new ArrayList<>();
    private List<Toppings> toppings = new ArrayList<>(); // master list for all toppings
    private double totalPrice;
    private boolean deepFried;
    // constructor
    public Taco(TacoSize size) {
        this.size = size;
        this.totalPrice = size.getBasePrice();
        this.meats = new ArrayList<>();
    }
    public void addTopping(Toppings topping) {
        switch (topping.getType()) {
            case MEAT -> meats.add(topping);
            case CHEESE -> cheeses.add(topping);
            case SAUCE -> sauces.add(topping);
        }
        toppings.add(topping); // always add to master list
        totalPrice += topping.getPrice();
    }

    //getters and setters
    public Tortilla getTortilla() { return tortilla; }
    public TacoSize getSize() { return size; }
    public double getTotalPrice() { return totalPrice; }
    public boolean isDeepFried() { return deepFried; }
    public void setDeepFried(boolean deepFried) { this.deepFried = deepFried; }

    //contracts from TacoCan
    @Override
    public void setTortilla(Tortilla tortilla){
          this.tortilla = tortilla;
    }
    @Override
    public void addMeat(Toppings meat) {
        meats.add(meat);
        totalPrice += meat.getPrice();
    }
    @Override
    public void addCheese(Toppings cheese) {
        cheeses.add(cheese);
        totalPrice += cheese.getPrice();
    }
    @Override
    public void addSauce(Toppings sauce) {
        sauces.add(sauce);
        totalPrice += sauce.getPrice();
    }
    @Override
    public double getPrice() {
        return totalPrice;
    }
    @Override
    public String getSummary() {
        return String.format("""
            🌮 Taco Summary:
            - Size: %s
            - Tortilla: %s
            - Deep Fried: %s
            - Meats: %s
            - Cheeses: %s
            - Sauces: %s
            - Total Price: $%.2f
            """,
                size, tortilla, deepFried ? "Yes" : "No",
                formatList(meats), formatList(cheeses), formatList(sauces),
                totalPrice
        );
    }
    private String formatList(List<Toppings> list) {
        return list.isEmpty() ? "None" :
                list.stream().map(Toppings::getName).collect(Collectors.joining(", "));
    }
}
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
