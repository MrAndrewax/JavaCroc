package ru.croc.task16;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MessagesFromLogsGetter {
    public List<TimeStamp> getTimeStamps(List<String> logFilesPaths) throws IOException {

        LogInformationGetter get = new LogInformationGetter();

        int filesNumber = logFilesPaths.size();
        BufferedReader[] bufferedReaders = new BufferedReader[filesNumber];
        for (int i = 0; i < filesNumber; i++){
            bufferedReaders[i] = new BufferedReader(new FileReader(logFilesPaths.get(i)));
        }
        List<TimeStamp> timeStamps = new ArrayList<>(filesNumber);
        for (int i =0 ; i < filesNumber; i++){
            String a = get.readTime(bufferedReaders[i]);
            timeStamps.add(new TimeStamp(bufferedReaders[i],Long.parseLong(a)));
        }
        return timeStamps;
    }


    public void printMessagesFromLogs(List<TimeStamp> timeStamps) throws IOException {

        LogInformationGetter get = new LogInformationGetter();

        int endedLogs = 0;
        int filesNumber = timeStamps.size();
        while(endedLogs < filesNumber){
            int minIndex = Main.indexOfMin(timeStamps);
            String outputString = get.readMessage(timeStamps.get(minIndex).bufferedReader());
            System.out.println(timeStamps.get(minIndex).time() + " " + outputString);
            String newTimeString = get.readTime(timeStamps.get(minIndex).bufferedReader());
            if (newTimeString == null){//это значит, что этот файл закончился
                timeStamps.get(minIndex).bufferedReader().close();
                timeStamps.remove(minIndex);
                endedLogs++;
            } else{
                TimeStamp oldTimeStamp = timeStamps.get(minIndex);
                oldTimeStamp.setTime(Long.parseLong(newTimeString));
                timeStamps.set(minIndex, oldTimeStamp);
            }
        }
    }
}