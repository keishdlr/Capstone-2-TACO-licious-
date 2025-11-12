package Utilities;

import models.Toppings;

public interface TacoCan {

    double getPrice();           // Every taco must be able to report a price
    String getSummary();         // Every taco must have a receipt

    void addMeat(Toppings meat);
    void addCheese(Toppings cheese);
    void addSauce(Toppings sauce); // Add sauce
    void addTopping(Toppings topping); // Add toppings
}
