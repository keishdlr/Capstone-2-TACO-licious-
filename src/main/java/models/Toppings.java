package models;

public class Toppings {
    protected String name;
    protected double price;
    protected ToppingType type;

    // Constructor
    public Toppings(String name, double price, ToppingType type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }
    public ToppingType getType() {
        return type;
    }
    //getters and setter
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
}