package ru.croc.task13;

import java.util.*;

record Film(int id, String filmName) {
    @Override
    public String toString() {
        return "(" + id + ", " + filmName + ")";
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
        System.out.println("Рекомендованный фильм: " + filmName);
    }
}
