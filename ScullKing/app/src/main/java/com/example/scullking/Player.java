package com.example.scullking;

public class Player {

    private int player_number;
    private int[] bonusPoints = new int[10];
    private String name;
    private int points;
    private int call_points;
    private int round = 0;
    private boolean risky_zero = false;
    private int[] points_array;
    private int[] called_tricks = {0,0,0,0,0,0,0,0,0,0};
    private boolean[] succes = {false,false,false,false,false,false,false,false,false,false};
    private int risky_zero_round = -1;


    public Player(int player_number, String name, int points){
        this.player_number = player_number;
        this.points_array = new int[]{0,0,0,0,0,0,0,0,0,0};
        this.name = name;
        this.points = 0;
    }

    public int get_points(){
        return this.points;
    }

    public int getBonusPoints(){
        int sum = 0;
        for (int i = 0;i<10;i++){
            sum += this.bonusPoints[i];
        }
        return sum;
    }

    public int get_points(int round){
        return this.points_array[round];
    }

    public void set_risky_zero(){
        this.risky_zero = true;
        if(this.risky_zero_round == -1){
            this.risky_zero_round = this.round;
        }
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
        this.bonusPoints[this.round-1] = 0;
        if(this.risky_zero_round == this.round-1){
            this.risky_zero = false;
            this.risky_zero_round = -1;
        }
        this.points_array[this.round-1] = 0;
        if(this.round<2){
            this.points = 0;
            this.succes[0] = false;
            this.called_tricks[0] = 0;
        }
        else{
            this.points = this.points_array[this.round-2];
            this.succes[this.round-1] = false;
            this.called_tricks[this.round-1] = 0;
        }

        this.round--;
    }

    public void set_called_tricks(int tricks, int round){
        this.called_tricks[round-1] = tricks;
    }

    public void setBonusPoints(int bonusPoints){
        this.bonusPoints[this.round-1] = bonusPoints;
    }

    public void set_reached_tricks(int pround){
        this.succes[pround-1] = true;
    }

    public int called_tricks(){
        int sum = 0;
        for (int i = 0;i<10;i++){
            sum+=this.called_tricks[i];
        }
        return sum;
    }

    public int succesfully_called_tricks(){
        int sum = 0;
        for (int i = 0;i<10;i++){
            if(this.succes[i]) {
                sum += this.called_tricks[i];
            }
        }
        return sum;
    }

    public int called_zeros(){
        int sum = 0;
        for (int i = 0;i<10;i++){
            if(called_tricks[i]==0) {
                sum++;
            }
        }
        return sum;
    }

    public int failed_zeros(){
        int sum = 0;
        for (int i = 0;i<10;i++){
            if(called_tricks[i]==0 && !this.succes[i]){
                sum++;
            }
        }
        return sum;
    }


    public int correctPredictedRounds() {
        int temp = 0;
        for (int i = 0;i<10;i++){
            if(succes[i]){
                temp++;
            }
        }
        return temp;
    }

    public int riskyZero(){
        if (this.risky_zero){
            return 1;
        }

        else{
            return 0;
        }
    }
}
