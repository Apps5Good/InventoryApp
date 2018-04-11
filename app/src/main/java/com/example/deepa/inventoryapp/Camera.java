package com.example.deepa.inventoryapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Camera extends AppCompatActivity {
    private ImageView preview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        preview = findViewById(R.id.photoPreview);

        //starting activity and extracting data from the intent
        Bundle addOrSubtract = getIntent().getExtras();
        Boolean status = addOrSubtract.getBoolean("status");

       /* TextView answerBox = findViewById(R.id.statusTest);
        if(status) {
            answerBox.setText(R.string.test_add);
        }
        else {
            answerBox.setText(R.string.test_remove);
        }*/
        Button cameraButton = (Button)findViewById(R.id.cameraButton);

        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPhoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intentPhoto, 0);
            }
        });
    }

    @Override //accessing phone camera
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap image = (Bitmap)data.getExtras().get("data");
        preview.setImageBitmap(image);
    }

    public void toInventory(View v) {
        Intent intentInventory = new Intent(this,InventoryDisplay.class);

        startActivity(intentInventory);
    }

    public void toHome(View v) {
        Intent intentHome = new Intent(this, MainActivity.class);

        startActivity(intentHome);
    }

    public void toList(View v) {
        Intent intentListDisplay = new Intent(this, ListPreview.class);

        startActivity(intentListDisplay);
    }
}
