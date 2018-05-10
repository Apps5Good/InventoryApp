package com.example.deepa.inventoryapp;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class CreateItem extends AppCompatActivity {


    EditText name;
    EditText quantity;
    Button save;
    private static final String TAG = "CreateItem";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_item);
        name = findViewById(R.id.itemName);
        quantity = findViewById(R.id.itemQuantity);
        save = findViewById(R.id.saveItem);

        final AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database")
                .allowMainThreadQueries().build();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemname = String.valueOf(name.getText());
                String amount = String.valueOf(quantity.getText());

                if(!isEmpty(name) && !isEmpty(quantity) && (!amount.equals("0"))) {

                    boolean isThere = false;
                    List<Item> inventory = db.userDao().getAllItems();

                    for (Item myItem : inventory) {
                        if (itemname.equals(myItem.getItemName())) {
                            isThere = true;
                            Toast.makeText(CreateItem.this, "Updated existing item" + itemname + "from" +
                                    "quantity" + myItem.getItemQuantity() + "to" + amount, Toast.LENGTH_SHORT).show();
                            myItem.increment(Integer.parseInt(amount));
                            db.userDao().updateQuantity(myItem);
                        }
                    }
                    if (!isThere) {
                        db.userDao().insertAll((new Item(itemname, Integer.parseInt(amount))));
                    }
                    startActivity(new Intent(CreateItem.this, InventoryDisplay.class));
                }
                else {
                    if(amount.equals("0")) {
                        Toast.makeText(CreateItem.this, "Cannot add an item with a quantity of 0", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(CreateItem.this, "Item cannot be created with null fields", Toast.LENGTH_SHORT).show();
                    }
                      toInventory(v);
                }
                }
        });
    }

    public void toInventory(View v) {
        Intent intentInventory = new Intent(this, InventoryDisplay.class);

        startActivity(intentInventory);
    }

    private boolean isEmpty(EditText e) {
        return e.getText().toString().trim().length() == 0;
    }
}
