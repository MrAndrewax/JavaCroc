package ru.croc.task16;

import java.io.*;
import java.util.ArrayList;
import java.util.List;



public class Main {
    /*Этот метод возвращает индекс элемента
     с минимамальным значением time*/
    public static int indexOfMin(List<TimeStamp> timeStamps){
        int minInd = 0;
        for (int i = 0; i < timeStamps.size(); i++){
            if ( timeStamps.get(i).time() < timeStamps.get(minInd).time() ) minInd = i;
        }
        return minInd;
    }

    public static void main(String[] args) throws IOException {
        LogInformationGetter getter = new LogInformationGetter();
        MessagesFromLogsGetter messagesFromLogsGetter = new MessagesFromLogsGetter();

        String path = "/home/andrew/test16";//args[0];
        final File folder = new File(path);
        List<String> logFilesPaths = new ArrayList<>();
        getter.getLogFilesFromDirectory(folder, logFilesPaths);

        messagesFromLogsGetter.printMessagesFromLogs(
                messagesFromLogsGetter.getTimeStamps(logFilesPaths)
        );
    }
}