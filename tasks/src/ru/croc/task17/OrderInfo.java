package ru.croc.task17;

public class OrderInfo {
    private final int orderID;
    private final String userLogin;
    private final String productId;
    private final String productName;
    private final int price;

    OrderInfo(int orderID, String userLogin, String productId, String productName, int price){
        this.orderID = orderID;
        this.userLogin = userLogin;
        this.productId = productId;
        this.productName = productName;
        this.price = price;
    }

    @Override
    public String toString() {
        return orderID + "," + userLogin + ","
                + productId + "," + productName + "," + price;
    }

    public int getOrderID() {
        return orderID;
    }

    public String getUserLogin() {
        return userLogin;
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
