package model;

import java.util.UUID;

public class Balance {
    UUID id;
    Long userId;
    Long owesTo;
    Double amount;

    public Balance(Long userId, Long owesTo, Double amount) {
        this.userId = userId;
        this.owesTo = owesTo;
        this.amount = amount;
    }

    public UUID getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getOwesTo() {
        return owesTo;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
