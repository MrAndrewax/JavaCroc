package ru.croc.task17;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetInfoFromFile{
    public OrderInfo stringToLine(String line){
        String[] arr = line.split(",");
        return new OrderInfo(
                Integer.parseInt(arr[0].trim()), arr[1].trim(),
                arr[2].trim(), arr[3].trim(), Integer.parseInt(arr[4].trim()));
    }
    public List<OrderInfo> parseFileWithUsers(String path){
        try (FileReader fr = new FileReader(path);
             BufferedReader reader = new BufferedReader(fr)){
            List <OrderInfo> orders = new ArrayList<>();
            String line = reader.readLine();
            while (line != null) {
                orders.add(stringToLine(line));
                line = reader.readLine();
            }
            return orders;
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}