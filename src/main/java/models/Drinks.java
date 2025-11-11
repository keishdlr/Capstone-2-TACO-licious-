package models;

import Utilities.Valuable;

public class Drinks extends Order implements Valuable {

    private String name;
    private double price;

    public Drinks(String name, double price) {
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
