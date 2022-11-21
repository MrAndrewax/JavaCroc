package ru.croc.task11;

import java.util.Date;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        Lot lot = new Lot(new Date().getTime() + 60_000);
        User user1 = new User(lot, "Andrey");
        User user2 = new User(lot, "Kirill");
        User user3 = new User(lot, "Danya");
        User user4 = new User(lot, "Vlad");

        Thread thread1 = new Thread(user1);
        Thread thread2 = new Thread(user2);
        Thread thread3 = new Thread(user3);
        Thread thread4 = new Thread(user4);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();

        /*
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        executorService.submit(user1);
        executorService.submit(user2);
        executorService.submit(user3);
        executorService.submit(user4);
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.DAYS);
         */
        System.out.println("ПОБЕДИТЕЛЬ " + lot.getWinnerName());
    }
}