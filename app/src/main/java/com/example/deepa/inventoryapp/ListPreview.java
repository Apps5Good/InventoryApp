package com.example.deepa.inventoryapp;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import java.util.List;

public class ListPreview extends AppCompatActivity {
    Button save;
    String[] itemList;
    Boolean add;
    String receipt;
    EditText userField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_preview);

        Bundle bundle = getIntent().getExtras();
        add = bundle.getBoolean("status");
        receipt = bundle.getString("receipt");
        userField = findViewById(R.id.items);
        userField.setText(receipt);

        save = findViewById(R.id.saveButton);

        final AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database")
                .allowMainThreadQueries().build();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemList = readEditText();
                List<Item> inventory = db.userDao().getAllItems();
                if(add) {
                   addItems(inventory, db);
                }
                else
                    subtractItems(inventory, db);

                scanInventory(db);
                
                startActivity(new Intent(ListPreview.this, InventoryDisplay.class));
            }
        });
    }

    private void readPhoto() {
        EditText listPreview = findViewById(R.id.items);

        //read item data from file
        InputStream is = getResources().openRawResource(R.raw.modelreceipt);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line;

        try {
            while ((line = reader.readLine()) != null) {
                listPreview.append(line + "\n");
            }
        } catch (IOException e) {
            Log.wtf("ItemData", "ya dun messed up");
        }
    }


    public String[] readEditText() {
        //This was an attempt to export an arraylist with items found in the editText view, but this currently
        //makes the app crash

        String multiLines = userField.getText().toString();
        String[] items;
        String delimiter = "\n";

        items = multiLines.split(delimiter);
        return items;
    }

      public void addItems(List<Item> inventory, AppDatabase db) {
        boolean isThere;
          for (String listName : itemList) {
              isThere = false;
              for (Item myItem : inventory) {
                  if (listName.toLowerCase().equals(myItem.getItemName().toLowerCase())) {
                      isThere = true;
                      myItem.increment(1);
                      db.userDao().updateQuantity(myItem);
                  }
              }
              if (!isThere) {
                  db.userDao().insertAll((new Item(listName)));
              }
          }
    }

    public void subtractItems(List<Item> inventory, AppDatabase db) {
        boolean isThere;
        for (String anItemList : itemList) {
            isThere = false;
            for (Item myItem : inventory) {
                if (anItemList.toLowerCase().equals(myItem.getItemName().toLowerCase())) {
                    isThere = true;
                    if (myItem.getItemQuantity() > 1) {
                        myItem.decrement(1);
                        db.userDao().updateQuantity(myItem);
                    } else {
                        db.userDao().deleteItems(myItem);
                    }
                }
            }
            if (!isThere) {

            }
        }
    }

    public void scanInventory(AppDatabase db) {
        for(Item myItem : db.userDao().getAllItems()) {
            if(myItem.getItemName().replaceAll(" ", "").length() == 0) {
                db.userDao().deleteItems(myItem);
            }
        }
    }
}

