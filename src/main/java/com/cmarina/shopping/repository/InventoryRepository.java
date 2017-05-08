package com.cmarina.shopping.repository;

import java.util.ArrayList;
import java.util.List;

import com.cmarina.shopping.Item;
import com.cmarina.shopping.Item.Deals;

/**
 * This would normally map to a database table with all possible items for sale
 */
public class InventoryRepository {

    private static InventoryRepository instance = null;
    List<Item> inv;
    
    protected InventoryRepository() {
        this.inv = new ArrayList<Item>();
        inv.add(new Item("apple", 0.35));
        inv.add(new Item("banana", 0.20));
        inv.add(new Item("melon", 0.50, Deals.BUY_ONE_GET_ONE_FREE));
        inv.add(new Item("lime", 0.15, Deals.THREE_FOR_TWO));
    }
    
    public static InventoryRepository getInstance() {
        if (instance == null)
            instance = new InventoryRepository();
        return instance;
    }

    public Item getItem(String item) {
        for (Item i : inv) {
            if (i.getName().equalsIgnoreCase(item))
                return i;
        }
        //item not found in inventory
        return null;
    }

}
