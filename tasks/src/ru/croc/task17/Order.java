package ru.croc.task17;

public class Order {

    private int orderID;
    private String userLogin;
    private String productID;

    Order(int orderID, String userLogin, String productID){
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