package com.example.deepa.inventoryapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.os.Parcelable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class ListPreview extends AppCompatActivity {
    ArrayList<Item> listFromPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_preview);
        readPhoto();
      //  listFromPhoto = new ArrayList<>();

    }

    private void readPhoto() {
        EditText listPreview = findViewById(R.id.items);

        //read song data from file and construct song object to store in an arraylist
        InputStream is = getResources().openRawResource(R.raw.modelreceipt);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line = "";

        try {
            while ((line = reader.readLine()) != null) {
                listPreview.append(line + "\n");
            }
        } catch (IOException e) {
            Log.wtf("ItemData", "ya dun messed up");
        }
    }

    public void toInventory(View v) {
      /* EditText listPreview = findViewById(R.id.items);

        String multiLines = listPreview.getText().toString();
        String[] items;
        String delimiter = "\n";

        items = multiLines.split(delimiter);

        for(int i = 0; i < items.length - 1; i++) {
            listFromPhoto.add(new Item(items[i]));
        } */

        Intent intentInventory = new Intent(this, InventoryDisplay.class);
       // intentInventory.putParcelableArrayListExtra("itemList", listFromPhoto);
        startActivity(intentInventory);

    }

}

