package ru.croc.task10;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

public class Solution {




    public static String calculatePassword(int threadsNumber , String hashPass){


        List<Future<String>> futures = new ArrayList<Future<String>>();
        ExecutorService executorService = Executors.newFixedThreadPool(threadsNumber);//пул потоков
        for (int i = 0; i < threadsNumber; i++){
            Future<String> future;
            future = executorService.submit(new MyThread(i, threadsNumber, hashPass));
            futures.add(future);
        }
        executorService.shutdown();
        //executorService.awaitTermination(1, TimeUnit.DAYS);

        for (Future<String> future:futures){

            String password = null;
            try {
                password = future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            if (hashPassword(password).equals(hashPass)){
                return password;
            }
        }

        return "";
    }

    public static final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();

    public static String toHexString(byte[] bytes) {
        StringBuilder hex = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            hex.append(HEX_DIGITS[(b & 0xff) >> 4]);
            hex.append(HEX_DIGITS[b & 0x0f]);
        }
        return hex.toString();
    }

    public static String hashPassword(String password) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        digest.update(password.getBytes());
        byte[] bytes = digest.digest();
        return toHexString(bytes);
    }

    public static String convert(long number) {

        StringBuilder result = new StringBuilder();
        long dividend = number;

        do {
            long remainder = dividend % 26;
            result.append(numToStr(remainder));
            dividend = dividend / 26;
            if (dividend < 26) result.append(numToStr(dividend));
        } while (dividend >= 26);

        result.append("a".repeat(Math.max(0, 7 - result.length())));

        return result.toString();
    }

    public static String numToStr(long a){
        return String.valueOf( (char) (a + 97) );
    }
}














class CallableExample
{
    public CallableExample()
    {
        ExecutorService executor;
        executor = Executors.newFixedThreadPool(3);

        List<Future<String>> futures = new ArrayList<Future<String>>();

        // Создание экземпляра Callable класса
        Callable<String> callable = new CallableClass();

        for (int i = 0; i < 3; i++){
            /*
             * Стартуем возвращаюший результат исполнения
             * в виде объекта Future поток
             */
            Future<String> future;
            future = executor.submit(callable);
            /*
             * Добавляем объект Future в список для
             * отображения результат выполнения (получение
             * наименования потока)
             */
            futures.add(future);
        }
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("HH:mm:ss  ");
        for (Future<String> future : futures){
            try {
                // Выводим в консоль полученное значение
                String text = sdf.format(new Date())
                        + future.get();
                System.out.println(text);
            } catch (InterruptedException |
                     ExecutionException e) {}
        }
        // Останавливаем пул потоков
        executor.shutdown();
    }
    //-----------------------------------------------------
    // Класс, реализующий интерфейс Callable
    class CallableClass implements Callable<String>
    {
        @Override
        public String call() throws Exception {
            Thread.sleep(1000);
            // наименование потока, выполняющего
            // callable задачу
            return Thread.currentThread().getName();
        }
    }
    //-----------------------------------------------------
    public static void main(String args[])
    {
        new CallableExample();
    }
}