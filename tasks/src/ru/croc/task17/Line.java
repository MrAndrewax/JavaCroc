package ru.croc.task17;

public class Line {
    int orderNumber;
    String userLogin;
    String productNumber;
    String productName;
    int price;

    Line(int orderNumber, String userLogin, String productNumber, String productName, int price){
        this.orderNumber = orderNumber;
        this.userLogin = userLogin;
        this.productNumber = productNumber;
        this.productName = productName;
        this.price = price;
    }

    @Override
    public String toString() {
        return orderNumber + "," + userLogin + ","
                + productNumber + "," + productName + "," + price;
    }
}
