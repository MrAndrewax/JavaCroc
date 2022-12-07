package ru.croc.task18;

import java.sql.*;
import java.util.List;

public class OrderDAO{

    static final String DB_URL = "jdbc:postgresql://localhost/testDB";//args[0]
    static final String USER = "postgres";//args[1]
    static final String PASSWORD = "pass!";//args[2]

    User createOrder(String userLogin, List<Product> products){

        Connection connection;
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            Statement stmt = connection.createStatement();

            String sql  = "SELECT user_id FROM users WHERE user_name = " + userLogin;
            ResultSet resultSet = stmt.executeQuery(sql);
            int userId = resultSet.getInt("id");

            for (Product product : products){//Добавляем продукты, которых нет в таблице products
                sql = String.format("INSERT IF NOT EXISTS INTO products (product_id, product_name, price) VALUES ('%s', '%s', '%d')",
                        product.productId, product.productName, product.price);
                stmt.executeUpdate(sql);
            }

            for (Product product : products){
                sql = String.format("INSERT INTO orders VALUES ('%d', '%s')", userId, product.productId);
                stmt.executeUpdate(sql);
            }
            connection.close();
            return null;

        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return null;
        }
    }
    //
    // Создание заказа. Для указанного пользователя в базе данных создается новый заказ с заданным списком товаров.
}