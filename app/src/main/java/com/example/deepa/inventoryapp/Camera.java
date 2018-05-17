package com.example.deepa.inventoryapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.Text;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

import java.io.FileNotFoundException;

//This class accesses the photo gallery and uploads an image to the image reader, which reads the text and saves it as a string
public class Camera extends AppCompatActivity {
    private ImageView preview;
    public Boolean addorsub;
    Button scan;
    Button cameraButton;
    String receipt;
    Bitmap bitmap;
    Button rotate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        preview = findViewById(R.id.photoPreview);
        scan = findViewById(R.id.toDisplay);
        scan.setEnabled(false); //disables button until an image is uploaded
        cameraButton = findViewById(R.id.cameraButton);
        rotate = findViewById(R.id.rotateImage);
        rotate.setEnabled(false); //disables button until an image is uploaded

        //this boolean represents whether we are adding items or removing items from inventory
        Bundle addOrSubtract = getIntent().getExtras();
        addorsub = addOrSubtract.getBoolean("status");

        //code taken from https://stackoverflow.com/questions/38352148/get-image-from-the-gallery-and-show-in-imageview
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 0);
            }
        });

       //taken from https://github.com/androidmads/AndroidOCRSample
        //this is the image reading functionality of the entire application
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextRecognizer txtRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();
                Frame frame = new Frame.Builder().setBitmap(bitmap).build();
                SparseArray items = txtRecognizer.detect(frame);
                StringBuilder strBuilder = new StringBuilder();
                for (int i = 0; i < items.size(); i++) {
                    TextBlock item = (TextBlock) items.valueAt(i);
                    if(item.getValue().replaceAll(" ", "").length() != 0) {
                        strBuilder.append(item.getValue());
                        strBuilder.append("\n");
                    }
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


        //allows the user to rotate the image if it is not oriented correctly
        rotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rotatePhoto();
            }
        });

    }

    //code taken from https://stackoverflow.com/questions/38352148/get-image-from-the-gallery-and-show-in-imageview

    /**
     * sets the image taken from the photo gallery as a preview in the app, and enables the "see list" button
     * @param requestCode requestCode from photo
     * @param resultCode resultCode from photo
     * @param data how the photo is stored
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Uri targetUri = data.getData();
            try {
                if(targetUri != null)
                    bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));

                preview.setImageBitmap(bitmap);

                //enable the button
                scan.setEnabled(true);
                rotate.setEnabled(true);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * takes the user to the inventory display page
     * @param v instance of a View object
     */
    public void toInventory(View v) {
        Intent intentInventory = new Intent(this,InventoryDisplay.class);

        startActivity(intentInventory);
    }

    /**
     * takes the user to the home page (Main Activity)
     * @param v instance of a View object
     */
    public void toHome(View v) {
        Intent intentHome = new Intent(this, MainActivity.class);

        startActivity(intentHome);
    }

    /**
     * takes the user to the list preview where they can edit items
     * @param v instance of a View object
     */
    public void toList(View v) {
        Intent intentListDisplay = new Intent(this, ListPreview.class);
        intentListDisplay.putExtra("status", addorsub);
        intentListDisplay.putExtra("receipt", receipt);
        startActivity(intentListDisplay);
    }

    /**
     * rotates the imageView, since android studios likes to display images sideways
     * code taken from https://stackoverflow.com/questions/8981845/android-rotate-image-in-imageview-by-an-angle
     */
    public void rotatePhoto() {
        Matrix mat = new Matrix();
        mat.postRotate(Integer.parseInt("90"));
        bitmap = Bitmap.createBitmap(bitmap, 0, 0,bitmap.getWidth(),bitmap.getHeight(), mat, true);
        preview.setImageBitmap(bitmap);
    }
}
