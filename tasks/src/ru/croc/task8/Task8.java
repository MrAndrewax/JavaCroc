package ru.croc.task8;
import java.io.*;

import java.io.FileReader;

public class Task8 {
    public static void main(String[] args) throws IOException {
        String path = args[0];
        StringBuilder textFromFile;

        try (FileReader reader = new FileReader(path)){
            textFromFile = new StringBuilder("");
            int c;
            while ((c = reader.read()) != -1) {
                textFromFile.append((char) c);
            }
        }

        String clearText = String.valueOf(textFromFile).trim()
                .replaceAll("\n"," ")//заменяем символы переноса строки на пробелы
                .replaceAll(" +", " ");//удаляем дубликаты пробелов

        if (clearText.equals("")){//если строка пустая
            System.out.println(0);
        } else {
            String[] words = clearText.split("\\s");
            System.out.println(words.length);
        }
    }
}
