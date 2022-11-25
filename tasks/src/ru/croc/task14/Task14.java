package ru.croc.task14;


import java.util.*;

//Это предикат
//можно передать любой тип
//черный список это любая коллекция реализующая Iterable. (Юзаем тератор))0000))0

class Comments <T, C extends Iterable<T>>{//C - тип коллеции. T - тип комментария

}
/*
    Комментарий - упорядоченная совокупность допустимых и недопустимых слов.
    Комментарии - множество комментариев.
    Черный список - множество недопустимых слов.

    Задача:
    Удалить из комментариев слова, которые находятся в чёрном списке.
    То есть
    1) получаем комментарий итерируясь по всем комментариям.
    2) Удаляем недопустимые слова в каждом отдельном комментарии. Это можно сделать итерируясь по словам и проверяя наличие этих слов в черном списке.
    3) Получаем "чистые" комментарии.


    Вопросы:
    1) сигнатура дефолт метода

    2) про предикат
    * что значит способ фильтрации?
    * какой тип на вход принимает предикат.
    * Какая логика у предиката?


    3) про черный список
    * какой структорой данных представлен черный список? Так же множеством? Или любой коллекцией, которая реализует Iterable?

    4) Про отдельный комментарий
       * можем ли мы потребовать от сервисов, чтобы тип комментария, который они используют, поддерживал метод приведения к типу List<String>?
 */

interface BlackListFilter{

    default <T extends Iterable> List<T> filterComments(T comments, Set<String> blackList){

        Iterator commentsIterator = comments.iterator();
        while (commentsIterator.hasNext()){

                //System.out.println(commentsIterator.next());
        }




        return new ArrayList<>();
    }
}




public class Task14 {

    public static void main(String[] args){


        List<String> comments = new ArrayList<>();
        for (int i =0; i < 9; i++){
            comments.add("badWord" + (i+1));
            comments.add("goodWord" + (i+1) + " " + "badWord" + (i+1) + " goodWord" + (i+1));
        }


        Set<String> blackList = new HashSet<>();
        for (int i =0; i<9;i++){
            blackList.add("badWord" + (i+1));
        }


        System.out.println("comments: " + comments);
        System.out.println("blackList: " + blackList);
        new BlackListFilter(){}.filterComments(comments, blackList);
        System.out.println("Функция filterComments отработала");
        System.out.println("comments: " + comments);
        System.out.println("blackList: " + blackList);
    }
}


/*List<String> people = new ArrayList<>();
        people.add("Vanya");
        people.add("Vlad");
        people.add("Danya");
        people.add("Andrey");
        people.add("Kirill");
        people.add("Vanya Ivanov");

        Iterator<String> peopleIterator = people.iterator();
        while (peopleIterator.hasNext()){
            System.out.println(peopleIterator.next() + " Komarov");
        }*/