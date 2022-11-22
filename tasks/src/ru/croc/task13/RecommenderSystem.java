package ru.croc.task13;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecommenderSystem {

    /*Функция stringToUser парсит строку и возвращает объект класса User*/
    public static User stringToUser(String string){
        String[] strFilms = string.split(",");
        int[] intFilms = new int[strFilms.length];
        for (int i =0; i < strFilms.length; i++){
            intFilms[i] = Integer.parseInt(strFilms[i]);
        }
        return new User(intFilms);
    }

    /*Функция stringToFilm парсит строку и возвращает объект класса Film*/
    public static Film stringToFilm(String line){
        String[] strIdAndFilm = line.split(",");
        int id = Integer.parseInt(strIdAndFilm[0]);
        String filmName = strIdAndFilm[1];
        return new Film(id, filmName);
    }

    /*Функция parseFileWithUsers парсит файл с историями просмотров
      и возвращает список экземпляров класса User*/
    public static List<User> parseFileWithUsers(){
        try {
            File file = new File("/home/andrew/learning/java_croc/tasks/src/ru/croc/task13/2.txt");
            //создаем объект FileReader для объекта File
            FileReader fr = new FileReader(file);
            //создаем BufferedReader с существующего FileReader для построчного считывания
            BufferedReader reader = new BufferedReader(fr);
            List <User> users = new ArrayList<>();
            // считаем сначала первую строку
            String line = reader.readLine();
            while (line != null) {
                //System.out.println(line);
                users.add(stringToUser(line));
                // считываем остальные строки в цикле
                line = reader.readLine();
            }
            return users;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*Функция parseFileWithFilms парсит файл с фильмами
      и возвращает хеш таблицу, где ключом является идентификатор фильма,
        а значением - название фильма*/
    public static Map<Integer, String> parseFileWithFilms(){
        try {
            File file = new File("/home/andrew/learning/java_croc/tasks/src/ru/croc/task13/1.txt");
            //создаем объект FileReader для объекта File
            FileReader fr = new FileReader(file);
            //создаем BufferedReader с существующего FileReader для построчного считывания
            BufferedReader reader = new BufferedReader(fr);
            // считаем сначала первую строку

            Map<Integer, String> films = new HashMap<>();
            String line = reader.readLine();
            while (line != null) {
                Film film = stringToFilm(line);
                films.put(film.id(), film.filmName());
                // считываем остальные строки в цикле
                line = reader.readLine();
            }
            return films;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*isSimilarTaste возврашает true в случае,
     если второй пользователь user2 посмотрел минимум половину фильмов пользователя,
     для которого формируется рекомендация*/
    public static boolean isSimilarTaste(User user1, User user2){
        List<Integer> history1 = user1.getHistory();
        List<Integer> history2 = user2.getHistory();
        int counter = 0;
        for (int film : history1){
            if (history2.contains(film)){
                counter++;
            }
        }
        return counter >= history1.size() / 2;
    }

    /*Получаем список всех пользователей, для которых isSimilarTaste вернула true  */
    public static List<User> getUsersWithSimilarTaste(User user, List<User> users){
        List<User> usersWithSimilarTaste = new ArrayList<>();
        for (User user2 : users){
            if (isSimilarTaste(user, user2)){
                usersWithSimilarTaste.add(user2);
            }
        }
        usersWithSimilarTaste.remove(user);
        return usersWithSimilarTaste;
    }

    /*метод removeViewedFilms удаляет из списка,
    который вернул метод getUsersWithSimilarTaste,
    фильмы, просмотренные пользователем*/
    public static void removeViewedFilms(User user, List<User> usersWithSimilarTaste){
        for (User user2 : usersWithSimilarTaste){
            List<Integer> history = user2.getHistory();
            history.removeIf(user.getHistory()::contains);
            user2.setHistory(history);
        }
        usersWithSimilarTaste.removeIf(nextUser -> nextUser.getHistory().isEmpty());
    }

    /*Возвращает имя наиболее просматриваемого фильма*/
    public static String getTheMostViewedFilm(List<User> usersWithSimilarTaste, Map<Integer, String> films){
        HashMap<Integer, Integer> tableWithViews = new HashMap<>();
        for (User userWithSimilarTaste : usersWithSimilarTaste){
            List<Integer> history = userWithSimilarTaste.getHistory();
            for (int film : history){
                if (!tableWithViews.containsKey(film)){
                    tableWithViews.put(film, 0);
                }
                else{
                    int oldViews = tableWithViews.get(film);
                    tableWithViews.put(film, oldViews + 1);
                }
            }
        }
        Map.Entry<Integer, Integer>  maxEntry = null;
        for (Map.Entry<Integer, Integer> entry : tableWithViews.entrySet())
        {
            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)
            {
                maxEntry = entry;
            }
        }
        assert maxEntry != null;
        int filmId = maxEntry.getKey();

        //у нас есть id фильма. Мы хотим найти его название

        return films.getOrDefault(filmId, null);
    }
}