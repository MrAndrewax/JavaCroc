package ru.croc.task13;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetterFromFile{
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
    public List<User> parseFileWithUsers(){
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
    public Map<Integer, String> parseFileWithFilms(){
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
}
