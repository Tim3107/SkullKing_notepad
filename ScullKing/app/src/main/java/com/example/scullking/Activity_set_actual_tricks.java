package com.example.scullking;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.net.Inet4Address;

public class Activity_set_actual_tricks extends AppCompatActivity {
    private Button button_return;
    private TextView textView_error;

    private SeekBar seekBar_actual_1;
    private SeekBar seekBar_actual_2;
    private SeekBar seekBar_actual_3;
    private SeekBar seekBar_actual_4;
    private SeekBar seekBar_actual_5;
    private SeekBar seekBar_actual_6;

    private SeekBar[] seekBars_actual;

    private TextView textView_actual_progress_1;
    private TextView textView_actual_progress_2;
    private TextView textView_actual_progress_3;
    private TextView textView_actual_progress_4;
    private TextView textView_actual_progress_5;
    private TextView textView_actual_progress_6;

    private TextView[] textViews_actual_progress;

    private EditText[] editTexts;

    private TextView textView_name_actual_1;
    private TextView textView_name_actual_2;
    private TextView textView_name_actual_3;
    private TextView textView_name_actual_4;
    private TextView textView_name_actual_5;
    private TextView textView_name_actual_6;

    private TextView[] textViews_actual;

    private Intent get_intent;

    private int number_of_players;
    private int round;
    private String[] names;

    private int[] actual_tricks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_actual_tricks);
        this.get_intent = getIntent();

        this.number_of_players = this.get_intent.getIntExtra("number_of_players",3);
        this.names = this.get_intent.getStringArrayExtra("names");
        this.round = this.get_intent.getIntExtra("round",1);
        this.button_return = (Button) this.findViewById(R.id.Button_return_actual_tricks);
        this.textView_error = (TextView) this.findViewById(R.id.textView_error);

        this.seekBar_actual_1 = (SeekBar) this.findViewById(R.id.seekBar1);
        this.seekBar_actual_2 = (SeekBar) this.findViewById(R.id.seekBar3);
        this.seekBar_actual_3 = (SeekBar) this.findViewById(R.id.seekBar4);
        this.seekBar_actual_4 = (SeekBar) this.findViewById(R.id.seekBar5);
        this.seekBar_actual_5 = (SeekBar) this.findViewById(R.id.seekBar6);
        this.seekBar_actual_6 = (SeekBar) this.findViewById(R.id.seekBar7);

        this.seekBars_actual = new SeekBar[]{this.seekBar_actual_1,this.seekBar_actual_2,this.seekBar_actual_3,this.seekBar_actual_4,this.seekBar_actual_5,this.seekBar_actual_6};

        this.textView_actual_progress_1 = (TextView) this.findViewById(R.id.textView_actual_progress_1);
        this.textView_actual_progress_2 = (TextView) this.findViewById(R.id.textView_actual_progress_2);
        this.textView_actual_progress_3 = (TextView) this.findViewById(R.id.textView_actual_progress_3);
        this.textView_actual_progress_4 = (TextView) this.findViewById(R.id.textView_actual_progress_4);
        this.textView_actual_progress_5 = (TextView) this.findViewById(R.id.textView_actual_progress_5);
        this.textView_actual_progress_6 = (TextView) this.findViewById(R.id.textView_actual_progress_6);

        this.textViews_actual_progress = new TextView[]{this.textView_actual_progress_1,this.textView_actual_progress_2,this.textView_actual_progress_3,this.textView_actual_progress_4,this.textView_actual_progress_5,this.textView_actual_progress_6};

        this.textView_name_actual_1 = (TextView) this.findViewById(R.id.textView_actual_1);
        this.textView_name_actual_2 = (TextView) this.findViewById(R.id.textView_actual_2);
        this.textView_name_actual_3 = (TextView) this.findViewById(R.id.textView_actual_3);
        this.textView_name_actual_4 = (TextView) this.findViewById(R.id.textView_actual_4);
        this.textView_name_actual_5 = (TextView) this.findViewById(R.id.textView_actual_5);
        this.textView_name_actual_6 = (TextView) this.findViewById(R.id.textView_actual_6);

        this.textViews_actual = new TextView[]{this.textView_name_actual_1,this.textView_name_actual_2,this.textView_name_actual_3,this.textView_name_actual_4,this.textView_name_actual_5,this.textView_name_actual_6};

        this.set_names();

        button_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                return_actual_tricks();
            }
        });

        this.seekBar_actual_1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView_actual_progress_1.setText(Integer.toString( (int) (progress/10.0*round)));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        this.seekBar_actual_2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView_actual_progress_2.setText(Integer.toString( (int) (progress/10.0*round)));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        this.seekBar_actual_3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView_actual_progress_3.setText(Integer.toString( (int) (progress/10.0*round)));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        this.seekBar_actual_4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView_actual_progress_4.setText(Integer.toString( (int) (progress/10.0*round)));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        this.seekBar_actual_5.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView_actual_progress_5.setText(Integer.toString( (int) (progress/10.0*round)));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        this.seekBar_actual_6.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView_actual_progress_6.setText(Integer.toString( (int) (progress/10.0*round)));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        for (int i = this.number_of_players;i<6;i++){
            this.textViews_actual[i].setFocusable(false);
            this.textViews_actual[i].setClickable(false);
            this.textViews_actual[i].setVisibility(View.INVISIBLE);

            this.seekBars_actual[i].setFocusable(false);
            this.seekBars_actual[i].setClickable(false);
            this.seekBars_actual[i].setVisibility(View.INVISIBLE);

            this.textViews_actual_progress[i].setFocusable(false);
            this.textViews_actual_progress[i].setClickable(false);
            this.textViews_actual_progress[i].setVisibility(View.INVISIBLE);
        }
    }
    public void set_names(){
        for (int i = 0;i<this.number_of_players;i++){
            this.textViews_actual[i].setText(this.names[i]);
        }
    }

    public void return_actual_tricks(){
        this.actual_tricks = new int[this.number_of_players];
        for (int i = 0;i<this.number_of_players;i++){
            this.actual_tricks[i] = (int) (this.seekBars_actual[i].getProgress()/10.0*round);
        }
        int sum_of_tricks = 0;
        for (int i = 0;i<this.number_of_players;i++){
            sum_of_tricks += this.actual_tricks[i];
        }

        if(sum_of_tricks == this.round) {

            Intent intent_return = new Intent();
            intent_return.putExtra("actual_tricks", this.actual_tricks);
            setResult(Activity.RESULT_OK, intent_return);
            finish();
        }

        else{
            this.textView_error.setBackgroundResource(R.color.black);
            this.textView_error.setText("The amount of tricks " + Integer.toString(sum_of_tricks) +" \n and the current round  " +Integer.toString(this.round) +" do not match! \n Please correct your inputs.");
        }
    }
}