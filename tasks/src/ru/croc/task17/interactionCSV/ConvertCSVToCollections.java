package ru.croc.task17.interactionCSV;

import ru.croc.task17.importantClasses.Order;
import ru.croc.task17.importantClasses.OrderInfo;
import ru.croc.task17.importantClasses.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConvertCSVToCollections{
    public Map<String, Product> getProducts(List<OrderInfo> orderInfos){
        Map<String, Product> products = new HashMap<>();
        for (OrderInfo orderInfo : orderInfos){
            if (!products.containsKey(orderInfo.getProductId())){
                products.put(orderInfo.getProductId(),
                        new Product(orderInfo.getProductId(), orderInfo.getProductName(), orderInfo.getPrice()));
            }
        }
        return products;
    }

    public List<Order> getOrders(List<OrderInfo> orderInfos){
        List<Order> orders = new ArrayList<>();
        for (OrderInfo orderInfo : orderInfos){
            orders.add(new Order(
                    orderInfo.getOrderID(), orderInfo.getUserLogin(), orderInfo.getProductId()
            ));
        }
        return orders;
    }
}