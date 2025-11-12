package Utilities;

import models.Order;

public class ReceiptWriter {
    public static void saveReceipt(Order order) {
          String generateTimestamp;
            // Create timestamp string using a formatter
            // return that string

        // generate filename using current date/time (yyyyMMdd-HHmmss.txt)
            String filename = "receipt-" + generateTimestamp() + ".txt";
            Path path = Paths.get("src/main/resources/receipts", filename);
            try (BufferedWriter writer = Files.newBufferedWriter(path)) {
                writer.write(order.getOrderSummary());
                writer.write("\nThank you for visiting TACOlicious!");
            } catch (IOException e) {
                System.out.println("Error saving receipt: " + e.getMessage());
            }
        // Format 5: 2023-09-06 12:42:20
        //DateTimeFormatter formatter5 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Current date and time
        // LocalDateTime now = LocalDateTime.now();
        // System.out.println( now.format(formatter1));
        // create a FileWriter and wrap in BufferedWriter (src/main/resources/receipts)

        // loop through all order items
        //   - write the items to the to receipt

        //write total cost

        // close BufferedWriter
        // handle IOException with error message
}}
