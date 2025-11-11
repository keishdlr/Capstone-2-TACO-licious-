package Utilities;

import models.Toppings;

public interface TacoCan {

        double getPrice();           // Every taco must be able to report a price
        String getSummary();         // Every taco must have a receipt
        void addMeat(String meat,boolean isExtra);   // Must be able to add meat, maybe extra charge
        void addCheese(String cheese, boolean isExtra); // Add cheese, maybe with extra charge
        void addTopping(Toppings topping); // Add toppings
        void addSauce(String sauce); // Add sauce
    }
