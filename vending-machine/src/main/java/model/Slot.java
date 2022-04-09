package model;

public class Slot {
    private Integer row;
    private Integer col;
    private Product product;

    public Slot(Integer row, Integer col) {
        this.row = row;
        this.col = col;
        this.product = null;
    }

    public Integer getRow() {
        return row;
    }

    public Integer getCol() {
        return col;
    }

    public Product getProduct() {
        return product;
    }

    public Boolean containsProduct(){
        return product != null;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
