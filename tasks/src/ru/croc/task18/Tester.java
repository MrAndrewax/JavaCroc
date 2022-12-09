package ru.croc.task18;

public class Tester{
    void testFindProduct1(){
        System.out.println("TEST1");
        ProductDAO productDAO = new ProductDAO();
        Product product = productDAO.findProduct("Т1");
        System.out.println(product);
    }
    void testFindProduct2(){
        System.out.println("TEST2");
        ProductDAO productDAO = new ProductDAO();
        Product product = productDAO.findProduct("WrongProductID");
        System.out.println(product);
    }
    void testCreateProduct1(){
        System.out.println("TEST3");
        Product product = new Product("1", "product_name", 5);
        ProductDAO productDAO = new ProductDAO();
        Product product1 = productDAO.createProduct(product);
        System.out.println(product1);
    }
    void testCreateProduct2(){
        System.out.println("TEST4");
        Product product = new Product("1", "product_name1", 55);
        ProductDAO productDAO = new ProductDAO();
        Product product1 = productDAO.createProduct(product);
        System.out.println(product1);
    }
    void testUpdateProduct1(){
        System.out.println("TEST5");
        Product product = new Product("1", "new_product_name", 550);
        ProductDAO productDAO = new ProductDAO();
        Product product1 = productDAO.updateProduct(product);
        System.out.println(product1);
    }
    void testUpdateProduct2(){
        System.out.println("TEST6");
        Product product = new Product("2", "new_product_name", 550);
        ProductDAO productDAO = new ProductDAO();
        Product product1 = productDAO.updateProduct(product);
        System.out.println(product1);
    }
    void testDeleteProduct1(){}
    void testDeleteProduct2(){}
    void testCreateOrder1(){}
    void testCreateOrder2(){}
}