package ru.croc.task17;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConvertCSVToCollections{
    public Map<String, Product> getProducts(List<OrderInfo> orderInfos){
        Map<String, Product> products = new HashMap<>();
        for (OrderInfo orderInfo : orderInfos){
            if (!products.containsKey(orderInfo.productId)){
                Product order = new Product(orderInfo.productId, orderInfo.productName, orderInfo.price);
                products.put(orderInfo.productId, order);
            }
        }
        return products;
    }
    public List<Order> getOrders(List<OrderInfo> orderInfos, Map<String, Product> products){
        List<Order> orders = new ArrayList<>();

        for (OrderInfo orderInfo : orderInfos){
            int orderID = orderInfo.orderID;
            String userLogin = orderInfo.userLogin;
            String productID = orderInfo.productId;
            orders.add(new Order(orderID, userLogin, productID));
        }
        return orders;
    }
}