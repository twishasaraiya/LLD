package dto;

public class Balance {

    private String lentBy;
    private String paidBy;
    private Double amount;

    public Balance(String lentBy, String paidBy, Double amount) {
        this.lentBy = lentBy;
        this.paidBy = paidBy;
        this.amount = amount;
    }

    public String getPaidBy() {
        return paidBy;
    }

    public String getLentBy() {
        return lentBy;
    }

    public Double getAmount() {
        return amount;
    }
}
