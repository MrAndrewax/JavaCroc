package ru.croc.task18;

import java.sql.*;
import java.util.List;

public class OrderDAO{

    public int getMaxOrderIndex(){
        try (Connection connection = DriverManager.getConnection(DataBaseInfo.DB_URL, DataBaseInfo.USER, DataBaseInfo.PASSWORD);
             Statement stmt = connection.createStatement()){

            String sql  = "SELECT MAX(order_id) AS \"max\" FROM orders";

            ResultSet resultSet = stmt.executeQuery(sql);
            resultSet.next();
            int index = resultSet.getInt("max");


            return index;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    Order createOrder(String userLogin, List<Product> products){
        try (Connection connection = DriverManager.getConnection(DataBaseInfo.DB_URL, DataBaseInfo.USER, DataBaseInfo.PASSWORD);
             Statement stmt = connection.createStatement()){
            String sql;

            for (Product product : products){//Добавляем продукты, которых нет в таблице products
                sql = String.format("INSERT IF NOT EXISTS INTO products (product_id, product_name, price) VALUES ('%s', '%s', '%d')",
                        product.getProductId(), product.getProductName(), product.getPrice());
                stmt.executeUpdate(sql);
            }

            for (Product product : products){
                sql = String.format("INSERT INTO orders VALUES ('%s', '%s')", userLogin, product.getProductId());
                stmt.executeUpdate(sql);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}