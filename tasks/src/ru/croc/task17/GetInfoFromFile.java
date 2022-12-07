package ru.croc.task17;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetInfoFromFile{
    public Line stringToLine(String line){
        String[] arr = line.split(",");
        int orderNumber = Integer.parseInt(arr[0].trim());
        String userLogin = arr[1].trim();
        String productNumber = arr[2].trim();
        String productName = arr[3].trim();
        int price = Integer.parseInt(arr[4].trim());
        return new Line(orderNumber, userLogin, productNumber, productName, price);
    }
    public List<Line> parseFileWithUsers(String path){
        try (FileReader fr = new FileReader(path);
             BufferedReader reader = new BufferedReader(fr)){
            List <Line> orders = new ArrayList<>();
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