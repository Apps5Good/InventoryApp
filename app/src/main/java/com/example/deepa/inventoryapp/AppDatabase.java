package com.example.deepa.inventoryapp;

import android.arch.persistence.room.Database;
        import android.arch.persistence.room.RoomDatabase;

/**
 * Created by deepa on 4/23/2018. The entire app database as well as the DAO was created
 * with the help of the youtube tutorial at this link: https://www.youtube.com/watch?v=CTBiwKlO5IU
 */

@Database(entities = {Item.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
