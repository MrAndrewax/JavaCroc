package ru.croc.task17;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConvertCSVToCollections{
    public Map<Integer, User> getOrders(List<OrderInfo> orderInfos){
        Map<Integer, User> orders = new HashMap<>();
        for (OrderInfo orderInfo : orderInfos){
            if (!orders.containsKey(orderInfo.userId)){
                User user = new User(orderInfo.userId, orderInfo.userName);
                orders.put(orderInfo.userId, user);
            }
        }
        System.out.println(orders);
        return orders;
    }
    public Map<String, Product> getProducts(List<OrderInfo> orderInfos){
        Map<String, Product> products = new HashMap<>();
        for (OrderInfo orderInfo : orderInfos){
            if (!products.containsKey(orderInfo.productId)){
                Product order = new Product(orderInfo.productId, orderInfo.productName, orderInfo.price);
                products.put(orderInfo.productId, order);
            }
        }
        System.out.println(products);
        return products;
    }
    public List<Order> getPairs(List<OrderInfo> orderInfos, Map<Integer, User> orders, Map<String, Product> products){

        List<Order> pairs = new ArrayList<>();

        for (OrderInfo orderInfo : orderInfos){
            User user = orders.get(orderInfo.userId);
            Product product = products.get(orderInfo.productId);
            Order pair = new Order(user.userId, product.productId);
            pairs.add(pair);
        }
        return pairs;
    }

}