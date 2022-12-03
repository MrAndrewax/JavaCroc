package ru.croc.task13;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class RecommenderSystem {
    /*isSimilarTaste возврашает true в случае,
     если второй пользователь user2 посмотрел минимум половину фильмов пользователя,
     для которого формируется рекомендация*/
    public boolean isSimilarTaste(User user1, User user2){
        List<Integer> history1 = user1.getHistory();
        List<Integer> history2 = user2.getHistory();

        Set<Integer> historySet1 = new HashSet<>(history1);
        Set<Integer> historySet2 = new HashSet<>(history2);

        int counter = 0;
        for (int film : historySet1){
            if (historySet2.contains(film)){
                counter++;
            }
        }
        return counter >= history1.size() / 2;
    }

    /*Получаем список всех пользователей, для которых isSimilarTaste вернула true  */
    public List<User> getUsersWithSimilarTaste(User user, List<User> users){
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
    public void removeViewedFilms(User user, List<User> usersWithSimilarTaste){
        for (User user2 : usersWithSimilarTaste){
            List<Integer> history = user2.getHistory();
            history.removeIf(user.getHistory()::contains);
            user2.setHistory(history);
        }
        usersWithSimilarTaste.removeIf(nextUser -> nextUser.getHistory().isEmpty());
    }

    /*Возвращает имя наиболее просматриваемого фильма*/
    public String getTheMostViewedFilm(List<User> usersWithSimilarTaste, Map<Integer, String> films){
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


