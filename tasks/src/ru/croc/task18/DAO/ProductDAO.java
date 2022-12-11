package ru.croc.task18.DAO;

import ru.croc.task18.config.DataBaseInfo;
import ru.croc.task18.auxiliaryClasses.Product;

import java.sql.*;

public class ProductDAO{
    public Product findProduct(String productCode){
        try (Connection connection = DriverManager.getConnection(DataBaseInfo.DB_URL, DataBaseInfo.USER, DataBaseInfo.PASSWORD);
             Statement stmt = connection.createStatement()){

            String sql = String.format("SELECT * FROM products WHERE product_id = '%s'", productCode);
            ResultSet resultSet = stmt.executeQuery(sql);

            if (resultSet.next()){
                String productID = resultSet.getString("product_id");
                String productName = resultSet.getString("product_name");
                int price = resultSet.getInt("price");
                return new Product(productID, productName, price);
            }
            else{
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Product createProduct(Product product){
        try (Connection connection = DriverManager.getConnection(DataBaseInfo.DB_URL, DataBaseInfo.USER, DataBaseInfo.PASSWORD);
             Statement stmt = connection.createStatement()){

            String sql = String.format("INSERT INTO products VALUES ('%s', '%s', '%d')",
                    product.getProductId(), product.getProductName(), product.getPrice());
            stmt.executeUpdate(sql);

            sql = String.format("SELECT * FROM products WHERE product_id = '%s'", product.getProductId());
            ResultSet resultSet = stmt.executeQuery(sql);
            if (resultSet.next()){
                String productID = resultSet.getString("product_id");
                String productName = resultSet.getString("product_name");
                int price = resultSet.getInt("price");
                return new Product(productID, productName, price);
            }
            else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Product updateProduct(Product product){
        try (Connection connection = DriverManager.getConnection(DataBaseInfo.DB_URL, DataBaseInfo.USER, DataBaseInfo.PASSWORD);
             Statement stmt = connection.createStatement()){

            String sql = String.format("UPDATE products SET product_name = '%s', price = '%d' WHERE product_id = '%s'",
                    product.getProductName(), product.getPrice(), product.getProductId());
            stmt.executeUpdate(sql);//обработать.

            sql = String.format("SELECT * FROM products WHERE product_id = '%s'", product.getProductId());
            ResultSet resultSet = stmt.executeQuery(sql);
            if (resultSet.next()){
                String productID = resultSet.getString("product_id");
                String productName = resultSet.getString("product_name");
                int price = resultSet.getInt("price");
                return new Product(productID, productName, price);
            }
            else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteProduct(String productCode){
        try (Connection connection = DriverManager.getConnection(DataBaseInfo.DB_URL, DataBaseInfo.USER, DataBaseInfo.PASSWORD);
             Statement stmt = connection.createStatement()){
            String sql;

            sql = String.format("DELETE FROM orders WHERE product_id = '%s'", productCode);
            stmt.executeUpdate(sql);

            sql = String.format("DELETE FROM products WHERE product_id = '%s'", productCode);
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}



