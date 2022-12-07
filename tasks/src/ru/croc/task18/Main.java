package ru.croc.task18;

import java.sql.*;
import java.util.List;


class Order{
    int orderId;
    String userLogin;

    Order(int orderId, String customerName){
        this.orderId = orderId;
        this.userLogin = customerName;
    }


    @Override
    public String toString() {
        return orderId + " " + userLogin;
    }
}

class OrderDAO{

    static final String DB_URL = "jdbc:postgresql://localhost/testDB";//args[0]
    static final String USER = "postgres";//args[1]
    static final String PASSWORD = "pass!";//args[2]

    Order createOrder(String userLogin, List<Product> products){

        Connection connection;
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            Statement stmt = connection.createStatement();

            String sql  = "SELECT id FROM orders WHERE user_name = " + userLogin;

            ResultSet resultSet = stmt.executeQuery(sql);

            int orderId = resultSet.getInt("id");

            for (Product product : products){
                sql = String.format("INSERT INTO orders_products VALUES ('%d', '%s')", orderId, product.productId);
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


class Product{
    String productId;
    String productName;
    int price;

    Product(String productId, String productName, int price){
        this.productId = productId;
        this.productName = productName;
        this.price = price;
    }

    @Override
    public String toString() {
        return productId + " " + productName + " " + price;
    }
}


class ProductDAO{

    static final String DB_URL = "jdbc:postgresql://localhost/testDB";//args[0]
    static final String USER = "postgres";//args[1]
    static final String PASSWORD = "pass!";//args[2]

    Product findProduct(String productCode){
        Connection connection;
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            Statement stmt = connection.createStatement();

            String sql  = "SELECT * FROM products WHERE id = "+productCode;

            ResultSet resultSet = stmt.executeQuery(sql);

            String productID = resultSet.getString("id");
            String productName = resultSet.getString("userName");
            int price = resultSet.getInt("price");

            connection.close();

            return new Product(productID, productName, price);

        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return null;
        }

    }
    //Поиск в базе данных товара с указанным артикулом. Если соответствующего товара в базе данных нет, метод возвращает null.

    Product createProduct(Product product){
        Connection connection;
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            Statement stmt = connection.createStatement();

            String sql = String.format("INSERT INTO products VALUES ('%s', '%s', '%d')",product.productId, product.productName, product.price);
            stmt.executeUpdate(sql);
            //ResultSet resultSet = stmt.executeQuery(sql);
            connection.close();

            return product;

        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return null;
        }
    }
    //Создание нового товара. Если в базе данных существует товар с переданным артикулом, метод выбрасывает исключение.

    Product updateProduct(Product product){
        Connection connection;
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            Statement stmt = connection.createStatement();

            String sql = String.format("UPDATE products SET product_name = '%s', price = '%d' WHERE id = '%s'", product.productName, product.price, product.productId);
            stmt.executeUpdate(sql);
            connection.close();

            return product;
        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return null;
        }
    }
    //Изменение информации о товаре. Название и цена товара в базе данных заменяется на значения, указанные в полях параметра product. Артикул товара, данные которого должны быть изменены, также задается полем объекта product.

    void deleteProduct(String productCode){
        Connection connection;
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            Statement stmt = connection.createStatement();

            String sql = String.format("DELETE FROM products WHERE product_name = '%s'", productCode);
            stmt.executeUpdate(sql);
            connection.close();
        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
        }
    }
    //Удаление товара и всех упоминаний о нем в заказах. Вас смущает необходимость изменения уже выданных заказов, но заказчик настаивает.

}


public class Main {
    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
        }
    }
}
