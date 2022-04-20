package com.ithogskolan.gustavalbrecht;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;



public class MockitoTest {


        private static final String PRODUCT_NAME = "Iphone";

        private static final int PRODUCT_NAME_COUNT = 1;

        private static final int ID_COUNT = 1;

        private static final int ID = 1;

        ShoppingBasket basket;

        ProductDAO productDAO;

        Product p;

        Product p2;

        Product p3;

        Product pNullContent;

        @BeforeEach
        void setUp() {
            basket = new ShoppingBasket();

            productDAO = mock(ProductDAO.class);

            p = new Product(1, "Iphone", 500);

            p2 = new Product(2, "Samsung", 1000);

            p3 = new Product(3, "Sony", 1500);

            pNullContent = new Product();


            when(productDAO.findByName("Iphone")).thenReturn(p);

            when(productDAO.findById(1)).thenReturn(p);

            when(productDAO.findByName("Samsung")).thenReturn(p2);

            when(productDAO.findByName("Sony")).thenReturn(p3);

            when(productDAO.findByName("")).thenReturn(pNullContent);

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


            assertEquals(7000, basket.getTotalCost());

        }

        @Test
        void TestClear(){


            basket.addByName(PRODUCT_NAME_COUNT, p.getName());

            basket.addByName(2, p2.getName());

            basket.addByName(3, p3.getName());


            basket.clear();


            assertTrue(basket.toString().contains("[]"));

        }

        @Test
        void testToString(){



            basket.addByName(PRODUCT_NAME_COUNT, p.getName());

            basket.addByName(2 , p2.getName());

            basket.addByName(3, p3.getName());


            System.out.println(basket.toString());


            assertEquals("[[1*Iphone], [2*Samsung], [3*Sony]]", basket.toString());


        }

        @Test
        void testAddByNameExceptionThrow(){

            assertThrows(IllegalArgumentException.class, () -> basket.addByName(PRODUCT_NAME_COUNT, pNullContent.getName()) );
        }

        @Test
        void testAddByIdExceptionThrow(){


            assertThrows(IllegalArgumentException.class, () -> basket.addById(PRODUCT_NAME_COUNT, pNullContent.getId()));

        }

        @Test
        void testWrongInputNegativeNumber(){

            basket.addByName(-1, p.getName());

            assertEquals("[[-1*Iphone]]", basket.toString());


        }

    @Test
    void testWrongInputLargeQuantity(){

        basket.addByName(1000000000, p.getName());

        assertEquals("[[1000000000*Iphone]]", basket.toString());



    }


}

