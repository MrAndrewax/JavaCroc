package ru.croc.task10;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;



public class Task10{

    public static void main(String[] args) throws InterruptedException {

        /*
        Тестовые данные:
        password: passwrd
        hash: 40682260CC011947FC2D0B1A927138C5
        Программа отработала за 354104ms

        password: passwrd
        hash: 40682260CC011947FC2D0B1A927138C5
        Программа отработала за 529474ms
        */


        int threadsCount = Integer.parseInt(args[0]);//количество потоков
        String hash = args[1];//Хэш пароля, который надо найти

        long start = System.currentTimeMillis();

        ExecutorService executorService = Executors.newFixedThreadPool(threadsCount);//пул потоков
        for (int i = 0; i < threadsCount; i++){
            executorService.submit(new MyThread(i, threadsCount, hash));
        }
        executorService.shutdown();
        boolean termination = executorService.awaitTermination(1, TimeUnit.DAYS);

        long end = System.currentTimeMillis();
        System.out.println("Программа отработала за " + (end - start) + "ms");
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


