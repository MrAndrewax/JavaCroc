package ru.croc.task17;

public class OrderInfo {
    int userId;
    String userName;
    String productId;
    String productName;
    int price;

    OrderInfo(int userId, String userName, String productId, String productName, int price){
        this.userId = userId;
        this.userName = userName;
        this.productId = productId;
        this.productName = productName;
        this.price = price;
    }

    @Override
    public String toString() {
        return userId + "," + userName + ","
                + productId + "," + productName + "," + price;
    }
}
