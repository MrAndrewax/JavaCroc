package ru.croc.task15;

import java.util.*;

public class Main {
    /*Рекурсивная функция, которая обходит хэш таблицу и тем самым считает количество часов*/
    public static int calculateTime(Department department){
        List<Department> children = department.getChildren();

        int childrenMaxHours = 0;
        if (children.isEmpty()){
            return department.getHours();
        }
        for (Department child : children){
             int childHours = calculateTime(child);
             if (childHours > childrenMaxHours){
                 childrenMaxHours = childHours;
             }
        }
        return department.getHours() + childrenMaxHours;
    }

    public static void main(String[] args){
        String path = "/home/andrew/learning/java_croc/tasks/src/ru/croc/task15/test2.txt";//args[0];
        Department root = Department.getDepartmentsFromFile(path);
        System.out.println(calculateTime(root));
    }
}
