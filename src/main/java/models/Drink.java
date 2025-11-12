package models;

import Utilities.Valuable;

public class Drink implements Valuable {

    private String name;
    private double price;

    //constructor
    public Drink(String name, double price) {
            this.name = name;
            this.price = price;
    }
    public String getName() {
        return name;
    }
    @Override
    public double getPrice() {
        return price;
    }
    public String getSummary() {
        return name + " - $" + String.format("%.2f", price);
    }
}
