package models;

import java.util.List;

 //Represents a taco topping with optional premium pricing and descriptive tags.
public class Toppings {
    private String name;
    private boolean isPremium;
    private double price;
    private List<String> tags; // e.g., "spicy", "crunchy", "vegan"

     // Constructors

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
