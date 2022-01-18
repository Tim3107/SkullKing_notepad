package com.example.scullking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class Activity_show_stats extends AppCompatActivity {

    private Button button_close;


    private ScrollView scrollView_stats;
    private LinearLayout horizontal;
    private LinearLayout vertical_1;
    private LinearLayout vertical_2;

    private TextView textView_points;
    private TextView textView_points_show;
    private TextView textView_wins;
    private TextView textView_wins_show;
    private TextView textView_risky_zeros;
    private TextView textView_risky_zeros_show;
    private TextView textView_name;
    private TextView textView_name_show;
    private TextView textView_called;
    private TextView textView_called_show;
    private TextView textView_failed_zeros;
    private TextView textView_failed_zeros_show;
    private TextView textView_average;
    private TextView textView_average_show;
    private TextView textView_zeros;
    private TextView textView_zeros_show;
    private TextView textView_correct_predicted;
    private TextView textView_correct_predicted_show;
    private TextView textView_games;
    private TextView textView_games_show;
    private TextView textView_corr_pred_rounds;
    private TextView textView_corr_pred_rounds_show;
    private TextView textView_bonus_points;
    private TextView textView_bonus_points_show;

    private TextView[] textViews_label;
    private TextView[] textViews_show;

    private String[] strings_to_show;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_stats);

        this.button_close = (Button) this.findViewById(R.id.button_done);

        this.button_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent got_intent = getIntent();
        this.strings_to_show = got_intent.getStringArrayExtra("strings_to_show");

        this.scrollView_stats = (ScrollView) this.findViewById(R.id.scroll_View_Stats);
        this.horizontal = (LinearLayout) this.findViewById(R.id.Layout_hori);
        this.vertical_1 = (LinearLayout) this.findViewById(R.id.verti_1);
        this.vertical_2 = (LinearLayout) this.findViewById(R.id.verti_2);

        this.textView_average = this.findViewById(R.id.textView_average);
        this.textView_average_show = this.findViewById(R.id.textView_name_average_show);
        this.textView_called = this.findViewById(R.id.textView_called_tricks);
        this.textView_called_show = this.findViewById(R.id.textView_name_called_tricks_show);
        this.textView_correct_predicted = this.findViewById(R.id.textView_succesfully_called_tricks);
        this.textView_correct_predicted_show = this.findViewById(R.id.textView_name_correct_predicted_show);
        this.textView_points = this.findViewById(R.id.textView_points);
        this.textView_points_show = this.findViewById(R.id.textView_name_points_show);
        this.textView_name = this.findViewById(R.id.textView_name);
        this.textView_name_show = this.findViewById(R.id.textView_name_show);
        this.textView_risky_zeros = this.findViewById(R.id.textView_risky_zeros);
        this.textView_risky_zeros_show = this.findViewById(R.id.textView_name_risky_zeros_show);
        this.textView_failed_zeros = this.findViewById(R.id.textView_lost_zeros);
        this.textView_failed_zeros_show = this.findViewById(R.id.textView_name_failed_zeros_show);
        this.textView_wins = this.findViewById(R.id.textView_wins);
        this.textView_wins_show = this.findViewById(R.id.textView_name_show3);
        this.textView_zeros = this.findViewById(R.id.textView_calledzeros);
        this.textView_zeros_show = this.findViewById(R.id.textView_name_called_show);
        this.textView_games = this.findViewById(R.id.textView_games);
        this.textView_games_show = this.findViewById(R.id.textView_played_games_show);
        this.textView_corr_pred_rounds = this.findViewById(R.id.textView_corr_pred_rounds);
        this.textView_corr_pred_rounds_show = this.findViewById(R.id.textView_corr_pred_rounds_show);
        this.textView_bonus_points = this.findViewById(R.id.textView_bonus_points);
        this.textView_bonus_points_show = this.findViewById(R.id.textView_bonus_points_show);

        textViews_label = new TextView[] {textView_average,textView_called,textView_correct_predicted,textView_points,textView_name,textView_risky_zeros,textView_failed_zeros,textView_wins,textView_zeros,textView_games,textView_corr_pred_rounds,textView_bonus_points};
        textViews_show = new TextView[] {textView_average_show,textView_called_show,textView_correct_predicted_show,textView_points_show,textView_name_show,textView_risky_zeros_show,textView_failed_zeros_show,textView_wins_show,textView_zeros_show,textView_games_show,textView_corr_pred_rounds_show,textView_bonus_points_show};

       // for (int i = 0;i<this.textViews_show.length;i++){
        //    this.vertical_1.addView(this.textViews_label[i]);
        //    this.vertical_2.addView(this.textViews_show[i]);
        //}
        show_stats();





    }

    private void show_stats() {
        for (int i = 0;i<this.strings_to_show.length;i++){
            this.textViews_show[i+1].setText(this.strings_to_show[i]);
        }
        if(Integer.valueOf(this.strings_to_show[this.strings_to_show.length-1]) != 0){
            this.textViews_show[0].setText(Integer.toString((Integer.valueOf(this.strings_to_show[2])) / (Integer.valueOf(this.strings_to_show[8]))));
        }
    }
}