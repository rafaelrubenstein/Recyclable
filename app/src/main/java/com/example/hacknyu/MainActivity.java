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
        processImage();


    }

    public void processImage(){
        try {
            Model model = Model.newInstance(getApplicationContext());
            // Creates inputs for reference.
            TensorImage image = TensorImage.fromBitmap(BitmapFactory.decodeFile("glass8.bmp"));
            // Runs model inference and gets result.
            Model.Outputs outputs = model.process(image);
            List<Category> scores = outputs.getScoresAsCategoryList();
            // Releases model resources if no longer used.
            System.out.print(scores);
            model.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}



