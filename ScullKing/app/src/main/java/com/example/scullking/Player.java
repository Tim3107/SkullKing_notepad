package com.example.scullking;

public class Player {

    private int player_number;
    private String name;
    private int points;
    private int call_points;
    private boolean risky_zero = false;

    public Player(int player_number, String name, int points){
        this.player_number = player_number;
        this.name = name;
        this.points = 0;
    }

    public int get_points(){
        return this.points;
    }

    public void set_risky_zero(){
        this.risky_zero = true;
    }

    public boolean get_risky_zero(){
        return this.risky_zero;
    }

    public int get_call_points(){
        return this.call_points;
    }

    public void update_points(int new_points){
        this.points += new_points;
    }

    public String get_name(){
        return this.name;
    }


}
