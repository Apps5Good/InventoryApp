package com.example.deepa.inventoryapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * This class stores information about individual items that would be stored in an inventory
 * @author javy1
 *
 */

public class Item implements Parcelable{
        //Data
        private String itemName;
        private int itemQuantity;
        private Parcelable.Creator CREATOR;
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
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(itemName);
        parcel.writeInt(itemQuantity);
    }

    @Override
    public String toString() {
        return getItemName() + " " + getItemQuantity();
    }
}

