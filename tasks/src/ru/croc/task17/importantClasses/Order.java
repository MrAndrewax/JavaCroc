package ru.croc.task17.importantClasses;

public class Order {

    private final int orderID;
    private final String userLogin;
    private final String productID;

    public Order(int orderID, String userLogin, String productID){
        this.orderID = orderID;
        this.userLogin = userLogin;
        this.productID = productID;
    }

    public int getOrderID() {
        return orderID;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public String getProductID() {
        return productID;
    }
}