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
    public void setInformationInDB(){
        String path = "/home/andrew/learning/java_croc/tasks/src/ru/croc/task17/table.CSV";//args[0]

        GetInfoFromFile getter = new GetInfoFromFile();
        List<Line> lines = getter.parseFileWithUsers(path);

        Connection connection;
        try {

            connection = DriverManager.getConnection(this.DB_URL, this.USER, this.PASSWORD);
            Statement stmt = connection.createStatement();

            String createProductsTable  = "DROP TABLE IF EXISTS products; " +
                    "CREATE TABLE products " +
                    "(id VARCHAR(255), " +
                    "product_number VARCHAR(255)," +
                    " product_name VARCHAR(255), " +
                    " price int, " +
                    " PRIMARY KEY ( id ));";
            String createOrdersTable = "DROP TABLE IF EXISTS orders; " +
                    "CREATE TABLE orders " +
                    "(id int not NULL, " +
                    " user_name VARCHAR(255)," +
                    " PRIMARY KEY ( id ));";

            String createOrderProductsTable = "DROP TABLE IF EXISTS orders_products; " +
                    "CREATE TABLE orders_products " +
                    "(order_id int," +
                    " product_id VARCHAR(255));";

            stmt.executeUpdate(createProductsTable);
            stmt.executeUpdate(createOrdersTable);
            stmt.executeUpdate(createOrderProductsTable);

            ConvertCSVToCollections converter = new ConvertCSVToCollections();

            Map<Integer, Order> orders = converter.getOrders(lines);
            Map<String, Product> products = converter.getProducts(lines);
            List<Pair> pairs = converter.getPairs(lines, orders, products);

            for (Map.Entry<Integer, Order> orderEntry : orders.entrySet()) {
                String sql = String.format("INSERT INTO orders VALUES ('%d', '%s')", orderEntry.getKey(), orderEntry.getValue().userLogin);
                stmt.executeUpdate(sql);
            }
            for (Map.Entry<String, Product> productEntry : products.entrySet()) {
                String sql = String.format("INSERT INTO products VALUES ('%s', '%s', '%d')", productEntry.getKey(), productEntry.getValue().productName, productEntry.getValue().price);
                stmt.executeUpdate(sql);
            }
            for (Pair pair : pairs){
                String sql = String.format("INSERT INTO orders_products VALUES ('%d', '%s')", pair.orderId, pair.productId);
                stmt.executeUpdate(sql);
            }



        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
        }
    }
}