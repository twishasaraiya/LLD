package service;

import enums.CoinType;
import enums.PaymentType;
import enums.ProductType;
import model.Product;
import model.Slot;

import java.util.Arrays;

public class VendingMachine {

    private final SlotService slotService;
    private static VendingMachine vendingMachine;
    private final Integer DEFAULT_ROW_SIZE = 10;
    private final Integer DEFAULT_COL_SIZE = 10;
    private Slot selectedSlot;
    private long centsInsertedSoFar = 0L;

    public VendingMachine() {
        slotService = new SlotService();
        selectedSlot = null;
    }

    public static VendingMachine getVendingMachine(){
        if(vendingMachine == null){
            synchronized (vendingMachine){
                vendingMachine = new VendingMachine();
            }
        }
        return vendingMachine;
    }
    void selectSlotAndShowPrice(Integer row, Integer column){
        // get product in slot
        Product product = slotService.getProduct(row, column);

        // display product details
        System.out.println("Product Price " + product.getPrice());
    }

    void handlePaymentByCash(String username, CoinType[] coins){
        if(selectedSlot == null){
            System.out.println("Please select a product item first");
            return;
        }

        // check the amount of the selected product
        long costPrice = selectedProduct.getPrice();
        // refund amount
        long totalSumOfCoins = Arrays.stream(coins)
                .map(coinType -> coinType.getValue())
                .count();

        centsInsertedSoFar += totalSumOfCoins;
        if(centsInsertedSoFar < costPrice){
            long diff = costPrice - centsInsertedSoFar;
            System.out.println("please insert extra " + diff + " cents");
            return;
        }else{
            long refundAmount = centsInsertedSoFar - costPrice;
            System.out.println("Please collect " + refundAmount + " cents");
        }

        // update slot / inventory
        Product product = slotService.dispenseProductFromSlot(row, col);

        // dispense product
    }

    void cancelSelection(){
        // remove product selection
        selectedProduct = null;
        centsInsertedSoFar = 0l;
    }

    void loadNewProduct(Integer row, Integer col, String name, Double price, ProductType productType, String userName){
        // check if slot is empty

        // add new product to slot
    }

}