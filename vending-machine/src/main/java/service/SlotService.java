package service;

import model.Product;

import java.util.HashMap;
import java.util.Map;

public class SlotService {
    private Map<Integer, Map<Integer, Product>> slots;

    public SlotService() {
        this.slots = new HashMap<>();
    }

    private Boolean isValidSlot(Integer row, Integer col){
        if(!slots.containsKey(row)) return Boolean.FALSE;
        else if(!slots.get(row).containsKey(col)) return Boolean.FALSE;
        return Boolean.TRUE;
    }

    public Product getProduct(Integer row, Integer col){
        if(!isValidSlot(row, col)){
            System.out.println("Invalid slot");
            throw new IllegalArgumentException("Invalid slot");
        }

        return slots.get(row).get(col);
    }

    public Product dispenseProductFromSlot(Integer row, Integer col){
        Product product = getProduct(row, col);
        slots.get(row).put(col, null);
        return product;
    }
}
