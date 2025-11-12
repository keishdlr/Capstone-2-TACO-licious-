package models;

import Utilities.Pricing;

public enum SignatureMenuOptions {
    STREET_TACOS,
    SUPER_BURRITO;

    public Taco build() {
        return switch (this) {
            case STREET_TACOS -> {
                Taco taco = new Taco(TacoSize.THREE);
                taco.setTortilla("Corn");
                taco.addMeat(new Toppings("Carne Asada", Pricing.meatBasePrice(taco.getSize()), ToppingType.MEAT));
                taco.addTopping(new Toppings("Onions", 0.25, ToppingType.EXTRA));
                taco.addTopping(new Toppings("Cilantro", 0.25, ToppingType.EXTRA));
                taco.addSauce(new Toppings("Salsa Verde", 0.00, ToppingType.SAUCE));
                taco.addTopping(new Toppings("Lime Wedges", 0.25, ToppingType.EXTRA));
                yield taco;
            }
            case SUPER_BURRITO -> {
                Taco taco = new Taco(TacoSize.BURRITO);
                taco.setTortilla("Flour");
                taco.addMeat(new Toppings("Carnitas", Pricing.meatBasePrice(taco.getSize()), ToppingType.MEAT));
                taco.addCheese(new Toppings("Cheddar", 0.75, ToppingType.CHEESE));
                taco.addTopping(new Toppings("Pico de Gallo", 0.50, ToppingType.EXTRA));
                taco.addTopping(new Toppings("Lettuce", 0.25, ToppingType.EXTRA));
                taco.addTopping(new Toppings("Tomatoes", 0.25, ToppingType.EXTRA));
                taco.addTopping(new Toppings("Birria Dipped", 1.00, ToppingType.EXTRA));
                yield taco;
            }
        };
    }
}