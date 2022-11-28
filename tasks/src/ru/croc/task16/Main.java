package ru.croc.task16;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main {

    /*Этот метод возвращает список с абсолютными путями лог-файлов*/
    public static void getLogFilesFromDirectory(final File folder, List<String> paths) {
        for (final File fileEntry : Objects.requireNonNull(folder.listFiles())) {
            if (fileEntry.isDirectory()) {
                getLogFilesFromDirectory(fileEntry, paths);
            } else if ( fileEntry.getAbsolutePath().matches(".+(.log)") ||
                    fileEntry.getAbsolutePath().matches(".+(.trace)") )
                paths.add(fileEntry.getAbsolutePath());
        }
    }


    /*Этот метод читает файл до пробела или до конца переноса строки
    и в качестве результата возвращает экземпляр класса String*/
    public static String read(BufferedReader br) throws IOException {
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


    /*Этот метод возвращает индекс элемента
     с минимамальным значением time*/
    public static int indexOfMin(List<TimeStamp> timeStamps){
        int minInd = 0;
        for (int i = 0; i < timeStamps.size(); i++){
            if ( timeStamps.get(i).time() < timeStamps.get(minInd).time() ) minInd = i;
        }
        return minInd;
    }



    public static List<TimeStamp> getTimeStamps(List<String> logFilesPaths) throws IOException {
        int filesNumber = logFilesPaths.size();
        BufferedReader[] bufferedReaders = new BufferedReader[filesNumber];
        for (int i = 0; i < filesNumber; i++){
            bufferedReaders[i] = new BufferedReader(new FileReader(logFilesPaths.get(i)));
        }
        List<TimeStamp> timeStamps = new ArrayList<>(filesNumber);
        for (int i =0 ; i < filesNumber; i++){
            String a = read(bufferedReaders[i]);
            timeStamps.add(new TimeStamp(bufferedReaders[i],Long.parseLong(a)));
        }
        return timeStamps;
    }


    public static void printMessagesFromLogs(List<TimeStamp> timeStamps) throws IOException {
        int endedLogs = 0;
        int filesNumber = timeStamps.size();
        while(endedLogs < filesNumber){
            int minIndex = indexOfMin(timeStamps);
            String outputString = read(timeStamps.get(minIndex).bufferedReader());
            System.out.println(outputString);
            String newTimeString = read(timeStamps.get(minIndex).bufferedReader());
            if (newTimeString == null){//это значит, что этот файл закончился
                timeStamps.remove(minIndex);
                endedLogs++;
            } else{
                TimeStamp oldTimeStamp = timeStamps.get(minIndex);
                oldTimeStamp.setTime(Long.parseLong(newTimeString));
                timeStamps.set(minIndex, oldTimeStamp);
            }
        }
    }



    public static void main(String[] args) throws IOException {
        String path = "/home/andrew/test16";//args[0];
        final File folder = new File(path);
        List<String> logFilesPaths = new ArrayList<>();
        getLogFilesFromDirectory(folder, logFilesPaths);
        printMessagesFromLogs(getTimeStamps(logFilesPaths));
    }
}