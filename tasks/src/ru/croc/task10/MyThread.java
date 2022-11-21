package ru.croc.task10;

import java.util.concurrent.Callable;

public class MyThread implements Callable<String>{

    private static final Object lock = new Object();
    private final int threadIndex;
    private final int threadsNumber;
    private final String hash;
    private static volatile boolean active;

    MyThread(int threadIndex, int threadsNumber, String hash) {
        //System.out.println("Thread " + threadIndex + " " + threadsNumber);
        this.threadIndex = threadIndex;
        this.threadsNumber = threadsNumber;
        this.hash = hash;
        active = true;
    }

    public String bruteForce(String hash, int threadIndex, int threadsNumber){//7 циклов в тупую.
        for (long i = threadIndex; (i < 8_031_810_176L) && active; i += threadsNumber){
            System.out.println(i);
            String password = Solution.convert(i);
            //System.out.println(i + " password: " + password + "\nhash: " + Solution.hashPassword(password));
            if (Solution.hashPassword(password).equals(hash)){
                //System.out.println(i + "password: " + password + "\nhash: " + Solution.hashPassword(password));
                active = false;
                return password;
            }
        }
        return "";
    }


    @Override
    public String call(){
        return bruteForce(hash, threadIndex, threadsNumber);
    }
}


