package com.example.deepa.inventoryapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.os.Parcelable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class InventoryDisplay extends AppCompatActivity {

    ArrayList<Item> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_display);

       // Bundle bundle = getIntent().getExtras();
       // items  = bundle.getParcelableArrayList("itemList");
        items = new ArrayList<>();
        readItemData();

        ListAdapter myAdapter = new ArrayAdapter<Item>(this, android.R.layout.simple_list_item_1, items);
        //EditText inputSearch;

        ListView myListView = (ListView) findViewById(R.id.myListView);
        //inputSearch = (EditText) findViewById(R.id.searchBar);
        myListView.setAdapter(myAdapter);

    }

    private void readItemData() {
        //read song data from file and construct song object to store in an arraylist
        InputStream is = getResources().openRawResource(R.raw.modelreceipt);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line = "";

        try {
            while ((line = reader.readLine()) != null) {
               Item i = new Item(line);
               items.add(i);
            }
        }
        catch(IOException e) {
            Log.wtf("ItemData", "ya dun messed up");
        }
    }
}


