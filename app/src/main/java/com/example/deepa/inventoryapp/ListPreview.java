package com.example.deepa.inventoryapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.os.Parcelable;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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

        //read item data from file
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

    private void writeToStorage() {
        try {
            FileOutputStream storage = openFileOutput("modelreceipt.txt", MODE_WORLD_READABLE);

            EditText listPreview = findViewById(R.id.items);

            String itemString = listPreview.getText().toString();
            String[] items;
            String delimiter = "\n";
            items = itemString.split(delimiter);

            for(int i = 0; i < items.length - 1; i++) {
                storage.write(items[i].getBytes());
                storage.close();
            }

        } catch (FileNotFoundException e) {
            Log.wtf("File", "File not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void toInventory(View v) {
        //This was an attempt to export an arraylist with items found in the editText view, but this currently
        //makes the app crash

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

