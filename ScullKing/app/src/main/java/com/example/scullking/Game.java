package com.example.scullking;
import android.app.Activity;
import android.content.Intent;

import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.Math;
import java.util.Arrays;

public class Game {
    private int number_of_players;
    private Player[] player_list;
    private int round;
    private int[][] points;
    private boolean risky_zero_param;
    private int[] called_tricks;
    private int[] actual_tricks;
    private int[] bonus_points;
    private boolean[] risky_zeros;
    private String[] names;
    private Gui gui;
    private Intent intent_called_tricks;

    public Game(int number_of_players, Player[] player_list, Intent intent_set_tricks, boolean risky_zero){
        this.number_of_players = number_of_players;
        this.risky_zero_param = risky_zero;
        this.player_list = player_list;
        this.round = 1;
        points = new int[10][this.number_of_players];
        this.risky_zero_param  = false;
        this.called_tricks = new int[this.number_of_players];
        this.actual_tricks = new int[this.number_of_players];
        this.bonus_points = new int[this.number_of_players];
        this.intent_called_tricks = intent_set_tricks;
    }

    public Game(int number_of_players, Player[] player_list, Gui gui){
        this.gui = gui;
        this.number_of_players = number_of_players;
        this.player_list = player_list;
        this.round = 1;
        points = new int[10][this.number_of_players];
        this.risky_zero_param  = false;
        this.called_tricks = new int[this.number_of_players];
        this.actual_tricks = new int[this.number_of_players];
    }

    public Game(Gui gui){
        this.gui = gui;
        this.round = 0;
        points = new int[10][this.number_of_players];
        this.risky_zero_param  = false;
        this.called_tricks = new int[this.number_of_players];
        this.actual_tricks = new int[this.number_of_players];
    }

    //
    public void run_game(){
        for(int i = 0;i<10;i++){
            this.run_round();
        }
    }

    public void run_round(){



        //int[] called_tricks = new int[this.number_of_players];
        //int[] actual_tricks = new int[this.number_of_players];

        int[] new_points = this.compute_points_list(this.called_tricks,this.actual_tricks,this.bonus_points,this.risky_zeros);
        this.update_scores(new_points);

    }

    //
    public int[] compute_points_list(int[] called_tricks, int[] actual_tricks, int[] bonus_points,boolean[] risky_zeros){
        System.out.println("Die Runde beträgt in game: " + this.round);
        int[] return_points = new int[this.number_of_players];
        for (int i=0;i<this.number_of_players;i++){
            return_points[i] = this.compute_points(called_tricks[i],actual_tricks[i],bonus_points[i],risky_zeros[i],i);
            player_list[i].set_called_tricks(called_tricks[i],this.round);
            if(called_tricks[i]==actual_tricks[i]){
                player_list[i].set_reached_tricks(this.round);
            }

        }
        return return_points;
    }


    public int compute_points(int called_tricks,int actual_tricks, int bonus_points, boolean risky_zero,int playerNumber){
        if(called_tricks == 0){
            if(actual_tricks == 0){
                if(!risky_zero){
                    return this.round*10;
                }
                else{
                    this.player_list[playerNumber].setBonusPoints(50);
                    return this.round*10 + 50;

                }

            }
            else {
                if(!risky_zero){
                    return this.round*-10;
                }
                else{
                    return this.round*-10 - 50;
                }
            }
        }
        else{
            if(called_tricks == actual_tricks){
                this.player_list[playerNumber].setBonusPoints(bonus_points);
                return 20*called_tricks + bonus_points;
            }
            else{
                return -10*Math.abs(called_tricks-actual_tricks);
            }
        }
    }

    public void update_scores(int[] points){
        for (int i = 0;i<this.number_of_players;i++){
            this.player_list[i].update_points(points[i]);
        }

    }

    public void set_called_tricks(int[] called_tricks){
        this.called_tricks = called_tricks;
    }

    public void set_actual_tricks(int[] actual_tricks){
        this.actual_tricks = actual_tricks;
    }

    public void set_bonus_points(int[] bonus_points, boolean[] risky_zeros){
        this.bonus_points = bonus_points;
        this.risky_zeros = risky_zeros;
    }

    public void set_names(String[] names){
        this.names = names;
    }

    public void set_Players(Player[] players){
        this.player_list = players;

    }

    public void set_number_of_players(int number_of_players){
        this.number_of_players = number_of_players;
    }

    public int get_round(){
        return this.round;
    }

    public int get_player_points(int number_of_player){
        return player_list[number_of_player].get_points();
    }

    public void increment_round(){
        this.round ++;
    }

    public Player[] sort_by_points(){
        int[][] points_pairs = new int[this.number_of_players][2];
        for (int i = 0;i<this.number_of_players;i++){
            points_pairs[i][0] = player_list[i].get_points();
            points_pairs[i][1] = i;
        }
        Arrays.sort(points_pairs, (a, b) -> a[0] - b[0]);

        Player[] sorted_players = new Player[this.number_of_players];
        for (int i = 0;i<this.number_of_players;i++){
            sorted_players[i] = player_list[points_pairs[i][1]];
        }
        return sorted_players;
    }

    public void correction(){

        for (int i = 0; i<this.number_of_players;i++){
            this.points[this.round-1][i] = 0;
            this.player_list[i].correction(this.round);
        }
        this.round --;
    }

    public void set_zero(){
        for (int i = 0; i<6;i++){
            this.called_tricks[i] = 0;
            this.actual_tricks[i] = 0;
            this.bonus_points[i] = 0;
        }
    }

    public int[] get_points(){
        int[] return_points = new int[this.number_of_players];
        for (int i = 0;i<this.number_of_players;i++){
            return_points[i] = player_list[i].get_points();
        }
        return return_points;
    }


}
