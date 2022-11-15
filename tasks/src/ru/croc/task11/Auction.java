package ru.croc.task11;

public class Auction {
    private static final Object lock = new Object();
    private String userName;
    private double currentValue;
    private long endTime;

    public void bid(String userName, double value, long time){
        synchronized (lock){
            if (value > currentValue && time < endTime){
                this.userName = userName;
                this.currentValue = value;
            }
        }
    }
    public String getWinnerName(long currentTime){
        synchronized(lock){
            if (currentTime > endTime){
                return userName;
            }
            return null;
        }
    }
}
