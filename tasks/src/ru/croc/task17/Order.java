package ru.croc.task17;

public class Order {

    int orderID;
    String userLogin;
    String productID;

    Order(int orderID, String userLogin, String productID){
        this.orderID = orderID;
        this.userLogin = userLogin;
        this.productID = productID;
    }

}