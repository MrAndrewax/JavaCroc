package ru.croc.task12;
import java.util.*;


public class Task12{

    public static void main(String[] args){


        List<String> comments = new ArrayList<>();
        for (int i =0; i < 9; i++){
            comments.add("badWord" + (i+1));
            //comments.add("goodWord" + (i+1));
            comments.add("goodWord" + (i+1) + " " + "badWord" + (i+1) + " goodWord" + (i+1));
        }


        Set<String> blackList = new HashSet<>();
        for (int i =0; i<9;i++){
            blackList.add("badWord" + (i+1));
        }


        System.out.println("comments: " + comments);
        System.out.println("blackList: " + blackList);
        new Filter().filterComments(comments, blackList);
        System.out.println("Функция filterComments отработала");
        System.out.println("comments: " + comments);
        System.out.println("blackList: " + blackList);
    }
}
