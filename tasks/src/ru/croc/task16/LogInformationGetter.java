package ru.croc.task16;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class LogInformationGetter {
    /*Этот метод возвращает список с абсолютными путями лог-файлов*/
    public void getLogFilesFromDirectory(final File folder, List<String> paths) {
        for (final File fileEntry : Objects.requireNonNull(folder.listFiles())) {
            if (fileEntry.isDirectory()) {
                getLogFilesFromDirectory(fileEntry, paths);
            } else if ( fileEntry.getAbsolutePath().matches(".+(.log|.LOG|.trace|.TRACE)") )
                paths.add(fileEntry.getAbsolutePath());
        }
    }

    /*Этот метод читает файл до пробела или до конца переноса строки
    и в качестве результата возвращает экземпляр класса String*/
    public String readTime(BufferedReader br) throws IOException {
        int character;
        StringBuilder sb = new StringBuilder();
        while ((character = br.read()) != -1) {
            char ch = (char) character;
            if (character == ' ' || character == '\n') break;
            sb.append(ch);
        }
        String result = sb.toString();
        if (result.equals("")){
            return null;
        }
        return result;
    }

    /*Этот метод читает файл до конца переноса строки
    и в качестве результата возвращает экземпляр класса String*/
    public String readMessage(BufferedReader br) throws IOException {
        int character;
        StringBuilder sb = new StringBuilder();
        while ((character = br.read()) != -1) {
            char ch = (char) character;
            if (character == '\n') break;
            sb.append(ch);
        }
        String result = sb.toString();
        if (result.equals("")){
            return null;
        }
        return result;
    }

}