package enums;

public enum CoinType{
    PENNY(1), NICKEL(5), DIME(10), QUARTER(25);

    private int value;
    CoinType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
