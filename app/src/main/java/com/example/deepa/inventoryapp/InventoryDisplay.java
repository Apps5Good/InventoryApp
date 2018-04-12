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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

        //below is an attempt to use Parcelabel to import an Arraylist of items from the previous activity
        //but currently it makes the app crash

       /* Bundle bundle = getIntent().getExtras();
        items  = bundle.getParcelableArrayList("itemList"); */

        items = new ArrayList<>();
        readItemData();

        ListAdapter myAdapter = new ArrayAdapter<Item>(this, android.R.layout.simple_list_item_1, items);
        //EditText inputSearch;

        ListView myListView = (ListView) findViewById(R.id.myListView);
        //inputSearch = (EditText) findViewById(R.id.searchBar);
        myListView.setAdapter(myAdapter);

    }

    private void readItemData() {
        //read item data from file and populate the listview

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

        //Hopefully this will work at some point--it's trying to take a shared file between this class and the ListPreview
        //class and read the file line by line to populate the inventory. This would allow the user to make changes to the
        //Edittext in the previous screen and have it automatically update the inventory


       /* try {
            // open the file for reading
            InputStream fis = new FileInputStream("modelreceipt.txt");
            // if file the available for reading
            if (fis != null) {

                // prepare the file for reading
                InputStreamReader chapterReader = new InputStreamReader(fis);
                BufferedReader buffreader = new BufferedReader(chapterReader);

                String line;

                // read every line of the file into the line-variable, one line at the time
                do {
                    line = buffreader.readLine();
                    // do something with the line
                    items.add(new Item(line));
                } while (line != null);

            }

            fis.close();
        } catch (Exception e) {
            // print stack trace.
        }*/
    }
}


