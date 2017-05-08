package com.cmarina.shopping;

import java.util.Arrays;

/**
 * Exercise:
 * Using Java, write a simple program that calculates the price of a basket of shopping.
 * Items are presented one at a time, in a list, identified by name - for example "Apple" or "Banana".
 * Multiple items are present multiple times in the list,
 * so for example ["Apple", "Apple", "Banana"] is a basket with two apples and one banana.
 * 
 * Items are priced as follows:
 * - Apples are 35p each
 * - Bananas are 20p each
 * - Melons are 50p each, but are available as 'buy one get one free'
 * - Limes are 15p each, but are available in a 'three for the price of two' offer
 * 
 * Given a list of shopping, calculate the total cost of those items.
 *
 */
public class App
{
    public static void main(String[] args) {
        
        ShoppingBasket basket;    
        
        if (args.length != 1) {
            System.out.println("Using hardcoded shopping basket!");
            System.out.println("You can create text files with items list and start ShoppingApp with that file as commandline parameter");
            basket = new ShoppingBasket(
                    Arrays.asList("Apple", "Apple", "Banana", "Melon", "Melon", 
                                  "Lime", "Melon", "Lime", "Lime", "Lime"));
        } 
        else
            basket = new ShoppingBasket(args[0]);
        
        System.out.println(basket.toString());
        System.out.format("%.2f%n", basket.computePrice());
    }
}
