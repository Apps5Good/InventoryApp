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
    ListView myListView;
    String ItemName;
    int ItemQuantity;
    Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_display);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database")
                .allowMainThreadQueries().build();

        List<Item> items = db.userDao().getAllItems();

       ArrayAdapter<Item> myAdapter = new ArrayAdapter<Item>(this, android.R.layout.simple_list_item_1, items);


        final ListView myListView = (ListView) findViewById(R.id.myListView);

        myListView.setAdapter(myAdapter);




        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l){
            Intent intent = new Intent(InventoryDisplay.this, ItemInfo.class);
            item = (Item) myListView.getItemAtPosition(i);
            ItemName = item.getItemName();
            ItemQuantity = item.getItemQuantity();

            intent.putExtra("ItemName", ItemName);
            intent.putExtra("ItemQuantity",ItemQuantity);

            startActivity(intent);

            }
        });
         myListView.setAdapter(myAdapter);
    }

    public void toHome(View v) {
        Intent intentHome = new Intent(this, MainActivity.class);

        startActivity(intentHome);
    }

    public void toCreateItem(View v) {
        Intent intentCreate = new Intent(this, CreateItem.class);

        startActivity(intentCreate);
    }

    public void toRemoveItem (View v){
        Intent intentRemove = new Intent (this, ListPreview.class);
        startActivity(intentRemove);
    }


}



