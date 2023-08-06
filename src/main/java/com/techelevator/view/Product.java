package com.techelevator.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Scanner;

public class Product implements Vendable {
    //
    // Changes on clases: variable names commented purchase menu item function below.
    //
    private static File inventoryFile = new File("./vendingmachine.csv");
    private String code;
    private String name;
    private BigDecimal price;
    private Vendable category;
    private int quantitySold;
    private int currentStock;
    private static final int DEFAULT_Quantity = 5;

    public String getMessage() {
        return message;
    }

    private String message;


    public String getCode() {
        return code;
    }



    public String getName() {
        return name;
    }



    public BigDecimal getPrice() {
        return price;
    }



    public int getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }
    public int getCurrentStock(){return currentStock;}



    public void setCategory(Vendable category) {
        this.category = category;
    } //not needed so far

    public Product(String code, String name, BigDecimal price) { //added qty
        this.code = code;
        this.name = name;
        this.price = price;
        this.category = category;
        currentStock = DEFAULT_Quantity;
        quantitySold = 0;
    }
//    public Product(String code, String name, BigDecimal price, Vendable category ) { //added qty
//        this.code = code;
//        this.name = name;
//        this.price = price;
//        this.category = category;
//        currentStock = DEFAULT_Quantity;
//        quantitySold = 0;
//    }

    @Override
    public String sound() {
        if(getCode().charAt(0)=='A') {
            return "Crunch crunch, Yum!";
        }else if(getCode().charAt(0)=='B'){
            return "Munch, munch, Yum!";
        }else if(getCode().charAt(0)=='C'){
            return "Slurp, slurp, Yum!";
        }else if(getCode().charAt(0)=='D'){
            return "Chew, chew, Yum!";
        }
        else
            return null;
    }

    public String vend(){
        currentStock -=1;

//        return category.sound();
        return sound();
    }




//    @Override
//    public String sound(String message) {
//        try(Scanner fileScanner = new Scanner(new File(String.valueOf(inventoryFile)))) {
//        while (fileScanner.hasNextLine()){
//            Product currentItem;
//            String currentLine = fileScanner.nextLine();
//            String[] lineInfo = currentLine.split("\\|");
//            String lineItemID = lineInfo[0];
//            String lineItemName = lineInfo[1];
//            BigDecimal lineItemPrice = new BigDecimal(lineInfo[2]);
//            String lineItemCategory = lineInfo[3];
//            Vendable currentCategory = null;
//            switch (lineItemCategory){
//                case "Chip":
//                    this.message="Crunch crunch, Yum!";
//                    break;
//                case "Candy":
//                    this.message="Munch, munch, Yum!";
//                    break;
//                case "Drink":
//                    this.message="Slurp, slurp, Yum!";
//                    break;
//                case "Gum":
//                   this.message="Chew, chew, Yum!";
//                    break;
//            }
//
//        }
//    } catch (FileNotFoundException ex) {
//        System.err.println("Machine data file not found.");
//
//    }
//        return sound(message);
//    }
}


    /*
=======
    private static File inventoryFile= new File("vendingmachine.csv");

    private String option;

    private String itemName;
    ////
    //CHANGE BELOW TO BIG DOUBLE WHEN WORKING
    ////
    private double price;
    private String itemType;
    private String productKey;

    public String getItemType() {return itemType;}

    public String getProductKey() {return productKey;}

    public String getOption() {
        return option;
    }

    public String getItemName() {
        return itemName;
    }

    public double getPrice() {
        return price;
    }

    public void purchaseMenuInput(String productKey){

        /////
        //this try catch is only set up to find the product key, I did this like I did because it seemed the easiest way to
        //get the key and use it in the try catch on line 57 which is used to find the values associated with the product and store them
        //I know there has to be a way to combine the two try loops to make them more condensed
        /////
        try(Scanner readInventory=new Scanner(inventoryFile)){
            while(readInventory.hasNextLine()){
                String line=readInventory.nextLine();
                ////
                //if loop to find search parameter(search param entered in vending.cli)
                ///
                if(line.startsWith(productKey)){
                    this.option= line.substring(0,line.indexOf("|"));
                }
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        try(   FileReader readFile=new FileReader(inventoryFile)) {
            BufferedReader bufferingFileReader=new BufferedReader(readFile);
            String line;
            while((line=bufferingFileReader.readLine())!=null){
                if(line.contains(productKey)) {
                    String[] item = line.split("\\|");
                    for (int i = 0; i < item.length; i++) {

                        if (i == 1) {
                            this.itemName = item[i];
                        }
                        if (i == 2) {
                            this.price = Double.parseDouble(item[i]);
                        }
                        if (i == 3) {
                            this.itemType = item[i];
                        }
                    }
                }
            }
            bufferingFileReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}*/