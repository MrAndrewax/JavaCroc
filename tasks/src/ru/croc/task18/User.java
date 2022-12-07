package ru.croc.task18;

public class User {
    int userId;
    String userName;

    User(int userId, String customerName){
        this.userId = userId;
        this.userName = customerName;
    }

    @Override
    public String toString() {
        return userId + " " + userName;
    }
}
