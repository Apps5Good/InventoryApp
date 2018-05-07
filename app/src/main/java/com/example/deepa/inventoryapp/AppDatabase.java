package com.example.deepa.inventoryapp;

import android.arch.persistence.room.Database;
        import android.arch.persistence.room.RoomDatabase;

/**
 * Created by deepa on 4/23/2018.
 */

@Database(entities = {Item.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
