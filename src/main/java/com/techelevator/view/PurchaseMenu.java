package com.techelevator.view;
import java.io.InputStream;
import java.io.OutputStream;

public class PurchaseMenu  {
    private static final String FEED_MONEY = "Feed Money";
    private static final String SELECT_PRODUCT = "Select Product";
    private static final String FINISH_TRANSACTION="Exit";
    private static final String[] PURCHASE_MENU_OPTIONS={FEED_MONEY,SELECT_PRODUCT,FINISH_TRANSACTION};

    //////
    //Product Menu iterator Method is below , I believe this would be a good location to possibly handle money transactions,
    //either here or in the cli part where we call for feed transaction
    /////


    public  void purchaseMenu(){
        int cont=0;
        for(String menu: PURCHASE_MENU_OPTIONS){
            cont++;
            System.out.println(cont+") "+menu);
        }
    }

}