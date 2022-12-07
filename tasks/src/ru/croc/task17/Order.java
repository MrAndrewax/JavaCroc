package ru.croc.task17;

public class Order{
    int orderId;
    String userLogin;

    Order(int orderId, String customerName){
        this.orderId = orderId;
        this.userLogin = customerName;
    }


    @Override
    public String toString() {
        return orderId + " " + userLogin;
    }
}

