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

    public SetInfoInDB(String DB_URL, String USER, String PASSWORD){
        this.DB_URL = DB_URL;
        this.USER = USER;
        this.PASSWORD = PASSWORD;
    }
    public void setInformationInDB(String path){
        GetInfoFromFile getter = new GetInfoFromFile();
        List<OrderInfo> orderInfos = getter.parseFileWithUsers(path);

        try (Connection connection = DriverManager.getConnection(this.DB_URL, this.USER, this.PASSWORD);
             Statement stmt = connection.createStatement()){

            stmt.executeUpdate("DROP TABLE IF EXISTS orders;");
            stmt.executeUpdate("DROP TABLE IF EXISTS products;");

            String createProductsTable  =
                    "CREATE TABLE IF NOT EXISTS products " +
                    "(product_id VARCHAR(255)," +
                    "product_name VARCHAR(255), " +
                    "price int, " +
                    "PRIMARY KEY (product_id));";
            String createOrdersTable =
                    "CREATE TABLE IF NOT EXISTS orders" +
                    "(order_id int," +
                    "user_login VARCHAR(255), " +
                    "product_id VARCHAR(255) REFERENCES products(product_id));";

            stmt.executeUpdate(createProductsTable);
            stmt.executeUpdate(createOrdersTable);

            ConvertCSVToCollections converter = new ConvertCSVToCollections();

            Map<String, Product> products = converter.getProducts(orderInfos);
            List<Order> orders = converter.getOrders(orderInfos);

            for (Map.Entry<String, Product> productEntry : products.entrySet()) {
                String sql = String.format("INSERT INTO products VALUES ('%s', '%s', '%d')",
                        productEntry.getValue().getProductId(), productEntry.getValue().getProductName(), productEntry.getValue().getPrice());
                stmt.executeUpdate(sql);
            }
            for (Order order : orders){
                String sql = String.format("INSERT INTO orders VALUES (%d, '%s', '%s')", order.getOrderID(), order.getUserLogin(), order.getProductID());
                stmt.executeUpdate(sql);
            }
        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}