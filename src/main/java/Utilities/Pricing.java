package Utilities;

import models.TacoSize;

public class Pricing {
    public static double meatBasePrice(TacoSize size) {
        return switch (size) {
            case SINGLE -> 1.00;
            case THREE  -> 2.00;
            case BURRITO-> 3.00;
        };
    }

    public static double meatExtraPrice(TacoSize size) {
        return switch (size) {
            case SINGLE -> 0.50;
            case THREE  -> 1.00;
            case BURRITO-> 1.50;
        };
    }

    public static double cheeseBasePrice(TacoSize size) {
        return switch (size) {
            case SINGLE -> 0.75;
            case THREE  -> 1.50;
            case BURRITO-> 2.25;
        };
    }

    public static double cheeseExtraPrice(TacoSize size) {
        return switch (size) {
            case SINGLE -> 0.30;
            case THREE  -> 0.60;
            case BURRITO-> 0.90;
        };
    }
    public static double drinkPrice(String sizeLabel) {
        return switch (sizeLabel.toLowerCase()) {
                case "small"  -> 2.00;
                case "medium" -> 2.50;
                case "large"  -> 3.00;
                default -> 0.0;
        };
    }

}
