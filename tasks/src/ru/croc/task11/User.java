package ru.croc.task11;

import java.util.Date;
import java.util.Random;

public class User implements Runnable{

    private final String userName;
    private final Lot lot;

    User(Lot lot, String userName){
        this.lot = lot;
        this.userName = userName;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++){
            lot.bid(userName, i * 1000 + new Random().nextInt(), new Date().getTime());
        }
    }
}
