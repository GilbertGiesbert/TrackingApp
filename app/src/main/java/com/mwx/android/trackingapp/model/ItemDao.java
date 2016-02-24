package com.mwx.android.trackingapp.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by joern on 24.02.2016.
 */
public class ItemDao { // fake dao

    private HashMap<Long, Item> items;

    public ItemDao() {

        String[] itemNames = {"car", "doll", "crane", "PC"};
        double[] itemPrices = {2000, 35, 856000, 550};

        items = new HashMap<>();
        for(int i = 0; i < itemNames.length; i++){
            Item item = new Item();
            item.setId(100+i);
            item.setName(itemNames[i]);
            item.setPrice(itemPrices[i]);
            items.put(item.getId(), item);
        }
    }

    public ArrayList<Item> getAllItems(){
        return new ArrayList<Item>(items.values());
    }

    public Item getItem(long id){
        return items.get(id);
    }
}