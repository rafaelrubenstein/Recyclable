package com.example.hacknyu;

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
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class CameraActivity extends AppCompatActivity {

    private ImageView imageView;
    private MaterialButton camerabtn;
    private TextView direction;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        imageView = findViewById(R.id.imageView);
        camerabtn = findViewById(R.id.camerabtn);
        direction = findViewById(R.id.direction);

        if (ContextCompat.checkSelfPermission(CameraActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(CameraActivity.this, new String[]{Manifest.permission.CAMERA}, 101);


        camerabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent open_camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(open_camera, 101);
            }
        });





    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 101){
            Bitmap bitmap= (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);
        }
    }

}