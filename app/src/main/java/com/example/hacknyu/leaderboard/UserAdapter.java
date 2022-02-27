package com.example.hacknyu.leaderboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hacknyu.R;

import java.util.ArrayList;
import java.util.Locale;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {
    //User adapter
    private  Context context;
    private  ArrayList<User> users;

    //constructor
    public UserAdapter(Context context,ArrayList<User> users){
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_layout_item,parent,false);
        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.UserHolder holder, int position) {
  User user = users.get(position);
   holder.SetDetails(user);

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    //User Holder
    static class UserHolder extends RecyclerView.ViewHolder{
        private  TextView txt_user_name;
        private  TextView txt_amount_recycled;
        private  TextView txt_carbon_reduced;
        public UserHolder(@NonNull View itemView){
            super(itemView);
            txt_user_name = itemView.findViewById(R.id.txt_user_name);
            txt_amount_recycled = itemView.findViewById(R.id.txt_amount_recycled);
            txt_carbon_reduced = itemView.findViewById(R.id.txt_carbon_reduced);
        }

        void SetDetails(User user){
            txt_user_name.setText(user.getUser_name());
            txt_amount_recycled.setText(String.format(Locale.US,"Amount you have recycled: %.2f",user.getAmount_recycled()));
            txt_carbon_reduced.setText(String.format(Locale.US,"carbon you have reduced: %.2f",user.getCarbon_reduced()));

        }
    }
}
