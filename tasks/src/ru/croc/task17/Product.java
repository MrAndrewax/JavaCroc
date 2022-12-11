package ru.croc.task17;

public class Product{
    private String productId;
    private String productName;
    private int price;

    Product(String productId, String productName, int price){
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

