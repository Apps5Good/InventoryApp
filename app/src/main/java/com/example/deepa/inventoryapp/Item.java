package com.example.deepa.inventoryapp;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * This class stores information about individual items that would be stored in an inventory
 * @author javy1
 *
 */

@Entity
public class Item{
        //Dataa

        @PrimaryKey(autoGenerate = true)
        private int id;

        @ColumnInfo(name = "itemName")
        private String itemName;

        @ColumnInfo(name = "itemQuantity")
        private int itemQuantity;

        //Constructor
        /**
         * Constructs an item with given name and given quantity
         * @param name item name
         * @param quantity item quantity
         */
        public Item(String name, int quantity) {
            itemName = name;
            itemQuantity = quantity;
        }

        /**
         * Constructs an item with a name; default quantity will be set to 1
         * @param name item name
         */
        public Item(String name) {
                itemName = name;
                itemQuantity = 1;
            }

        /**
         * Constructs a default item with a null name and a quantity of 0
         */
        public Item() {
                itemName = null;
                itemQuantity = 0;
            }

        //Methods
        /**
         * Gets the item name
         * @return the item name
         */
        public String getItemName(){
            return itemName;
        }
        /**
         * Gets the current item quantity
         * @return the item quantity
         */
        public int getItemQuantity() {
            return itemQuantity;
        }

        /**
         * accesses the individual id for each item
         * @return the id
         */
        public int getId() {
            return id;
         }

        /**
         * sets the item name to the String
         * @param newName
         */
        public void setItemName(String newName) {
             itemName = newName;
         }

        /**
         * sets the item quantity to the given parameter
         * @param newQuantity
         */
        public void setItemQuantity(int newQuantity) {
            itemQuantity = newQuantity;
        }

        /**
         * sets the item id to the given parameter
         * @param id
         */
        public void setId(int id) {
            this.id = id;
        }

        /**
         * Adds to the amount of an item that already exists
         * @param amount of items to be added.
         *
         */
        public void increment(int amount) {
            itemQuantity = itemQuantity + amount;
        }

        /**
         * Reduces the amount of an item
         * @param amount of items to be deducted
         */
        public void decrement(int amount) {
            itemQuantity = itemQuantity - amount;
        }

    @Override
    public String toString() {
        return getItemName() + " " + getItemQuantity();
    }
}

