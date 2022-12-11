package ru.croc.task18.auxiliaryClasses;

import java.util.List;
import java.util.Set;

public class Order{
    private final int orderID;
    private final String userLogin;
    private final List<String> productsID;

    public Order(int orderID, String userLogin, List<String> productsID){
        this.orderID = orderID;
        this.userLogin = userLogin;
        this.productsID = productsID;
    }

    @Override
    public String toString() {
        return "orderID = " + orderID +
                ", userLogin = '" + userLogin + '\'' +
                ", productsID = '" + productsID + '\'';
    }

    public int getOrderID() {
        return orderID;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public List<String> getProductsID() {
        return productsID;
    }
}