package com.example.deepa.inventoryapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.Text;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

public class Camera extends AppCompatActivity {
    private ImageView preview;
    public Boolean addorsub;
    Button scan;
    String receipt;
    Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        preview = findViewById(R.id.photoPreview);
        scan = findViewById(R.id.toDisplay);
        Button cameraButton = (Button)findViewById(R.id.cameraButton);

        //this boolean represents whether we are adding items or removing items from inventory
        Bundle addOrSubtract = getIntent().getExtras();
        addorsub = addOrSubtract.getBoolean("status");

        //redirecting the user to the camera as soon as the activity starts to avoid accidentally
        //submitting the list with no image scanned
        Intent intentPhoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intentPhoto, 0);


        //TODO 5/1/2018 add a way for the user to not HAVE to take an image if they don't want
       /* cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPhoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intentPhoto, 0);
            }
        });*/

        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextRecognizer txtRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();
                Frame frame = new Frame.Builder().setBitmap(bitmap).build();
                SparseArray items = txtRecognizer.detect(frame);
                StringBuilder strBuilder = new StringBuilder();
                for (int i = 0; i < items.size(); i++) {
                    TextBlock item = (TextBlock) items.valueAt(i);
                    strBuilder.append(item.getValue());
                    strBuilder.append("/");
                    for (Text line : item.getComponents()) {
                        //extract scanned text lines here
                        Log.v("lines", line.getValue());
                        for (Text element : line.getComponents()) {
                            //extract scanned text words here
                            Log.v("element", element.getValue());

                        }
                    }
                }
                receipt= strBuilder.toString();
                toList(v);
            }
        });
    }


    @Override //accessing phone camera
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Bitmap image = (Bitmap)data.getExtras().get("data");

        //uncomment this line if you want to set the image to the default image in the res folder
       Bitmap image =  BitmapFactory.decodeResource(getApplicationContext().getResources(),
                R.drawable.test_receipt);

        bitmap = image;
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
        intentListDisplay.putExtra("status", addorsub);
        intentListDisplay.putExtra("receipt", receipt);
        startActivity(intentListDisplay);

    }
}
