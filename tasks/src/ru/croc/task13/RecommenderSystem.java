package ru.croc.task13;

import java.util.*;

public class RecommenderSystem {
    /*isSimilarTaste возврашает true в случае,
     если второй пользователь user2 посмотрел минимум половину фильмов пользователя,
     для которого формируется рекомендация*/
    public boolean isSimilarTaste(User user1, User user2){
        List<Integer> history1 = user1.getHistory();
        List<Integer> history2 = user2.getHistory();
        int counter = 0;
        for (int film : history1){
            if (history2.contains(film)){
                //Если среди фильмов второго пользователя есть фильм первого,
                //то увеличиваем счётчик
                counter++;
            }
        }
        /* System.out.println("counter: " + counter);
        System.out.println("Size: " + history2.size());
        System.out.println("HalfSize: " + (history2.size() / 2));*/
        if (counter == 0) return false;
        return  counter >= ( history1.size() / 2);
    }

    /*Получаем список всех пользователей, для которых isSimilarTaste вернула true  */
    public List<User> getUsersWithSimilarTaste(User user, List<User> users) throws NoUsersException {
        List<User> usersWithSimilarTaste = new ArrayList<>();
        for (User user2 : users){
            if (isSimilarTaste(user, user2)){
                //System.out.println(user2);
                usersWithSimilarTaste.add(user2);
            }
        }
        if (usersWithSimilarTaste.isEmpty()){
            throw new NoUsersException();
        }
        return usersWithSimilarTaste;
    }

    /*метод removeViewedFilms удаляет из списка,
    который вернул метод getUsersWithSimilarTaste,
    фильмы, просмотренные пользователем*/
    public void removeViewedFilms(User user, List<User> usersWithSimilarTaste) throws NoUsersException {
        for (User user2 : usersWithSimilarTaste){
            List<Integer> history = user2.getHistory();
            history.removeIf(user.getHistory()::contains);
            user2.setHistory(history);
        }
        usersWithSimilarTaste.removeIf(nextUser -> nextUser.getHistory().isEmpty());
        if (usersWithSimilarTaste.isEmpty()){
            throw new NoUsersException();
        }
    }

    /*Возвращает имя наиболее просматриваемого фильма*/
    public Map<Integer, Integer> getTableWithViews(List<User> usersWithSimilarTaste){
        HashMap<Integer, Integer> tableWithViews = new HashMap<>();//Хэш таблица, где ключ - индекс фильма, а значение - кол-во просмотрво
        for (User userWithSimilarTaste : usersWithSimilarTaste){//итерируемся по всем пользователям
            for (int film : userWithSimilarTaste.getHistory()){//итерируемся по всем фильмам каждого из пользоваталей
                if (!tableWithViews.containsKey(film)){
                    tableWithViews.put(film, 0);//если фильма в табличке нет, то добавляем и ставим в соответствие 0 просмотров
                }
                else{ //если фильм в табличке есть, то увеличиваем просмотры на 1
                    int oldViews = tableWithViews.get(film);
                    tableWithViews.put(film, oldViews + 1);
                }
            }
        }
        return tableWithViews;


    }

    public int getTheMostViewedFilm(Map<Integer, Integer> tableWithViews){
        Map.Entry<Integer, Integer>  maxEntry = null;
        for (Map.Entry<Integer, Integer> entry : tableWithViews.entrySet())
        {
            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)
            {
                maxEntry = entry;
            }
        }
        assert maxEntry != null;

        return maxEntry.getKey();
    }
}


