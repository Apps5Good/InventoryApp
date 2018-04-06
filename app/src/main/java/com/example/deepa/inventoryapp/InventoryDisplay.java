package com.example.deepa.inventoryapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

public class InventoryDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_display);

        String[] foods = {"gefilte fish", "jelly", "glabgogabgalab", "brownies", "kugel"};
        ListAdapter myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, foods);
        EditText inputSearch;

        ListView myListView = (ListView) findViewById(R.id.myListView);
        //inputSearch = (EditText) findViewById(R.id.searchBar);
        myListView.setAdapter(myAdapter);


    }
}


