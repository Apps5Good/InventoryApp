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
    ImageView CameraPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

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
        ImageView viewCamera = (ImageView)findViewById(R.id.CameraPreview);

        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePhoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePhoto, 0);
            }
        });
    }

    public void takePicture(View v) {
        Intent toListDisplay = new Intent(this, ListDisplay.class);

        startActivity(toListDisplay);
    }

    @Override //accessing phone camera
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = (Bitmap)data.getExtras().get("data");
        CameraPreview.setImageBitmap(bitmap);
    }
}
