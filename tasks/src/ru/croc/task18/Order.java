package ru.croc.task18;

import java.util.List;
import java.util.Set;

public class Order{
    int orderID;
    String userLogin;
    List<String> productsID;
    Order(int orderID, String userLogin, List<String> productsID){
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
}