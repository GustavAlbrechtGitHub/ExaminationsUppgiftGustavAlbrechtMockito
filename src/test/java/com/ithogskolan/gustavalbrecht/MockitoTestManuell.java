package com.ithogskolan.gustavalbrecht;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class MockitoTestManuell {

    private static final String PRODUCT_NAME = "Iphone";

    private static final int PRODUCT_NAME_COUNT = 1;

    private static final int ID_COUNT = 1;

    private static final int ID = 1;

    ShoppingBasket basket;

    ProductDAO productDAO;

    Product p;

    Product p2;

    Product p3;



    @BeforeEach
    void setUp() {
        basket = new ShoppingBasket();

       productDAO = new MockProductDAO();

        p = productDAO.findById(1);

        p = productDAO.findByName("Iphone");

        p2 = productDAO.findByName("Samsung");

        p3 = productDAO.findByName("Sony");


        basket.setProductDAO(productDAO);


    }

    @Test
    void testAddByName() {


        basket.addByName(PRODUCT_NAME_COUNT, p.getName());


        assertEquals("[[1*Iphone]]", basket.toString());


    }

    @Test
    void testAddById(){


        basket.addById(PRODUCT_NAME_COUNT, p.getId());

        assertEquals("[[1*Iphone]]", basket.toString());

    }

    @Test
    void testGetTotalCost(){



        basket.addByName(PRODUCT_NAME_COUNT, p.getName());

        basket.addByName(2, p2.getName());

        basket.addByName(3, p3.getName());

        System.out.println(basket.toString());


        assertEquals(7000, basket.getTotalCost());

    }

    @Test
    void TestClearBasket(){

        basket.addByName(PRODUCT_NAME_COUNT, p.getName());

        basket.addByName(2, p2.getName());

        basket.addByName(3, p3.getName());


        basket.clear();


        assertTrue(basket.toString().contains("[]"));

    }

    @Test
    void testToString(){


        basket.addByName(PRODUCT_NAME_COUNT, p.getName());

        basket.addByName(2, p2.getName());

        basket.addByName(3, p3.getName());

        System.out.println(basket.toString());


        assertEquals("[[1*Iphone], [2*Samsung], [3*Sony]]", basket.toString());


    }

    @Test
    void testAddByNameExceptionThrow(){



        assertThrows(IllegalArgumentException.class, () -> basket.addByName(PRODUCT_NAME_COUNT, "") );
    }

    @Test
    void testAddByIdExceptionThrow(){

        assertThrows(IllegalArgumentException.class, () -> basket.addById(PRODUCT_NAME_COUNT, 0));

    }

}
class MockProductDAO implements ProductDAO{

    Product p = new Product (1, "Iphone", 500);

    Product p2 = new Product(2, "Samsung", 1000);

    Product p3 = new Product(3, "Sony", 1500);




    @Override
    public Product findById(int id) {

        if (p.getId() == id){
            return p;
        }
        else if (p2.getId() == id){
            return p2;
        }
        else if (p3.getId() == id){
            return p3;
        }
        else return null;
    }

    @Override
    public Product findByName(String name) {


        if (name.equals(p.getName())){
            return p;
        }
        else if (name.equals(p2.getName())){
            return p2;
        }
        else if (name.equals(p3.getName())){
            return p3;
        }
        else return null;
    }

    @Override
    public List<Product> findAll() {
        return null;
    }

    @Override
    public List<Product> findCheaperThan(int lowprice) {
        return null;
    }

    @Override
    public boolean isInStock(int id) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public void raiseAllPrices(double percent) {

    }

}