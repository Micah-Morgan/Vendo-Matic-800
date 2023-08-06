//package com.techelevator.view;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.PrintWriter;
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Scanner;
//
//public class Money {
//
//    // This class is for taking money asking what product they want by letter and number give change and record the date and time.
//    // It uses the format we were given.
////            01/01/2019 12:00:00 PM FEED MONEY: $5.00 $5.00
////            01/01/2019 12:00:15 PM FEED MONEY: $5.00 $10.00
////            01/01/2019 12:00:20 PM Crunchie B4 $1.75 $8.25
////            01/01/2019 12:01:25 PM Cowtales B2 $1.50 $6.75
////            01/01/2019 12:01:35 PM GIVE CHANGE: $6.75 $0.00
//
//
//
//
//    private static final Map<String, BigDecimal> PRODUCT_PRICES = new HashMap<>();
//    static {
//        PRODUCT_PRICES.put("A1 Potato Crisps", BigDecimal.valueOf(3.05));
//        PRODUCT_PRICES.put("A2 Stackers" ,BigDecimal.valueOf(1.45));
//        PRODUCT_PRICES.put("A3 Grain Waves" ,BigDecimal.valueOf(2.75));
//        PRODUCT_PRICES.put("A4 Cloud Popcorn" ,BigDecimal.valueOf(3.65));
//        PRODUCT_PRICES.put("B1 Moonpie" ,BigDecimal.valueOf(1.8));
//        PRODUCT_PRICES.put("B2 Cowtales" ,BigDecimal.valueOf(1.5));
//        PRODUCT_PRICES.put("B3 Wonka Bar" ,BigDecimal.valueOf(1.5));
//        PRODUCT_PRICES.put("B4 Crunchie" ,BigDecimal.valueOf(1.75));
//        PRODUCT_PRICES.put("C1 Cola" ,BigDecimal.valueOf(1.25));
//        PRODUCT_PRICES.put("C2 Dr. Salt" ,BigDecimal.valueOf(1.5));
//        PRODUCT_PRICES.put("C3 Mountain Melter" ,BigDecimal.valueOf(1.5));
//        PRODUCT_PRICES.put("C4 Heavy" ,BigDecimal.valueOf(1.5));
//        PRODUCT_PRICES.put("D1 U-Chews" ,BigDecimal.valueOf(0.85));
//        PRODUCT_PRICES.put("D2 Little League Chew" ,BigDecimal.valueOf(0.95));
//        PRODUCT_PRICES.put("D3 Chiclets" ,BigDecimal.valueOf(0.75));
//        PRODUCT_PRICES.put("D4 Triplemint" ,BigDecimal.valueOf(0.75));
//    }
//
//    private BigDecimal balance = BigDecimal.ZERO;
//    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
//    private final Map<LocalDateTime, String> transactionHistory = new HashMap<>();
//
//    public void feedMoney(BigDecimal amount) {
//        balance = balance.add(amount);
//        LocalDateTime now = LocalDateTime.now();
//        String message = String.format("%s FEED MONEY: $%.2F $%.2F", now.format(formatter), amount, balance);
//        transactionHistory.put(now, message);
//    }
//
//    public void selectProduct(String code) {
//        BigDecimal price = PRODUCT_PRICES.get(code);
//        if (price != null && balance.compareTo(price) >= 0) {
//            balance = balance.subtract(price);
//            LocalDateTime now = LocalDateTime.now();
//            String message = String.format("%s %s $%.2f $%.2f", now.format(formatter), code, price, balance);
//            transactionHistory.put(now, message);
//        }
//    }
//
//    public void giveChange() {
//        if (balance.compareTo(BigDecimal.ZERO) > 0) {
//            LocalDateTime now = LocalDateTime.now();
//            String message = String.format("%s GIVE CHANGE $%.2f $0.00", now.format(formatter), balance);
//            transactionHistory.put(now, message);
//            balance = BigDecimal.ZERO;
//        }
//    }
//
//    public void printTransactionHistory() throws FileNotFoundException {
//        for (Map.Entry<LocalDateTime, String> entry : transactionHistory.entrySet()) {
//            System.out.println(entry.getValue());
//        }
//    }
//
//
//
//    public static void main(String[] args) throws FileNotFoundException {
//        Money money = new Money();
//        Scanner scanner = new Scanner(System.in);
//
//
//
//        while (true) {
//            System.out.printf("Balance: $%.2fn", money.balance);
//            System.out.println("Enter command (F to feed money, S to select product, C to give Change, H for transaction history):");
//            String command = scanner.nextLine().toUpperCase();
//
//
//            switch (command) {
//                case "F":
//                    System.out.println("Enter amount to feed:");
//                    BigDecimal amount = scanner.nextBigDecimal();
//                    money.feedMoney(amount);
//                    scanner.nextLine();
//                    break;
//                case "S":
//                    System.out.println("Enter product code (e.g. A1, B2):");
//                    String code = scanner.nextLine().toUpperCase();
//                    money.selectProduct(code);
//                    break;
//                case "C":
//                    money.giveChange();
//                    break;
//                case "H":
//                    money.printTransactionHistory();
//                    break;
//            }
//        }
//    }
//}