package ru.croc.task17;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetInfoFromFile{
    public OrderInfo stringToLine(String line){
        String[] arr = line.split(",");
        int orderId = Integer.parseInt(arr[0].trim());
        String userLogin = arr[1].trim();
        String productId = arr[2].trim();
        String productName = arr[3].trim();
        int price = Integer.parseInt(arr[4].trim());
        return new OrderInfo(orderId, userLogin, productId, productName, price);
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