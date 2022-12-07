package ru.croc.task17;

public class Product{
    String productId;
    String productName;
    int price;

    Product(String productId, String productName, int price){
        this.productId = productId;
        this.productName = productName;
        this.price = price;
    }

    @Override
    public String toString() {
        return productId + " " + productName + " " + price;
    }
}//Map<String, Product> products;
