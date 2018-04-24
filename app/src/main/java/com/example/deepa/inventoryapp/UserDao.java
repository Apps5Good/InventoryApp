package com.example.deepa.inventoryapp;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

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
}
