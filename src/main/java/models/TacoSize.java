package models;

//Taco building actions
 public enum TacoSize {
        // Each size option has a base price and calories attached.
        SINGLE(3.50,250),   // A single taco — perfect for a snack or light meal
        THREE(9.00,750),    // A 3-taco plate — great for sharing or a full meal
        BURRITO(8.50,650);  // A burrito — hearty and wrapped up tight

        // The base price for this taco size
        private final double basePrice;
        private final int calories;

        // Constructor for TacoSize enum.
        // @param basePrice The starting price for this taco size.
        TacoSize(double basePrice, int calories) {
            this.basePrice = basePrice;
            this.calories = calories;
        }
        // Gets the base price for the taco size.
        // @return The base price as a double.
        public double getBasePrice() {
            return basePrice;
        }
        public int getCalories(){
            return calories;
        }
    }