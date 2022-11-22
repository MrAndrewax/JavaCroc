package ru.croc.task13;

import java.io.*;
import java.util.*;

class User{
    private List<Integer> history;
    User(int... films){
        history = new ArrayList<>();
        for(int film : films){
            history.add(film);
        }
    }


    public List<Integer> getHistory(){
        return history;
    }

    public void setHistory(List<Integer> history){
        this.history = history;
    }

    @Override
    public String toString() {
        return history.toString();
    }
}

class Film{
    private final int id;
    private final String filmName;

    public int getId(){
        return id;
    }

    public String getFilmName(){
        return filmName;
    }

    Film(int id, String filmName){
        this.id = id;
        this.filmName = filmName;
    }
    @Override
    public String toString() {
        return "("+ id +", " + filmName + ")";
    }
}
class RecommenderSystem {

    public static User stringToUser(String string){
        String[] strFilms = string.split(",");
        int[] intFilms = new int[strFilms.length];
        for (int i =0; i < strFilms.length; i++){
            intFilms[i] = Integer.parseInt(strFilms[i]);
        }
        return new User(intFilms);
    }
    public static Film stringToFilm(String line){
        String[] strIdAndFilm = line.split(",");
        int id = Integer.parseInt(strIdAndFilm[0]);
        String filmName = strIdAndFilm[1];
        return new Film(id, filmName);
    }


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
                films.put(film.getId(), film.getFilmName());
                // считываем остальные строки в цикле
                line = reader.readLine();
            }
            return films;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


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
    public static void removeViewedFilms(User user, List<User> usersWithSimilarTaste){
        for (User user2 : usersWithSimilarTaste){
            List<Integer> history = user2.getHistory();
            history.removeIf(user.getHistory()::contains);
            user2.setHistory(history);
        }
        Iterator<User> iterator = usersWithSimilarTaste.iterator();//создаем итератор
        while(iterator.hasNext()) {//до тех пор, пока в списке есть элементы

            User nextUser = iterator.next();//получаем следующий элемент
            if ( nextUser.getHistory().isEmpty() ){
                iterator.remove();//удаляем кота с нужным именем
            }
        }

    }
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

        if(films.containsKey(filmId)){
            return films.get(filmId);
        } else{
            return null;
        }
    }


}

public class Task13 {
    public static void main(String[] args){
        List <User> users;
        Map<Integer, String> films;
        users = RecommenderSystem.parseFileWithUsers();
        films = RecommenderSystem.parseFileWithFilms();
        System.out.println("Истории просмотров всех пользователей: " + users);
        System.out.println("Все фильмы: " + films);
        assert users != null;
        List<User> usersWithSimilarTaste = RecommenderSystem.getUsersWithSimilarTaste(users.get(0), users);
        RecommenderSystem.removeViewedFilms(users.get(0), usersWithSimilarTaste);
        System.out.println("Отобранные списки без фильмов, которые пользователь уже смотрел: " + usersWithSimilarTaste);
        String filmName = RecommenderSystem.getTheMostViewedFilm(usersWithSimilarTaste, films);
        System.out.println(filmName);
    }
}
