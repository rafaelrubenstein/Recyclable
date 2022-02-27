package com.example.hacknyu;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;

import com.example.hacknyu.ml.Model;
import com.google.android.material.button.MaterialButton;

import org.tensorflow.lite.support.image.TensorImage;
import org.tensorflow.lite.support.label.Category;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CameraActivity extends AppCompatActivity {

    private ImageView imageView;
    private MaterialButton camerabtn;
    private TextView direction;
    private Button gallerybtn;
    private TextView best_matching; // to display labeling
    private TextView second_matching;
    private TextView carbon;

    // Uri for picking image from gallery
    Uri imageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        imageView = findViewById(R.id.imageView);
        camerabtn = findViewById(R.id.camerabtn);
        direction = findViewById(R.id.direction);
        gallerybtn = findViewById(R.id.gallerybtn);
        best_matching = findViewById(R.id.bestmatchingtextView);
        second_matching = findViewById(R.id.secondmathchingtextView);
        carbon = findViewById(R.id.carbontextView);

        if (ContextCompat.checkSelfPermission(CameraActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(CameraActivity.this, new String[]{Manifest.permission.CAMERA}, 101);


        camerabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent open_camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(open_camera, 101);
            }
        });

        // gallery button
        gallerybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                openGallery();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 101){ // Take photo
            Bitmap bitmap= (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);
            processImage(bitmap);
        } else if (requestCode == 102){ // Choose from gallery
            if (resultCode == RESULT_OK && requestCode == 102){
                imageUri = data.getData(); // should be global
                try {
                    Bitmap bitmap =getBitmap(this.getContentResolver(), imageUri) ;
                    processImage(bitmap);
                    imageView.setImageBitmap(bitmap);
                } catch (RuntimeException e){
                    Log.i("ERROR","ERROR");
                }
            }
        }
    }

    // Open user's gallery
    public void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, 102);
    }


    // Convert @uri to bitmap
    public Bitmap getBitmap(ContentResolver cr, Uri uri) {
        try {
            return BitmapFactory.decodeStream(cr.openInputStream(uri));
        } catch (FileNotFoundException e){
            throw new RuntimeException("File does not exist");
        }
    }
    // Analyze @bitmap and change textViews accordingly
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void processImage(Bitmap bitmap){
        try {
            Model model = Model.newInstance(getApplicationContext());
            // Creates inputs for reference.
            TensorImage image = TensorImage.fromBitmap(bitmap);
            // Runs model inference and gets result.
            Model.Outputs outputs = model.process(image);
            List<Category> scores = outputs.getScoresAsCategoryList();
            scores.sort(Comparator.comparing(x -> -x.getScore())); // Sort descending
            best_matching.setText(scores.get(0).getLabel() + " : " + String.valueOf(scores.get(0).getScore()) + "%"); // Best Match
            second_matching.setText(scores.get(1).getLabel() + " : " + String.valueOf(scores.get(1).getScore()) + "%"); // Second best match
            // Releases model resources if no longer used.
            setCarbonText(scores.get(0).getLabel());
            model.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setCarbonText(String type){
        if (type.equals("plastic")){
            carbon.setText("On average, a plastic bottle has 82.8g of CO2");
        } else if (type.equals("glass")){
            carbon.setText("67 grams of Co2 will be saved by recycling 100g of glass");
        } else if (type.equals("cardboard")){
            carbon.setText("82 grams of Co2 will be saved by recycling 100g of cardboards");
        } else if (type.equals("metal")){
            carbon.setText("You are saving 98.7g of CO2 by recycling a single can");
        } else {
            carbon.setText("46g of CO2 will be saved by recycling 100g of papers");
        }
    }
}