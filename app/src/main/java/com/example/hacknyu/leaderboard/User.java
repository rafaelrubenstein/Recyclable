package com.example.hacknyu.leaderboard;

public class User {

private String user_name;
private double amount_recycled;
private double carbon_reduced;

    public User(String user_name, double amount_recycled, double carbon_reduced) {
        this.user_name = user_name;
        this.amount_recycled = amount_recycled;
        this.carbon_reduced = carbon_reduced;
    }

    public String getUser_name() {
        return user_name;
    }

    public double getAmount_recycled() {
        return amount_recycled;
    }

    public double getCarbon_reduced() {
        return carbon_reduced;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setAmount_recycled(double amount_recycled) {
        this.amount_recycled = amount_recycled;
    }

    public void setCarbon_reduced(double carbon_reduced) {
        this.carbon_reduced = carbon_reduced;
    }

}
