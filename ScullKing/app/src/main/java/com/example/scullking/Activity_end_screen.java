package com.example.scullking;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity_end_screen extends AppCompatActivity {

    private int number_of_players;

    private Button end_button;
    private TextView end_textView;

    private TextView textView_names_1;
    private TextView textView_names_2;
    private TextView textView_names_3;
    private TextView textView_names_4;
    private TextView textView_names_5;
    private TextView textView_names_6;

    private TextView textView_points_1;
    private TextView textView_points_2;
    private TextView textView_points_3;
    private TextView textView_points_4;
    private TextView textView_points_5;
    private TextView textView_points_6;

    private TextView[] textViews_names;
    private TextView[] textViews_points;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_screen);

        this.end_button = (Button) this.findViewById(R.id.button_ending);
        this.end_textView = (TextView) this.findViewById(R.id.textView_end_screen);

        this.textView_names_1 = (TextView) this.findViewById(R.id.names1);
        this.textView_names_2 = (TextView) this.findViewById(R.id.names2);
        this.textView_names_3 = (TextView) this.findViewById(R.id.names3);
        this.textView_names_4 = (TextView) this.findViewById(R.id.names4);
        this.textView_names_5 = (TextView) this.findViewById(R.id.names5);
        this.textView_names_6 = (TextView) this.findViewById(R.id.names6);

        this.textView_points_1 = (TextView) this.findViewById(R.id.points1);
        this.textView_points_2 = (TextView) this.findViewById(R.id.points2);
        this.textView_points_3 = (TextView) this.findViewById(R.id.points3);
        this.textView_points_4 = (TextView) this.findViewById(R.id.points4);
        this.textView_points_5 = (TextView) this.findViewById(R.id.points5);
        this.textView_points_6 = (TextView) this.findViewById(R.id.points6);

        this.textViews_names = new TextView[]{this.textView_names_1,this.textView_names_2, this.textView_names_3, this.textView_names_4, this.textView_names_5, this.textView_names_6};
        this.textViews_points = new TextView[]{this.textView_points_1,this.textView_points_2,this.textView_points_3,this.textView_points_4,this.textView_points_5,this.textView_points_6};

        Intent get_intent = new Intent();
        get_intent = getIntent();
        int points = get_intent.getIntExtra("points",0);
        String player_name = get_intent.getStringExtra("name");
        int[] sorted_points = get_intent.getIntArrayExtra("sorted_points");
        String[] sorted_names = get_intent.getStringArrayExtra("sorted_names");
        this.number_of_players = get_intent.getIntExtra("number_of_players",3);

        this.end_textView.setText("Yo-Ho-Ho the next battle is still waiting");

        this.end_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_OK);
                finish();
            }
        });
        for (int i = this.number_of_players; i<6;i++){

            this.textViews_points[i].setFocusable(false);
            this.textViews_points[i].setClickable(false);
            this.textViews_points[i].setVisibility(View.INVISIBLE);

            this.textViews_names[i].setFocusable(false);
            this.textViews_names[i].setClickable(false);
            this.textViews_names[i].setVisibility(View.INVISIBLE);
        }
        this.setEnd_textView(sorted_points,sorted_names);
    }

    public void setEnd_textView(int[] points, String[] names){
        for (int i = 0; i<this.number_of_players;i++){
            this.textViews_names[this.number_of_players-i-1].setText(names[i]);
            this.textViews_points[this.number_of_players-i-1].setText(Integer.toString(points[i]));
        }
    }
}