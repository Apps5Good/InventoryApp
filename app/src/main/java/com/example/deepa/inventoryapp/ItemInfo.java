package com.example.deepa.inventoryapp;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ItemInfo extends AppCompatActivity {

    EditText editName;
    EditText editQuantity;
    Button saveAndExit;
    Button delete;
    int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_info);

        editName = findViewById(R.id.editName);
        editQuantity = findViewById(R.id.editQuantity);
        saveAndExit = findViewById(R.id.saveButton);
        delete = findViewById(R.id.deleteButton);

        //this bundle contains the specific item id, which is important when updating item information
        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
            editName.setText(bundle.getString("ItemName"));
            editQuantity.setText(String.valueOf(bundle.getInt("ItemQuantity")));
            id = bundle.getInt("ItemId");
        }

        final AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database")
                .allowMainThreadQueries().build();

        saveAndExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemname = String.valueOf(editName.getText());
                String amt = String.valueOf(editQuantity.getText());
                performChecks(db, itemname, amt);

                exit(v);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               List<Item> inventory = db.userDao().getAllItems();

                for (Item myItem: inventory) {
                    if (id == myItem.getId()) {
                        Toast.makeText(ItemInfo.this, "Item " + myItem.getItemName() + " removed from inventory", Toast.LENGTH_SHORT).show();
                        db.userDao().deleteItems(myItem);
                    }
                }
                exit(v);
            }
        });

    }

    /**
     * Checks for null entries in the text boxes and toasts to the user if something is wrong
     * @param db instance of an AppDatabase
     * @param itemname name of the item in the editText field
     * @param amount quantity of the item in the editText field
     */
    public void performChecks(AppDatabase db, String itemname, String amount) {
        List<Item> inventory = db.userDao().getAllItems();

        //initial quantity check
        if(spaceReplaceInfo(amount) == 0) {
            Toast.makeText(ItemInfo.this, "Item must have a quantity", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(ItemInfo.this, InventoryDisplay.class));
        }

        //name check
        for (Item myItem: inventory) {
            if (id == myItem.getId()) {
                if(spaceReplaceInfo(itemname) == 0) {
                    Toast.makeText(ItemInfo.this, "Item must have a name", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ItemInfo.this, InventoryDisplay.class));
                }
                else
                    myItem.setItemName(itemname);

                //quantity check number 2 (done here because it's done on every item in the list)
                if(myItem.getItemQuantity() == 0) {
                    db.userDao().deleteItems(myItem);
                    Toast.makeText(ItemInfo.this, "Item removed because of 0 quantity", Toast.LENGTH_SHORT).show();
                }
                myItem.setItemQuantity(Integer.parseInt(amount));
                db.userDao().updateQuantity(myItem);
            }
        }
    }

    /**
     * deletes all the white space in a string and returns the length
     * @param s string object
     * @return the string length
     */
    private int spaceReplaceInfo(String s) {
        return s.replaceAll(" ", "").length();
    }

    /**
     * takes the user to the inventory display page
     * @param v instance of a view object
     */
    public void exit(View v) {
        Intent intentInventory = new Intent(this, InventoryDisplay.class);

        startActivity(intentInventory);
    }
}
