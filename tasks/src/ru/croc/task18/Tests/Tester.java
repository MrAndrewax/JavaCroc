package ru.croc.task18.Tests;

import ru.croc.task18.DAO.OrderDAO;
import ru.croc.task18.Product;
import ru.croc.task18.DAO.ProductDAO;

import java.util.ArrayList;
import java.util.List;

public class Tester{
    public void testFindProduct1(){
        System.out.println("TEST1");
        ProductDAO productDAO = new ProductDAO();
        Product product = productDAO.findProduct("Т1");
        System.out.println(product);
    }
    public void testFindProduct2(){
        System.out.println("TEST2");
        ProductDAO productDAO = new ProductDAO();
        Product product = productDAO.findProduct("WrongProductID");
        System.out.println(product);
    }
    public void testCreateProduct1(){
        System.out.println("TEST3");
        Product product = new Product("1", "product_name", 5);
        ProductDAO productDAO = new ProductDAO();
        Product product1 = productDAO.createProduct(product);
        System.out.println(product1);
    }
    public void testCreateProduct2(){
        System.out.println("TEST4");
        Product product = new Product("1", "product_name1", 55);
        ProductDAO productDAO = new ProductDAO();
        Product product1 = productDAO.createProduct(product);
        System.out.println(product1);
    }
    public void testUpdateProduct1(){
        System.out.println("TEST5");
        Product product = new Product("1", "new_product_name", 550);
        ProductDAO productDAO = new ProductDAO();
        Product product1 = productDAO.updateProduct(product);
        System.out.println(product1);
    }
    public void testUpdateProduct2(){
        System.out.println("TEST6");
        Product product = new Product("2", "new_product_name", 550);
        ProductDAO productDAO = new ProductDAO();
        Product product1 = productDAO.updateProduct(product);
        System.out.println(product1);
    }
    public void testDeleteProduct1(){
        ProductDAO productDAO = new ProductDAO();
        productDAO.deleteProduct("Т1");
        productDAO.deleteProduct("Т2");
    }
    public void testCreateOrder1(){
        String userLogin = "coolUserName";
        Product product1 = new Product("A1", "new_product_name1", 1);
        Product product2 = new Product("A2", "new_product_name2", 23);
        Product product3 = new Product("A3", "new_product_name3", 456);
        Product product4 = new Product("A4", "new_product_name4", 78);
        Product product5 = new Product("A5", "new_product_name5", 9);

        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);
        products.add(product5);

        OrderDAO orderDAO = new OrderDAO();
        orderDAO.createOrder(userLogin, products);

    }
}