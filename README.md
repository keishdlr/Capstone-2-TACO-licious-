🌮 TACOlicious Project Structure Overview
📁 com.pluralsight/
- TACOliciousApp.java
Your main entry point. Calls Prompts.showHomeScreen() to start the interactive flow.

📁 models/
- Taco.java
Core taco object. Holds size, toppings, and price logic. Implements TacoCan and Valuable.
- Toppings.java
Represents individual toppings with name, type, price, tags, and premium status.
- Order.java
Holds lists of tacos, drinks, chips. Implements Valuable. Generates order summary and total.
- SignatureTacos.java
Predefined taco recipes. Could be static methods or a menu class.
- Drinks.java
Represents drink items. Implements Valuable.
- ChipsAndSalsa.java
Represents chips. 
- TacoSize.enum
Enum for SINGLE, THREE, BURRITO. Holds base prices and calories.
- ToppingType.enum
Enum for MEAT, CHEESE, SAUCE, EXTRA, etc.

📁 Utilities/
- TacoCan.interface
Interface for taco-like objects. Requires getPrice(), getSummary(), and topping methods.
- Valuable.interface
Interface for anything with a price. Used by Taco, Drink, Chips, Order.
- ReceiptWriter.java
Saves order summary to a timestamped .txt file. Used during checkout.

📁 UserInterface/
- Prompts.java
Handles all user interaction. Includes:
- showHomeScreen() and showOrderMenu()
- promptForSize(), promptForMeats(), promptForCheeses()
- promptForSaucesWithRemove(), promptForSides(), promptForDrinkSize()
- promptForSandwich(), promptForDrink(), promptForChips()
- checkout() with full confirmation flow

## 🧩 Features
-    Twilio Integration for SMS
- 🧠 Interactive CLI 
- 🌮 Enum-based menu items
