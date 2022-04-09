package model;

import enums.PaymentType;

import java.util.Date;
import java.util.UUID;

public class Order {
    private UUID id;
    private Product product;
    private Double amountPaid;
    private Date date;
    private Slot slot;
    private Double returnAmount;
    private PaymentType paymentType;

    public Order(UUID id, Product product, Double amountPaid, Date date, Slot slot, Double returnAmount, PaymentType paymentType) {
        this.id = id;
        this.product = product;
        this.amountPaid = amountPaid;
        this.date = date;
        this.slot = slot;
        this.returnAmount = returnAmount;
        this.paymentType = paymentType;
    }
}
