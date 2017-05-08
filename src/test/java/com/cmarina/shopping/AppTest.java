package com.cmarina.shopping;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
/**
 * @author calin
 *
 */
/**
 * @author calin
 *
 */
public class AppTest {
    private ByteArrayOutputStream out = new ByteArrayOutputStream();
    private PrintStream oldOut = System.out;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * There is a hardcoded shopping list in case specific shopping list file
     * was not given
     */
    @Test
    public final void testWithoutCommandLineParameter() {
        String[] args = {};
        System.setOut(new PrintStream(out));
        App.main(args);
        System.setOut(oldOut);
        System.out.print(out);
        assertTrue(out.toString().contains("Lime=4, Apple=2, Melon=3, Banana=1"));
        assertTrue(out.toString().contains("2.35"));
    }

    /**
     * Loading shopping list from a text file indicated via command line
     * when file is missing
     */
    @Test
    public final void testWithCommandLineParamAndMissingFile() {
        String[] args = { "someFile.txt" };

        System.setOut(new PrintStream(out));
        App.main(args);
        System.setOut(oldOut);
        System.out.print(out);
        
        assertTrue(new String(out.toByteArray()).contains("File someFile.txt not found"));
    }

    /**
     * Loading shopping list from a text file indicated via command line
     */
    @Test
    public final void testWithCommandLineParam() {
        String[] args = { "src/main/resources/shoppingList.txt" };
        
        System.setOut(new PrintStream(out));
        App.main(args);
        System.setOut(oldOut);
        System.out.print(out);

        assertTrue(new String(out.toByteArray()).contains("0.90"));
    }

    /**
     * testing buy "one get one free" offer
     */
    @Test
    public final void testBuyOneGetOneFree() {
        ShoppingBasket basket = new ShoppingBasket(Arrays.asList("melon"));
        double price = basket.computePrice();
        System.out.format("%s -> Price: %.2f%n", basket.toString(), price);
        assertEquals(0.5d, price, 0.01);

        basket = new ShoppingBasket(Arrays.asList("melon", "melon"));
        price = basket.computePrice();
        System.out.format("%s -> Price: %.2f%n", basket.toString(), price);
        assertEquals(0.5d, price, 0.01);

        basket = new ShoppingBasket(Arrays.asList("melon", "melon", "melon"));
        price = basket.computePrice();
        System.out.format("%s -> Price: %.2f%n", basket.toString(), price);
        assertEquals(1.0d, price, 0.01);
    }

    
    /**
     * testing 3for2 offer
     */
    @Test
    public final void testThreeForTwo() {
        ShoppingBasket basket = new ShoppingBasket(Arrays.asList("lime"));
        double price = basket.computePrice();
        System.out.format("%s -> Price: %.2f%n", basket.toString(), price);
        assertEquals(0.15d, price, 0.01);

        basket = new ShoppingBasket(Arrays.asList("lime", "lime"));
        price = basket.computePrice();
        System.out.format("%s -> Price: %.2f%n", basket.toString(), price);
        assertEquals(0.30d, price, 0.01);

        basket = new ShoppingBasket(Arrays.asList("lime", "lime", "lime"));
        price = basket.computePrice();
        System.out.format("%s -> Price: %.2f%n", basket.toString(), price);
        assertEquals(0.30d, price, 0.01);

        basket = new ShoppingBasket(Arrays.asList("lime", "lime", "lime", "lime"));
        price = basket.computePrice();
        System.out.format("%s -> Price: %.2f%n", basket.toString(), price);
        assertEquals(0.45d, price, 0.01);

        basket = new ShoppingBasket(Arrays.asList("lime", "lime", "lime", "lime", "lime"));
        price = basket.computePrice();
        System.out.format("%s -> Price: %.2f%n", basket.toString(), price);
        assertEquals(0.60d, price, 0.01);

        basket = new ShoppingBasket(Arrays.asList("lime", "lime", "lime", "lime", "lime", "lime"));
        price = basket.computePrice();
        System.out.format("%s -> Price: %.2f%n", basket.toString(), price);
        assertEquals(0.60d, price, 0.01);

    }

    
    /**
     * non existing items will be ignored
     */
    @Test
    public final void testOmitItemsMissingFromInventory() {
        ShoppingBasket basket = new ShoppingBasket(Arrays.asList("melon", "melon", "banana", "noexisting"));
        double price = basket.computePrice();
        System.out.format("%s -> Price: %.2f%n", basket.toString(), price);
        assertEquals(0.70d, price, 0.01);

        basket = new ShoppingBasket(Arrays.asList("nonexisting"));
        price = basket.computePrice();
        System.out.format("%s -> Price: %.2f%n", basket.toString(), price);
        assertEquals(0d, price, 0.01);

        basket = new ShoppingBasket(Arrays.asList("noItem1", "noItem2", "noItem3"));
        price = basket.computePrice();
        System.out.format("%s -> Price: %.2f%n", basket.toString(), price);
        assertEquals(0d, price, 0.01);
    }

}
