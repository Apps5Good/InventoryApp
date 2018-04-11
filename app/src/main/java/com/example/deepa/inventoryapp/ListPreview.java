package com.example.deepa.inventoryapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ListPreview extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_preview);
        readPhoto();
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

}

