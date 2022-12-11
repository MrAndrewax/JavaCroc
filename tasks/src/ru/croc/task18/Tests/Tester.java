package ru.croc.task18.Tests;

import ru.croc.task18.Product;
import ru.croc.task18.DAO.ProductDAO;

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

    }
}