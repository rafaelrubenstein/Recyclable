package com.example.hacknyu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private MaterialButton loginbtn;
    private MaterialButton signupbtn;
    private TextView username;
    private TextView password;

    private FirebaseAuth auth;

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

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_username = username.getText().toString();
                String txt_password = password.getText().toString();
                loginUser(txt_username, txt_password);

            }
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