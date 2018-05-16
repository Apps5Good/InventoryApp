package com.example.deepa.inventoryapp;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
                if(add) {
                   addItems(db);
                }
                else
                    subtractItems(db);

                scanInventory(db);
                
                startActivity(new Intent(ListPreview.this, InventoryDisplay.class));
            }
        });
    }

    /**
     * reads the editText line by line to extract item names
     * @return an array of strings that will become item names
     */
    public String[] readEditText() {
        String multiLines = userField.getText().toString();
        String[] items;
        String delimiter = "\n";

        items = multiLines.split(delimiter);
        return items;
    }

    /**
     * extracts item names from the string array and adds them to the inventory
     * @param db instance of the AppDatabase
     */
      private void addItems(AppDatabase db) {
        boolean isThere;
          for (String listName : itemList) {
              isThere = false;
              for (Item myItem : db.userDao().getAllItems()) {
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

    /**
     * extracts item names from the string array and removes them from the inventory
     * @param db instance of the AppDatabase
     */
    private void subtractItems(AppDatabase db) {
        boolean isThere;
        for (String anItemList : itemList) {
            isThere = false;
            for (Item myItem : db.userDao().getAllItems()) {
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

    /**
     * searches inventory for any null items and deletes them before taking the user to the inventory
     * @param db instance of the AppDatabase
     */
    public void scanInventory(AppDatabase db) {
        for(Item myItem : db.userDao().getAllItems()) {
            if(myItem.getItemName().replaceAll(" ", "").length() == 0) {
                db.userDao().deleteItems(myItem);
            }
        }
    }

    /**
     * redirects the user to the MainActivity
     * @param v instance of a View object
     */
    public void toHome(View v) {
        Intent intentHome = new Intent(this, MainActivity.class);

        startActivity(intentHome);
    }
}

