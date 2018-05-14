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

    TextView nameDisplay;
    TextView quantityDisplay;
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
                int amount = Integer.parseInt(String.valueOf(editQuantity.getText()));

                List<Item> inventory = db.userDao().getAllItems();

                for (Item myItem: inventory) {
                    if (id == myItem.getId()) {
                        if(itemname.replaceAll(" ", "").length() == 0) {
                            Toast.makeText(ItemInfo.this, "Item must have a name", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(ItemInfo.this, InventoryDisplay.class));
                        }
                        else
                            myItem.setItemName(itemname);

                        if(myItem.getItemQuantity() == 0) {
                            db.userDao().deleteItems(myItem);
                            Toast.makeText(ItemInfo.this, "Item removed because of 0 quantity", Toast.LENGTH_SHORT).show();
                        }
                        //TODO fix the crash that is caused by empty field
                        myItem.setItemQuantity(amount);
                            db.userDao().updateQuantity(myItem);
                    }
                }
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

    public void exit(View v) {
        Intent intentInventory = new Intent(this, InventoryDisplay.class);

        startActivity(intentInventory);
    }
}
