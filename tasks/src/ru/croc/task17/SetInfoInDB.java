package ru.croc.task17;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;


public class SetInfoInDB{
    final String DB_URL;
    final String USER;
    final String PASSWORD;
    SetInfoInDB(String DB_URL, String USER, String PASSWORD){
        this.DB_URL = DB_URL;
        this.USER = USER;
        this.PASSWORD = PASSWORD;
    }
    public void setInformationInDB(String path){
        GetInfoFromFile getter = new GetInfoFromFile();
        List<OrderInfo> orderInfos = getter.parseFileWithUsers(path);

        Connection connection;
        try {

            connection = DriverManager.getConnection(this.DB_URL, this.USER, this.PASSWORD);
            Statement stmt = connection.createStatement();

            String createProductsTable  = "DROP TABLE IF EXISTS products; " +
                    "CREATE TABLE products " +
                    "(product_id VARCHAR(255)," +
                    " product_name VARCHAR(255), " +
                    " price int, " +
                    " PRIMARY KEY (product_id));";
            String createUsersTable = "DROP TABLE IF EXISTS users; " +
                    "CREATE TABLE users " +
                    "(user_id int not NULL, " +
                    " user_name VARCHAR(255)," +
                    " PRIMARY KEY (user_id));";
            String createOrdersTable = "DROP TABLE IF EXISTS orders; " +
                    "CREATE TABLE orders" +
                    "(user_id int," +
                    " product_id VARCHAR(255));";
            stmt.executeUpdate(createProductsTable);
            stmt.executeUpdate(createUsersTable);
            stmt.executeUpdate(createOrdersTable);

            ConvertCSVToCollections converter = new ConvertCSVToCollections();

            Map<Integer, User> users = converter.getOrders(orderInfos);
            Map<String, Product> products = converter.getProducts(orderInfos);
            List<Order> orders = converter.getPairs(orderInfos, users, products);

            for (Map.Entry<Integer, User> orderEntry : users.entrySet()) {
                String sql = String.format("INSERT INTO users VALUES ('%d', '%s')", orderEntry.getValue().userId, orderEntry.getValue().userName);
                stmt.executeUpdate(sql);
            }
            for (Map.Entry<String, Product> productEntry : products.entrySet()) {
                String sql = String.format("INSERT INTO products VALUES ('%s', '%s', '%d')", productEntry.getValue().productId, productEntry.getValue().productName, productEntry.getValue().price);
                stmt.executeUpdate(sql);
            }
            for (Order order : orders){
                String sql = String.format("INSERT INTO orders VALUES ('%d', '%s')", order.userID, order.productId);
                stmt.executeUpdate(sql);
            }
        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
        }
    }
}