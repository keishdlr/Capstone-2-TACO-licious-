package models;

public class Chips implements Valuable {
    private String name;
    private double price;

    public Chips(String name, double price) {
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
