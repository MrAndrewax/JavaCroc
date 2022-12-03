package ru.croc.task16;

import java.io.BufferedReader;

public class TimeStamp {
    private BufferedReader bufferedReader;
    private long time;
    TimeStamp(BufferedReader bf, long time){
        this.bufferedReader = bf;
        this.time = time;
    }

    public long time() {
        return time;
    }

    public BufferedReader bufferedReader() {
        return bufferedReader;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setBufferedReader(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    @Override
    public String toString() {
        return String.valueOf(time);
    }
}


