package com.example.deepa.inventoryapp;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.os.Parcelable;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class InventoryDisplay extends AppCompatActivity {

    ArrayList<Item> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_display);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database")
                .allowMainThreadQueries().build();

        List<Item> items = db.userDao().getAllItems();

        //items = new ArrayList<>();
       // readItemData();

        ListAdapter myAdapter = new ArrayAdapter<Item>(this, android.R.layout.simple_list_item_1, items);
        //EditText inputSearch;

        ListView myListView = (ListView) findViewById(R.id.myListView);
        //inputSearch = (EditText) findViewById(R.id.searchBar);
        myListView.setAdapter(myAdapter);

    }

    private void readItemData() {
        //read item data from file and populate the listview

        InputStream is = getResources().openRawResource(R.raw.modelreceipt);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line = "";

        try {
            while ((line = reader.readLine()) != null) {
                Item i = new Item(line);
                items.add(i);
            }
        } catch (IOException e) {
            Log.wtf("ItemData", "ya dun messed up");
        }

    }

    public void toHome(View v) {
        Intent intentHome = new Intent(this, MainActivity.class);

        startActivity(intentHome);
    }

    public void toCreateItem(View v) {
        Intent intentCreate = new Intent(this, CreateItem.class);

        startActivity(intentCreate);
    }
}


