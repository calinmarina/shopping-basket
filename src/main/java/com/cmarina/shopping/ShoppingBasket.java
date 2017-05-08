package com.cmarina.shopping;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.cmarina.shopping.repository.InventoryRepository;

public class ShoppingBasket {
    private Map<String, Long> basketContents = new HashMap<String, Long>();

    public ShoppingBasket(List<String> list) {
        this.setBasketContents(groupByItem(list));
    }

    public ShoppingBasket(String basketContentsFile) {
        List<String> list = new ArrayList<>();
        System.out.println("Current directory: "+ System.getProperty("user.dir"));
        try(BufferedReader br = new BufferedReader(new FileReader(new File(basketContentsFile)))) {
            // read each line as item
            for(String line; (line = br.readLine()) != null; ) {
                list.add(line.toLowerCase());
            }
            this.setBasketContents(groupByItem(list));
        } catch (FileNotFoundException e) {
            System.out.println("Exception: File "+ basketContentsFile+ " not found! Basket is empty");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Exception: Some IO exception ocurred!");
            e.printStackTrace();
        }    
    }

    private Map<String, Long> groupByItem(List<String> list) {
        return list.stream().collect(
                Collectors.groupingBy(Function.identity(), Collectors.counting())
        );
    }

    public Map<String, Long> getBasketContents() {
        return basketContents;
    }

    public void setBasketContents(Map<String, Long> basketContents) {
        this.basketContents = basketContents;
    }

    @Override
    public String toString() {
        return "ShoppingBasket [basketContents=" + basketContents + "]";
    }

    public double computePrice() {
        InventoryRepository inv = InventoryRepository.getInstance();
        double total = 0;
        for (Map.Entry<String, Long> purchasedItem : this.basketContents.entrySet()) {
            Item itemDetails = inv.getItem(purchasedItem.getKey());
            if (itemDetails==null) {
                System.out.println("Item "+ purchasedItem+ " was not found in our inventory! Refer to a cashier");
                continue;
            }
            total += itemDetails.getPricePerQuantity(purchasedItem.getValue());
        }
        return total;
    }


}
