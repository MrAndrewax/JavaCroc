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
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            int value = i * 1000 + new Random().nextInt();
            long time = new Date().getTime();
            System.out.println("Игрок " + userName + " сделал ставку " + value + " в " + time);
            lot.bid(userName, value, time);
        }
    }
}
