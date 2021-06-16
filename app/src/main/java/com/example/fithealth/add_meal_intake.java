package com.example.fithealth;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class add_meal_intake extends AppCompatActivity {

    ImageView captureImageView;
    Button captureButton, saveButton, backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal_intake);

        captureImageView = findViewById(R.id.imageView);
        captureButton = findViewById(R.id.captureButton);
        saveButton = findViewById(R.id.saveButton);
        backButton = findViewById(R.id.backButton);

        // request camera...runtime permission
        if(ContextCompat.checkSelfPermission(add_meal_intake.this, Manifest.permission.CAMERA)
        != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(add_meal_intake.this, new String[]{
                    Manifest.permission.CAMERA
            }, 100);
        }

        captureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 100);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 100) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            //set imageView
            captureImageView.setImageBitmap(bitmap);
        }
    }
}