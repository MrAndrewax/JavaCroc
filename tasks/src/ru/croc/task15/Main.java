package ru.croc.task15;

import java.util.*;

public class Main {
    /*Рекурсивная функция, которая обходит хэш таблицу и тем самым считает количество часов*/

    public static void main(String[] args){
        String path = "/home/andrew/learning/java_croc/tasks/src/ru/croc/task15/test1.txt";//args[0];
        Department root = Department.getDepartmentsFromFile(path);
        System.out.println(root.calculateTime());
    }
}
