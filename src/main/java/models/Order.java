package models;

import Utilities.Valuable;
import java.util.ArrayList;
import java.util.List;

public class Order implements Valuable {
    // create List for order items (taco, drinks, and chips)
    private List<Taco> tacos = new ArrayList<>();
    private List<Drink> drinks = new ArrayList<>();
    private List<ChipsAndSalsa> chips = new ArrayList<>();

    public List<Taco> getTacos() {
        return tacos;
    }
    public List<Drink> getDrinks() {
        return drinks;
    }
    public List<ChipsAndSalsa> getChips() {
        return chips;
    }

    // constructor:
    //   - initialize the class properties
    public Order() {
        this.tacos = new ArrayList<>();
        this.drinks = new ArrayList<>();
        this.chips = new ArrayList<>();
    }

    // addTaco method:
    // - add Taco to list
    public void addTaco(Taco taco) {
        tacos.add(taco);
    }
    // addDrink method:
    //   - add drink to list
    public void addDrink(Drink drink) {
        drinks.add(drink);
    }
    // addChips method:
    //   - add chips to list
    public void addChips(ChipsAndSalsa chip) {
        chips.add(chip);
    }

    // store total price
    @Override
    public double getPrice() {
        // getTaco, getDrinks, getChips:
        //   - return the orders items
        return tacos.stream().mapToDouble(Taco::getPrice).sum()
                + drinks.stream().mapToDouble(Drink::getPrice).sum()
                + chips.stream().mapToDouble(ChipsAndSalsa::getPrice).sum();
    }
    // getOrderSummary method:
    //   - return formatted string of all items and total
    public String getOrderSummary() {
        StringBuilder sb = new StringBuilder("🧾 Order Summary:\n");

        if (!tacos.isEmpty()) {
            sb.append("🌮 Tacos:\n");
            tacos.forEach(t -> sb.append(t.getSummary()).append("\n"));
        }
        if (!drinks.isEmpty()) {
            sb.append("🥤 Drinks:\n");
            drinks.forEach(d -> sb.append(d.getSummary()).append("\n"));
        }
        if (!chips.isEmpty()) {
            sb.append("🍟 Chips:\n");
            chips.forEach(c -> sb.append(c.getSummary()).append("\n"));
        }
        // getTotal method:
        //   - return total price of order
        sb.append("💰 Total: $").append(String.format("%.2f", getPrice())).append("\n");
        return sb.toString();
    }
}
