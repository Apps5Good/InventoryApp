package com.example.deepa.inventoryapp;

/*
 * stores items in an inventory and accesses information about the inventory
 * @author sreshtaa
 *
 */

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Inventory {

    //data
    private ArrayList<Item> userItems;

    //constructor
    public Inventory() {
        userItems = new ArrayList<Item>();
    }

    //methods
    /**
     * scans the inventory for an item with the same name as the inputed item and increments the quantity
     * if it is an item that doesn't already exist, the method adds it to the end of the ArrayList
     * @param i the item being added
     */
    public void addItem(Item i) {
        boolean exists = false;
        for(Item myItem : userItems) {
            if(i.getItemName().equals(myItem.getItemName())) {
                myItem.increment(i.getItemQuantity());
                exists = true;
            }
        }
        if(!exists)
            userItems.add(i);
    }

    /**
     * scans the inventory for an item with the same name as the inputed item and decrements the quantity
     * if the quantity is 1 (there is only one of the item), the item is deleted from the ArrayList
     * @param i the item being removed
     */
    public void subtractItem(Item i) {
        for(int j = userItems.size()-1; j >= 0; j--) {
            if(i.getItemName().equals(userItems.get(j).getItemName())) {
                if(userItems.get(j).getItemQuantity() > i.getItemQuantity())
                    userItems.get(j).decrement(i.getItemQuantity());
                else
                    userItems.remove(j);
            }
        }
    }

    /**
     * removes an entire item object from the inventory, regardless the item's quantity
     * @param i the item being deleted
     */
    public void deleteItem(Item i) {
        for(int j = userItems.size()-1; j >= 0; j--) {
            if(i.getItemName().equals(userItems.get(j).getItemName())) {
                userItems.remove(j);
            }
        }
    }

    }

