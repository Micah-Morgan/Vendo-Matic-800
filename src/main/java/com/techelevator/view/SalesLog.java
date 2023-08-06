package com.techelevator.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class SalesLog {

    //private static final String SALES_LOG_NAME = "saleslog.txt";
    public static void generateReport(Map<String, Product> items, BigDecimal totalSales) {
        File report = new File("SalesLog-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM_dd_yy_hh-mm-ss-a")) + ".txt");
        try (PrintWriter reportWriter = new PrintWriter(report)){
            for(Map.Entry<String, Product> entry : items.entrySet()) {
                reportWriter.write(entry.getValue().getName() + "|" + entry.getValue().getQuantitySold() + System.lineSeparator());
            }
            reportWriter.write(System.lineSeparator());
            reportWriter.write("Total Sales: ");
            reportWriter.write(VendingMachine.formatMoney(totalSales));
        } catch (FileNotFoundException exception) {
            System.err.println("Sales report file not found");
        }
    }

//    public static void logSale(String message) {
//        try {
//            File logFile = new File(SALES_LOG_NAME);
//            FileWriter writer = new FileWriter(logFile, true);
//            writer.write(String.format("[%s] SALE: %s%n", DATE_FORMAT.format(new Date()), message));
//            writer.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
