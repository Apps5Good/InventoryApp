package com.example.deepa.inventoryapp;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by deepa on 4/23/2018.
 */

@Dao
public interface UserDao {
    @Query("SELECT * FROM item")
    List<Item> getAllItems();

    @Insert
    void insertAll(Item...items);

    @Delete
    void deleteItems(Item... items);

    @Query("DELETE FROM item")
    void nukeTable();

    @Query("SELECT itemQuantity FROM item WHERE itemName = :name")
    int getAmount(String name);

    @Update
    void updateQuantity(Item i);
}
