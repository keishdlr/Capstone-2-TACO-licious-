package Utilities;

import models.ChipsAndSalsa;
import models.Drink;
import models.Order;
import models.Taco;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReceiptWriter {
    public static void saveReceipt(Order order) {
        // Create timestamp string using a formatter
        // return that string
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String Timestamp = LocalDateTime.now().format(formatter1);

        // generate filename using current date/time (yyyyMMdd-HHmmss.txt)
        // Format 1: 2023-09-06 12:42:20
        //DateTimeFormatter formatter5 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String filename = "receipt-" + Timestamp + ".txt";
        String folder = "src/main/resources/receipts/";
        File file = new File(folder + filename);

        try (FileWriter writer = new FileWriter(file)) { // create a FileWriter and wrap in BufferedWriter (src/main/resources/receipts)
            // loop through all order items
            //   - write the items to the to receipt
            // Tacos
            writer.write("🌮 Tacos:\n");
            for (Taco taco : order.getTacos()) {
                writer.write("- " + taco.getSummary() + " ($" + String.format("%.2f", taco.getPrice()) + ")\n");
            }
            // Drinks
            writer.write("\n🥤 Drinks:\n");
            for (Drink drink : order.getDrinks()) {
                writer.write("- " + drink.getName() + " ($" + String.format("%.2f", drink.getPrice()) + ")\n");
            }
            // Chips
            writer.write("\n🥔 Chips:\n");
            for (ChipsAndSalsa chips : order.getChips()) {
                writer.write("- " + chips.getName() + " ($" + String.format("%.2f", chips.getPrice()) + ")\n");
            }
            // write total cost
            // Total
            writer.write("\n💰 Total: $" + String.format("%.2f", order.getPrice()) + "\n");
            writer.write("\nThank you for visiting Seven Serpent Taco Shop!\n");

        } catch (IOException e) {
            System.out.println("Error saving receipt: " + e.getMessage());
        }
    }}
