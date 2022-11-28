package ru.croc.TaskB1;

import java.util.concurrent.*;

class Runner implements Runnable{

    public final static Object lock = null;

    @Override
    public void run() {
        synchronized (lock){
            for (int i = 0; i < 25_000_000; i++){
                System.out.println(i);
            }
        }
        for (int i = 0; i < 100_000_000; i++){
            System.out.println(i);
        }
    }
}


public class TaskB1 {

    public static void multiThreadingAlgo(int threadsNumber) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(threadsNumber);//пул потоков
        for (int i = 0; i < threadsNumber; i++){
            executorService.submit(new Runner());
        }
        executorService.shutdown();
        //boolean b = executorService.awaitTermination(1, TimeUnit.DAYS);
    }

    public static void main(String[] args){
        try {
            multiThreadingAlgo(4);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}