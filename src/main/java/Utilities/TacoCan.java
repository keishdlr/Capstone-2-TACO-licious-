package Utilities;

import models.Toppings;

public interface TacoCan {

    // addCheese method:
    void addCheese(String cheese);

        double getPrice();           // Every taco must be able to report its price
        String getSummary();         // Every taco must describe itself
        void addMeat(String meat);   // Must be able to add meat
        void addCheese(String cheese, boolean isExtra); // Add cheese, maybe with extra charge
        void addTopping(Toppings topping); // Add a topping object
        void addSauce(String sauce); // Add sauce
    }
