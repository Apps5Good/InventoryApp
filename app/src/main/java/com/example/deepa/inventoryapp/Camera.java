package com.example.deepa.inventoryapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Camera extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        //starting activity and extracting data from the intent
        Bundle addOrSubtract = getIntent().getExtras();
        Boolean status = addOrSubtract.getBoolean("status");

        TextView answerBox = findViewById(R.id.statusTest);
        if(status) {
            answerBox.setText(R.string.test_add);
        }
        else {
            answerBox.setText(R.string.test_remove);
        }
    }
}
