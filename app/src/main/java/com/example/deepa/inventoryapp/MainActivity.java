package com.example.deepa.inventoryapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

  /* public void readReceipt() {
        InputStream is = getResources().openRawResource(R.raw.modelreceipt);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line = "";
        ArrayList<Item> newReceipt = new ArrayList<>();

        try {
            while ((line = reader.readLine()) != null) {
                newReceipt.add(new Item(line));
            }
        }
        catch(IOException e) {
            Log.wtf("ImageData", "ya dun messed up");
        }
    }*/

    public void addItems(View v) {
        Boolean status = true; //if true, it will add; if false, it will subtract
        Intent addItems = new Intent (this, Camera.class);
        addItems.putExtra("status", status);

        startActivity(addItems);
    }

    public void subtractItems(View v) {
        Boolean status = false;
        Intent subtractItems = new Intent(this, Camera.class);
      subtractItems.putExtra("status", status);

        startActivity(subtractItems);
    }

    public void toInventory(View v) {
        Intent accessInventory = new Intent(this,InventoryDisplay.class);

        startActivity(accessInventory);
    }
}
