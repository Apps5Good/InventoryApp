package com.example.deepa.inventoryapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * sets a boolean to true that will be used later on to add items
     * redirects user to the Camera Activity
     * @param v instance of a View object
     */
     public void addItems(View v) {
        Boolean status = true; //if true, it will add; if false, it will subtract
        Intent intentAdd = new Intent (this, Camera.class);
        intentAdd.putExtra("status", status);

        startActivity(intentAdd);
    }

    /**
     * sets a boolean to false that will be used later on to remove items
     * redirects user to the Camera Activity
     * @param v instance of a View object
     */
    public void subtractItems(View v) {
        Boolean status = false;
        Intent intentSubtract = new Intent(this, Camera.class);
        intentSubtract.putExtra("status", status);

        startActivity(intentSubtract);
    }

    /**
     * redirects user to the InventoryDisplay Activity
     * @param v instance of a View object
     */
    public void toInventory(View v) {
        Intent intentInventory = new Intent(this,InventoryDisplay.class);

        startActivity(intentInventory);
    }
}
