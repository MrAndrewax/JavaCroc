package ru.croc.task10;

public class Task10{
    public static void main(String[] args) throws Exception {
        int threadsNumber = Integer.parseInt(args[0]);//количество потоков
        String hash = args[1];//Хэш пароля, который надо найти
        String password = Solution.calculatePassword(threadsNumber, hash);
        if (password.equals("")){
            System.out.println("Нет пароля с таким хэшем: " + hash);
        } else{
            System.out.println("Password: " + password);
        }
    }
}