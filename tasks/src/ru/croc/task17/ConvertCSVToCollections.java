package ru.croc.task17;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConvertCSVToCollections{
    public Map<Integer, Order> getOrders(List<Line> lines){
        Map<Integer, Order> orders = new HashMap<>();
        for (Line line : lines){
            if (!orders.containsKey(line.orderNumber)){
                Order order = new Order(line.orderNumber, line.userLogin);
                orders.put(line.orderNumber, order);
            }
        }
        System.out.println(orders);
        return orders;
    }
    public Map<String, Product> getProducts(List<Line> lines){
        Map<String, Product> products = new HashMap<>();
        for (Line line : lines){
            if (!products.containsKey(line.productNumber)){
                Product order = new Product(line.productNumber, line.productName, line.price);
                products.put(line.productNumber, order);
            }
        }
        System.out.println(products);
        return products;
    }
    public List<Pair> getPairs(List<Line> lines, Map<Integer, Order> orders, Map<String, Product> products){

        List<Pair> pairs = new ArrayList<>();

        for (Line line : lines){
            Order order = orders.get(line.orderNumber);
            Product product = products.get(line.productNumber);
            Pair pair = new Pair(order.orderId, product.productId);
            pairs.add(pair);
        }
        return pairs;
    }

}