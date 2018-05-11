package com.example.deepa.inventoryapp;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class InventoryDisplay extends AppCompatActivity {

    List<Item> items;
    ListView myListView;
    String ItemName;
    int ItemQuantity;
    int ItemId;
    Item item;
    TextView statusMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_display);

        final AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database")
                .allowMainThreadQueries().build();

        statusMessage = findViewById(R.id.inventoryStatus);
        items = db.userDao().getAllItems();
        if(items.size()==0) {
            statusMessage.setText(R.string.sad_status);
        }
        else
            statusMessage.setText(null);

        final ArrayAdapter<Item> myAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);

        myListView = findViewById(R.id.myListView);
        myListView.setAdapter(myAdapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l){
                Intent intentInfo = new Intent(InventoryDisplay.this, ItemInfo.class);
                item = (Item) myListView.getItemAtPosition(i);
                ItemName = item.getItemName();
                ItemQuantity = item.getItemQuantity();
                ItemId = item.getId();

                intentInfo.putExtra("ItemName", ItemName);
                intentInfo.putExtra("ItemQuantity",ItemQuantity);
                intentInfo.putExtra("ItemId", ItemId);

                startActivity(intentInfo);

            }
        });
         myListView.setAdapter(myAdapter);
    }

    //taken from https://www.youtube.com/watch?v=jJYSm_yrT7I
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        MenuItem searchItem = menu.findItem(R.id.item_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<Item> tempList = new ArrayList<>();

                for(Item i: items) {
                    if(i.getItemName().toLowerCase().contains(newText.toLowerCase())) {
                        tempList.add(i);
                    }
                }
                ArrayAdapter<Item> myAdapter = new ArrayAdapter<>(InventoryDisplay.this,
                        android.R.layout.simple_list_item_1, tempList);
                myListView.setAdapter(myAdapter);

                return true;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);

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



