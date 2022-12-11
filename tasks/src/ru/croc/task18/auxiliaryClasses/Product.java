package ru.croc.task18.auxiliaryClasses;

public class Product{
    private final String productId;
    private final String productName;
    private final int price;

    public Product(String productId, String productName, int price){
        this.productId = productId;
        this.productName = productName;
        this.price = price;
    }

    @Override
    public String toString() {
        return productId + " " + productName + " " + price;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getPrice() {
        return price;
    }
}
