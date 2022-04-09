package model;

import enums.ProductType;

public class Product {
    private String name;
    private Long price;
    private ProductType type;

    public Product(String name, Long price, ProductType type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Long getPrice() {
        return price;
    }

    public ProductType getType() {
        return type;
    }
}
