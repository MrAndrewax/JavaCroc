package ru.croc.task13;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class dataGetterFromFile {
    /*Функция stringToUser парсит строку и возвращает объект класса User*/
    public User stringToUser(String string){
        String[] strFilms = string.split(",");
        int[] intFilms = new int[strFilms.length];
        for (int i =0; i < strFilms.length; i++){
            intFilms[i] = Integer.parseInt(strFilms[i]);
        }
        return new User(intFilms);
    }

    /*Функция stringToFilm парсит строку и возвращает объект класса Film*/
    public Film stringToFilm(String line){
        String[] strIdAndFilm = line.split(",");
        int id = Integer.parseInt(strIdAndFilm[0]);
        String filmName = strIdAndFilm[1];
        return new Film(id, filmName);
    }

    /*Функция parseFileWithUsers парсит файл с историями просмотров
      и возвращает список экземпляров класса User*/
    public List<User> parseFileWithUsers(String path){
        try (FileReader fr = new FileReader(path);
             BufferedReader reader = new BufferedReader(fr)){
            List <User> users = new ArrayList<>();
            String line = reader.readLine();
            while (line != null) {
                users.add(stringToUser(line));
                line = reader.readLine();
            }
            return users;
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    /*Функция parseFileWithFilms парсит файл с фильмами
      и возвращает хеш таблицу, где ключом является идентификатор фильма,
        а значением - название фильма*/
    public Map<Integer, String> parseFileWithFilms(String path){
        try (FileReader fr = new FileReader(path);
             BufferedReader reader = new BufferedReader(fr)){
            Map<Integer, String> films = new HashMap<>();
            String line = reader.readLine();
            while (line != null) {
                Film film = stringToFilm(line);
                films.put(film.id(), film.filmName());
                line = reader.readLine();
            }
            return films;
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
