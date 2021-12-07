package com.example.scullking;

public class Player {

    private int player_number;
    private String name;
    private int points;
    private int call_points;
    private int round = 0;
    private boolean risky_zero = false;
    private int[] points_array;

    public Player(int player_number, String name, int points){
        this.player_number = player_number;
        this.points_array = new int[]{0,0,0,0,0,0,0,0,0,0};
        this.name = name;
        this.points = 0;
    }

    public int get_points(){
        return this.points;
    }

    public int get_points(int round){
        return this.points_array[round];
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
        this.points_array[round] = this.points;
        this.round++;
    }

    public String get_name(){
        return this.name;
    }

    public void correction(int pround){
        System.out.println(Integer.toString(round)+"sssssssssssssssssssssssssssss");
        this.points_array[this.round-1] = 0;
        if(this.round<2){
            this.points = 0;
        }
        else{
            this.points = this.points_array[this.round-2];
        }

        this.round--;
    }


}
