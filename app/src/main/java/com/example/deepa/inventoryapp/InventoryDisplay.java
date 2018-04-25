package com.example.deepa.inventoryapp;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

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

        ListAdapter myAdapter = new ArrayAdapter<Item>(this, android.R.layout.simple_list_item_1, items);
        //EditText inputSearch;

        ListView myListView = (ListView) findViewById(R.id.myListView);

        myListView.setAdapter(myAdapter);
       /* myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                   Intent intentItemInfo = new Intent(InventoryDisplay.this, ItemInfo.class);
                   Item selectedItem = parent<Item>[position];

                   startActivity(intentItemInfo);
            }
        });*/

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


