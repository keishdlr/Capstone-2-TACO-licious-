package Utilities;

import models.TacoSize;

public class Pricing {
    private double meatBasePrice(TacoSize size) {
        return switch (size) {
            case SINGLE -> 1.00;
            case THREE  -> 2.00;
            case BURRITO-> 3.00;
        };
    }

    private double meatExtraPrice(TacoSize size) {
        return switch (size) {
            case SINGLE -> 0.50;
            case THREE  -> 1.00;
            case BURRITO-> 1.50;
        };
    }

    private double cheeseBasePrice(TacoSize size) {
        return switch (size) {
            case SINGLE -> 0.75;
            case THREE  -> 1.50;
            case BURRITO-> 2.25;
        };
    }

    private double cheeseExtraPrice(TacoSize size) {
        return switch (size) {
            case SINGLE -> 0.30;
            case THREE  -> 0.60;
            case BURRITO-> 0.90;
        };
    }
    private double drinkPrice(String sizeLabel) {
        return switch (sizeLabel.toLowerCase()) {
                case "small"  -> 2.00;
                case "medium" -> 2.50;
                case "large"  -> 3.00;
                default -> 0.0;
        };
    }

}
