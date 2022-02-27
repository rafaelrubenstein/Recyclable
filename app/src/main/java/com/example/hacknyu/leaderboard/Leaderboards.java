package com.example.hacknyu.leaderboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.hacknyu.R;

import java.util.ArrayList;


public class Leaderboards extends AppCompatActivity {
    RecyclerView leaderboard;

    private ArrayList<User> userArrayList;
    private UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboards);

        leaderboard = findViewById(R.id.leaderboard);
        leaderboard.setLayoutManager(new LinearLayoutManager(this));
        userArrayList = new ArrayList<>();
        adapter = new UserAdapter(this, userArrayList);
        leaderboard.setAdapter(adapter);
        leaderboard.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
        createListData();


    }

    private void createListData(){
        //method for adding data to recycler view

        User user  = new User("Rafael",7.9,20.5);
        userArrayList.add(user);
         user  = new User("jason",7.9,20.5);
        userArrayList.add(user);

        adapter.notifyDataSetChanged();
    }
}