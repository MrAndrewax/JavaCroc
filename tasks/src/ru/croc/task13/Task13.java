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
        dataGetterFromFile dataGetterFromFile = new dataGetterFromFile();

        List <User> users;
        Map<Integer, String> films;
        users = dataGetterFromFile.parseFileWithUsers(args[0]);
        films = dataGetterFromFile.parseFileWithFilms(args[1]);

        System.out.println(users);

        assert users != null;
        assert films != null;

        Scanner in = new Scanner(System.in);
        System.out.print("Введите список просмотров текущего пользователя: ");
        String currentUserStr = in.nextLine();
        User currentUser = dataGetterFromFile.stringToUser(currentUserStr);

        try {
            List<User> usersWithSimilarTaste = recommenderSystem.getUsersWithSimilarTaste(currentUser, users);
            System.out.println(usersWithSimilarTaste);
            recommenderSystem.removeViewedFilms(currentUser, usersWithSimilarTaste);
            System.out.println("Отобранные списки без фильмов, которые пользователь уже смотрел: " + usersWithSimilarTaste + "\n");
            Map<Integer, Integer> tableWithViews = recommenderSystem.getTableWithViews(usersWithSimilarTaste);
            int filmId = recommenderSystem.getTheMostViewedFilm(tableWithViews);
            System.out.println(filmId);
            System.out.println("Рекомендованный фильм: " + films.get(filmId));
        }
        catch (NoUsersException exception){
            System.out.println("Пользователей с похожим вкусом нет.\nСервис не может предложить рекомендацию.");
        }

    }
}
