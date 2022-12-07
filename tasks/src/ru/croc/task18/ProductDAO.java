package ru.croc.task18;

import java.sql.*;

public class ProductDAO{
    Product findProduct(String productCode){
        Connection connection;
        try {
            connection = DriverManager.getConnection(DataBaseInfo.DB_URL, DataBaseInfo.USER, DataBaseInfo.PASSWORD);
            Statement stmt = connection.createStatement();

            String sql  = "SELECT * FROM products WHERE product_id = " + productCode;

            ResultSet resultSet = stmt.executeQuery(sql);

            String productID = resultSet.getString("product_id");
            String productName = resultSet.getString("product_name");
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
            connection = DriverManager.getConnection(DataBaseInfo.DB_URL, DataBaseInfo.USER, DataBaseInfo.PASSWORD);
            Statement stmt = connection.createStatement();

            String sql = String.format("INSERT INTO products VALUES ('%s', '%s', '%d')",product.productId, product.productName, product.price);
            stmt.executeUpdate(sql);
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
            connection = DriverManager.getConnection(DataBaseInfo.DB_URL, DataBaseInfo.USER, DataBaseInfo.PASSWORD);
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
            connection = DriverManager.getConnection(DataBaseInfo.DB_URL, DataBaseInfo.USER, DataBaseInfo.PASSWORD);
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



