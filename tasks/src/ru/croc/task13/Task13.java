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

        RecommenderSystem recommenderSystem = new RecommenderSystem();
        GetterFromFile getterFromFile = new GetterFromFile();

        List <User> users;
        Map<Integer, String> films;
        users = getterFromFile.parseFileWithUsers();
        films = getterFromFile.parseFileWithFilms();
        assert users != null;

        Scanner in = new Scanner(System.in);
        System.out.print("Введите номер пользователя (от 0 до " + users.size() + ", для которого ищете рекомендацию: ");
        int userNumber = in.nextInt();

        List<User> usersWithSimilarTaste = recommenderSystem.getUsersWithSimilarTaste(users.get(userNumber), users);
        recommenderSystem.removeViewedFilms(users.get(userNumber), usersWithSimilarTaste);
        System.out.println("Отобранные списки без фильмов, которые пользователь уже смотрел: " + usersWithSimilarTaste);
        String filmName = recommenderSystem.getTheMostViewedFilm(usersWithSimilarTaste, films);
        System.out.println("Рекомендованный фильм: " + filmName);
    }
}
