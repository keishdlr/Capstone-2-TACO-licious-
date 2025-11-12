package models;

import Utilities.Valuable;

public class Drink implements Valuable {

    private String name;
    private double price;

    public Drink(String name, double price) {
            this.name = name;
            this.price = price;
    }
    @Override
    public double getPrice() {
        return price;
    }
    public String getSummary() {
        return name + " - $" + String.format("%.2f", price);
    }
}
