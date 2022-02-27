package com.example.hacknyu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.hacknyu.leaderboard.Leaderboards;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {


Button btn1; // this can be removed (button to access leaderboard)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView username = (TextView) findViewById(R.id.username);
        TextView password = (TextView) findViewById(R.id.password);

        MaterialButton loginbtn = (MaterialButton) findViewById(R.id.loginbtn);

        //Function to for button, this can be removed as well
        btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this, Leaderboards.class);
            startActivity(i);
        });



    }
}