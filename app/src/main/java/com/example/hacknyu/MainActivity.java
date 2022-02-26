package com.example.hacknyu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageProxy;

import java.io.File;
import java.io.IOException;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.hacknyu.ml.Model;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.label.ImageLabeler;
import com.google.mlkit.vision.label.ImageLabeling;
import com.google.mlkit.vision.label.automl.AutoMLImageLabelerLocalModel;
import com.google.mlkit.vision.label.custom.CustomImageLabelerOptions;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.image.TensorImage;
import org.tensorflow.lite.support.label.Category;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView username = (TextView) findViewById(R.id.username);
        TextView password = (TextView) findViewById(R.id.password);

        MaterialButton loginbtn = (MaterialButton) findViewById(R.id.loginbtn);
        Log.i("ACTIVITY","HERE@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        processImage();


    }

    public void processImage(){
        try {
            Model model = Model.newInstance(getApplicationContext());
            Bitmap bitmap = BitmapFactory.decodeStream( getAssets().open("glass8.bmp"));
            // Creates inputs for reference.
            TensorImage image = TensorImage.fromBitmap(bitmap);
            // Runs model inference and gets result.
            Model.Outputs outputs = model.process(image);
            List<Category> scores = outputs.getScoresAsCategoryList();
            String Label1 = scores.get(0).getLabel();
            String Score1 = String.valueOf(scores.get(0).getScore());

            String Label2 = scores.get(1).getLabel();
            String Score2 = String.valueOf(scores.get(1).getScore());
            Log.i("ACTIVITY",Label1 + " " + Score1);

            // Releases model resources if no longer used.
            model.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}



