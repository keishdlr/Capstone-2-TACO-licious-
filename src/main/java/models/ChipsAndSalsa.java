package models;

import Utilities.Valuable;

public class ChipsAndSalsa extends Order implements Valuable {

        private String name;
        private double price;
        public ChipsAndSalsa(String name, double price) {
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
