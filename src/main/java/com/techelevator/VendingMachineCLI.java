package com.techelevator;

import com.techelevator.view.Menu;
import com.techelevator.view.SalesLog;
import com.techelevator.view.VendingMachine;

import java.math.BigDecimal;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit"; // Inserted missing option
	private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT}; // created the string array to work with menu
	private static final String MAIN_MENU_OPTION_SALES_REPORT = "Sales Report";

	//productMenu idea

	private static final String FEED_MONEY = "Feed Money";
	private static final String SELECT_PRODUCT = "Select Product";
	private static final String FINISH_TRANSACTION="Finish Transaction"; //changed to proper finish transaction
	private static final String[] HIDDEN_MENU_OPTIONS = {MAIN_MENU_OPTION_SALES_REPORT};
	private static final String[] PURCHASE_MENU_OPTIONS={FEED_MONEY,SELECT_PRODUCT,FINISH_TRANSACTION};
	private static final String PATH_TO_INVENTORY_FILE = "./vendingmachine.csv";
	//
	//	private Product product;
//	private List<Product> products ;
	private VendingMachine vendingMachine;
	private Menu menu;
//	BigDecimal money=BigDecimal.ZERO;
//	String purchaseChoice=" "; //New: declared here instead of the for loop allow us to use in finish transaction if needed.
//	// step 3
//	private static File inventory=new File("vendingmachine.csv");

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
		this.vendingMachine = new VendingMachine(PATH_TO_INVENTORY_FILE, System.out);
	}

	public void run() {

		while (true) {

			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS, HIDDEN_MENU_OPTIONS);


			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				System.out.println(vendingMachine.displayVendingItems());
				/////
				// display vending machine items this works
				/////



			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				/*
					Selecting "(1) Feed Money" allows the customer to repeatedly feed money into the machine in whole dollar amounts.
					The "Current Money Provided" indicates how much money the customer has fed into the machine.
				 */

				while (true) {
					choice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS, vendingMachine.displayCurrentBalance());
					System.out.println();
					//String purchaseMenuChoice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);  //New: shows purchase menu options
					if (choice.equals(FEED_MONEY)) {
						System.out.println("Please insert cash into the machine: ");
						String moneyInserted = menu.getIn().nextLine();
						vendingMachine.feedMoney(moneyInserted);
					} else if (choice.equals(SELECT_PRODUCT)) {
						System.out.println(vendingMachine.displayVendingItems());
						System.out.println("Please make your selection: ");
						String input = menu.getIn().nextLine();
						vendingMachine.selectProduct(input);
					} else if (choice.equals(FINISH_TRANSACTION)) {
						vendingMachine.finishTransaction(vendingMachine.getCurrentBalance());
						vendingMachine.setCurrentBalance(BigDecimal.ZERO);
						break;
					}
				}
			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {

				break;
			} else if (choice.equals(MAIN_MENU_OPTION_SALES_REPORT)) {
				SalesLog.generateReport(vendingMachine.getAllVendingItem(), vendingMachine.getTotalSales());
			}
		}
	}
	public static void main (String[]args){
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
/*
When the customer selects "(2) Purchase", they're guided through the purchasing process menu:
Current Money Provided: $2.00

(1) Feed Money
(2) Select Product
(3) Finish Transaction

 */
//
//					if (purchaseMenuChoice.equals(FEED_MONEY)) {
//						Scanner userInput = new Scanner(System.in);
////					money= BigDecimal.valueOf(userInput.nextDouble());
//						while (true) {
//							System.out.println("Please insert currency and select (d) when finished");
//							String input = userInput.nextLine();
//							if (input.equalsIgnoreCase("d")) {
//								break;
//							}
//							try {
//								BigDecimal amount = new BigDecimal(input);
//								money = money.add(amount);
//							} catch (NumberFormatException e) {
//								System.out.println("Invalid input");
//							}
//							System.out.println("Total:" + money);
//						}
//					} else if (purchaseMenuChoice.equals(SELECT_PRODUCT)) {
//						System.out.println("select product");
//					/*
//					Show the list of products available and allow the customer to enter a code to select an item.
//					*/
//						for (Map.Entry<String, Product> entry : map.entrySet()) {
//							System.out.printf(entry.getKey() + " " + entry.getValue().getName() + " $" + entry.getValue().getPrice() + " " + entry.getValue().getQty() + "\n");
//						}
//					/*
//					Prompt the user for code.
//					*/
//						Scanner scanner = new Scanner(System.in);
//						System.out.println("Select item code ");
//						purchaseChoice = scanner.next().toUpperCase(); //now you can enter lower case and works too
//
//						if (!map.containsKey(purchaseChoice)) { //check if item exist
//							System.out.println("Invalid entry"); //If the product code doesn't exist, the vending machine informs the customer
//							menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS); // and returns them to the Purchase menu.
//						} else {
//							if (map.get(purchaseChoice).getQty() == 0) { // If a product is currently sold out, the vending machine informs the customer and returns them to the Purchase menu.
//								System.out.println("Sold Out");
//								//	menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);  //return to purchase menu
//							} else {//valid entry
//
//								if (money.compareTo(money.valueOf(map.get(purchaseChoice).getPrice())) > 0) { //New: check if money is bigger than selected product price
//									money = money.subtract(BigDecimal.valueOf(map.get(purchaseChoice).getPrice())); //update money
//									map.get(purchaseChoice).setQty(map.get(purchaseChoice).getQty() - 1); //update qty
//									String message = "";
//									if (map.get(purchaseChoice).getCategory().equals("Chip")) {
//										message = "Crunch Crunch, Yum!"; //All chip items print "Crunch Crunch, Yum!"
//									}
//									if (map.get(purchaseChoice).getCategory().equals("Candy")) {
//										message = "Munch Munch, Yum!"; //All candy items print "Munch Munch, Yum!"
//									}
//									if (map.get(purchaseChoice).getCategory().equals("Drink")) {
//										message = "Glug Glug, Yum!"; // All drink items print "Glug Glug, Yum!"
//									}
//									if (map.get(purchaseChoice).getCategory().equals("Gum")) {
//										message = "Chew Chew, Yum!"; //All gum items print "Chew Chew, Yum!"
//									}
//
//									//Dispensing an item prints the item name, cost, and the money remaining. Dispensing also returns a message
//									System.out.println("purchased: " + map.get(purchaseChoice).getName() +  " " + message + " " + map.get(purchaseChoice).getPrice() + " " + map.get(purchaseChoice).getQty() + "\nMoney: $" + money);
//									//	menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);  //return to purchase menu
//								} else {
//									System.out.println("Insufficient Founds");
//								}
//							}
//						}
//					} else if (purchaseMenuChoice.equals(FINISH_TRANSACTION)) {
////						BigDecimal quarterNumber=new BigDecimal(.25);
////						BigDecimal remainder=money.remainder(quarterNumber);
////
////							if(remainder.compareTo(BigDecimal.ZERO)==0){
////								System.out.println("Your change is "+money.divide(BigDecimal.valueOf(.25))+" quarters");
////							}else if (remainder.compareTo(BigDecimal.ZERO)!=0){
////
////							}
//
//
//						int quarters = money.divideToIntegralValue(new BigDecimal("0.25")).intValue();
//						BigDecimal quartersValue = new BigDecimal(quarters).multiply(new BigDecimal("0.25"));
//
//						int dimes = money.subtract(quartersValue).divideToIntegralValue(new BigDecimal("0.10")).intValue();
//						BigDecimal dimesValue = new BigDecimal(dimes).multiply(new BigDecimal("0.10"));
//
//						int nickels = money.subtract(quartersValue).subtract(dimesValue).divideToIntegralValue(new BigDecimal("0.05")).intValue();
//						BigDecimal nickelsValue = new BigDecimal(nickels).multiply(new BigDecimal("0.05"));
//
//						BigDecimal pennies = money.subtract(quartersValue).subtract(dimesValue).subtract(nickelsValue);
//
//						System.out.println("Here is your change");
//						System.out.println("Quarters: " + quarters);
//
//						System.out.println("Nickels: " + nickels);
//						System.out.println("Pennies: " + pennies);
//						money=BigDecimal.ZERO;
//						break;
					/*

7.iii Selecting "(3) Finish Transaction" allows the customer to complete the transaction and receive any remaining change.
The machine returns the customer's money using nickels, dimes, and quarters (using the smallest amount of coins possible).
The machine's current balance updates to $0 remaining.
After completing their purchase, the user returns to the "Main" menu to continue using the vending machine.
					 */
//
//					}
//				}
//
//
//			}
//		}
//	}
//
//	public void purchaseMenu(){
//		Product customer=new Product();
//		PurchaseMenu purchaseMenu=new PurchaseMenu();
//		purchaseMenu.purchaseMenu();
//
//		Scanner scanner=new Scanner(System.in);
//		System.out.print("\nChose from the following options:");
//		String choice=scanner.nextLine();
//		if(choice.equals("2")){
//			try(Scanner readingFile=new Scanner(inventory)){
//				while(readingFile.hasNextLine()){
//					System.out.println(readingFile.nextLine());
//				}
//			}catch(FileNotFoundException e){
//				System.err.println("File Not Found");
//			}
//			System.out.println("Enter your choice below using the first two digits\n of the item you wish to purchase");
//			product=new Product();
//			////
//			//below is a placeholder test for the file iterator in Product class in purchaseMenuInput()
//			// have not moved the data into a HASHMAP yet but is set up where we can move that into one.
//			////
//
//			product.purchaseMenuInput(choice=scanner.nextLine().toUpperCase());
//			System.out.println("The "+product.getItemName()+"\n Costs: "+product.getPrice());
//
//		}
//       String purchaseMenuChoice= (String)
//	}
//







