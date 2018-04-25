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

     public void addItems(View v) {
        Boolean status = true; //if true, it will add; if false, it will subtract
        Intent intentAdd = new Intent (this, Camera.class);
        intentAdd.putExtra("status", status);

        startActivity(intentAdd);
    }

    public void subtractItems(View v) {
        Boolean status = false;
        Intent intentSubtract = new Intent(this, Camera.class);
        intentSubtract.putExtra("status", status);

        startActivity(intentSubtract);
    }

    public void toInventory(View v) {
        Intent intentInventory = new Intent(this,InventoryDisplay.class);

        startActivity(intentInventory);
    }
}
