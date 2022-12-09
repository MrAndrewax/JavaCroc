package ru.croc.task17;

public class OrderInfo {
    int orderID;
    String userLogin;
    String productId;
    String productName;
    int price;

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
}
