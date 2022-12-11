package ru.croc.task17;

public class OrderInfo {
    private int orderID;
    private String userLogin;
    private String productId;
    private String productName;
    private int price;

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

    ge
}
