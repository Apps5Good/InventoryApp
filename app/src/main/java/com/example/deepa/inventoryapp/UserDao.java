package com.example.deepa.inventoryapp;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * This is the Data Access Object which is what can access tables in the SQLite database and return table information
 * Created by deepa on 4/23/2018.
 */

@Dao
public interface UserDao {
    //returns the entire list of items
    @Query("SELECT * FROM item")
    List<Item> getAllItems();

    //creates a new item
    @Insert
    void insertAll(Item...items);

    //deletes an item
    @Delete
    void deleteItems(Item... items);

    //deletes the entire table
    @Query("DELETE FROM item")
    void nukeTable();

    //returns the itemQuantity by matching an inputted item name
    @Query("SELECT itemQuantity FROM item WHERE itemName = :name")
    int getAmount(String name);

    //updates the item information to new values that were set by the user
    @Update
    void updateQuantity(Item i);
}
