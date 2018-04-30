package com.example.deepa.inventoryapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class ItemInfo extends AppCompatActivity {

    TextView edit_item;
    TextView edit_quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_info);

        edit_item = findViewById(R.id.edit_item);
        edit_quantity = findViewById(R.id.edit_quantity);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            edit_item.setText(bundle.getString("ItemName"));
            edit_quantity.setText(String.valueOf(bundle.getInt("ItemQuantity")));

        }
    }
}
