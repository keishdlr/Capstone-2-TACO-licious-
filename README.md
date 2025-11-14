# 🌮 TACOlicious CLI Menu Builder  
This command-line application allows users to build custom tacos by selecting meats and toppings.

---

## 🧱 Project Structure  
Each class in this project has a clear and focused responsibility:

### `Main.java`  
Handles user interaction and menu logic.

- Customized greeting with name and time of day
- ASCII art 
- Displays menu options  
- Prompts user input and routes to appropiate service  

### `Taco.java`  
Defines the data model for a taco.

- Fields: meat type, toppings  
- Groups selected ingredients into a single object  
- Formats taco details for display  

### `Prompts.java`  
Manages interactive CLI prompts.

- Collects user input for meats and toppings  
- Supports side items
- Validates selections and returns structured data  

### `TacoFactory.java`  
Assembles tacos 

- Creates Taco objects from user selections     

### `MeatType.java` and `ToppingType.java` (Enums)  
Encapsulate menu logic. 

### `TacoService.java` *(optional)*  
Handles saving and loading taco orders.

- Writes taco data to file  
- Reads previous orders for review  

---

## 🖼️ Images  

![Greeting and Menu](Screenshot 2025-11-14 012005)  
![Meat Selection](Screenshot 2025-11-14 012154)   
![Final Taco Summary](Screenshot 2025-11-14 012157, Screenshot 2025-11-14 012414)

---

## 🧩 Features
-    Twilio Integration for SMS
- 🧠 Interactive CLI 
- 🌮 Enum-based menu items
- Build custom tacos


