package ru.croc.task10;

public class MyThread implements Runnable{

    private static final Object lock = new Object();
    private final int threadNum;
    private final int threadsCount;
    private final String hash;
    private static boolean active;


    MyThread(int threadNum, int threadsCount, String hash){
        this.threadNum = threadNum;
        this.threadsCount = threadsCount;
        this.hash = hash;
        active = true;
    }

    public static void bruteForce(String hash, int threadNum, int threadsCount){//7 циклов в тупую.
        for (long i = threadNum; i < 8_031_810_176L && active; i += threadsCount){
            synchronized (lock){
                String password = Task10.convert(i);
                if (Task10.hashPassword(password).equals(hash)){
                    System.out.println("password: " + password + "\nhash: " + Task10.hashPassword(password));
                    active = false;
                }
            }
        }
    }

    @Override
    public synchronized void run() {
        bruteForce(hash, threadNum, threadsCount);
    }
}
