package com.example.hacknyu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageProxy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.content.Intent;
import android.net.Uri;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hacknyu.ml.Model;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.example.hacknyu.leaderboard.Leaderboards;


import com.google.android.gms.tasks.OnSuccessListener;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {
    private MaterialButton loginbtn;
    private MaterialButton signupbtn;
    private TextView username;
    private TextView password;


    private FirebaseAuth auth;





    Button btn1; // this can be removed (button to access leaderboard)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Linking object in .xml file into mainactivity
        TextView username = (TextView) findViewById(R.id.username);
        TextView password = (TextView) findViewById(R.id.password);
        MaterialButton loginbtn = (MaterialButton) findViewById(R.id.loginbtn);

        MaterialButton signupbtn = (MaterialButton) findViewById(R.id.signupbtn);

        auth = FirebaseAuth.getInstance();






        //Creating a button instance that will take user from login screen to a separate screen
        //where they can sign up.
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
                finish();
            }
        });


        //Function to for button, this can be removed as well
        btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this, Leaderboards.class);
            startActivity(i);
        });



        auth = FirebaseAuth.getInstance();

        //Creating a button instance that will take user from login screen to a separate screen
        //where they can sign up.
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
                finish();
            }
        });


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_username = username.getText().toString();
                String txt_password = password.getText().toString();

                loginUser(txt_username, txt_password); }
        });

        //Function to for button, this can be removed as well
         btn1 = findViewById(R.id.btn1);
         btn1.setOnClickListener(view -> {
             Intent i = new Intent(MainActivity.this, Leaderboards.class);
             startActivity(i);
         });


            }






    private void loginUser(String username, String password) {
        auth.signInWithEmailAndPassword(username, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, CameraActivity.class));
                finish();

            }
        });
    }
}