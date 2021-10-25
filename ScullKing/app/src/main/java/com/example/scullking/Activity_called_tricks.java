package com.example.scullking;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Activity_called_tricks extends AppCompatActivity {
    private Button button_set_called_tricks;

    private int number_of_players;
    private int round;

    private String[] names;

    //private EditText editText_called_1;
    //private EditText editText_called_2;
    //private EditText editText_called_3;
    //private EditText editText_called_4;
    //private EditText editText_called_5;
    //private EditText editText_called_6;

    private SeekBar seekBar_called_1;
    private SeekBar seekBar_called_2;
    private SeekBar seekBar_called_3;
    private SeekBar seekBar_called_4;
    private SeekBar seekBar_called_5;
    private SeekBar seekBar_called_6;


    private TextView textView_name_1;
    private TextView textView_name_2;
    private TextView textView_name_3;
    private TextView textView_name_4;
    private TextView textView_name_5;
    private TextView textView_name_6;

    private TextView textView_called_progress_1;
    private TextView textView_called_progress_2;
    private TextView textView_called_progress_3;
    private TextView textView_called_progress_4;
    private TextView textView_called_progress_5;
    private TextView textView_called_progress_6;

    //private EditText[] editTexts;
    private SeekBar[] seekBars;
    private TextView[] textViews_names;
    private TextView[] textViews_progress_called;

    private Intent get_intent;
    private int[] called_tricks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.called_tricks);
        button_set_called_tricks = (Button) this.findViewById(R.id.set_Tricks);

        this.get_intent = new Intent();
        this.get_intent = getIntent();
        this.number_of_players = this.get_intent.getIntExtra("number_of_players",3);
        this.names = this.get_intent.getStringArrayExtra("names");
        this.round = this.get_intent.getIntExtra("round",1);

        //this.editText_called_1 = (EditText) this.findViewById(R.id.calledtricksplayer1);
        //this.editText_called_2 = (EditText) this.findViewById(R.id.calledtricksplayer2);
        //this.editText_called_3 = (EditText) this.findViewById(R.id.calledtricksplayer3);
        //this.editText_called_4 = (EditText) this.findViewById(R.id.calledtricksplayer4);
        //this.editText_called_5 = (EditText) this.findViewById(R.id.calledtricksplayer5);
        //this.editText_called_6 = (EditText) this.findViewById(R.id.calledtricksplayer6);

        //editTexts = new EditText[]{editText_called_1,editText_called_2,editText_called_3,editText_called_4,editText_called_5,editText_called_6};

        this.seekBar_called_1 = (SeekBar) this.findViewById(R.id.seekBar_called_1);
        this.seekBar_called_2 = (SeekBar) this.findViewById(R.id.seekBar_called_2);
        this.seekBar_called_3 = (SeekBar) this.findViewById(R.id.seekBar_called_3);
        this.seekBar_called_4 = (SeekBar) this.findViewById(R.id.seekBar_called_4);
        this.seekBar_called_5 = (SeekBar) this.findViewById(R.id.seekBar_called_5);
        this.seekBar_called_6 = (SeekBar) this.findViewById(R.id.seekBar_called_6);

        this.seekBars = new SeekBar[] {this.seekBar_called_1,this.seekBar_called_2,this.seekBar_called_3,this.seekBar_called_4,this.seekBar_called_5,this.seekBar_called_6};

        this.textView_called_progress_1 = (TextView) this.findViewById(R.id.textView_called_progress_1);
        this.textView_called_progress_2 = (TextView) this.findViewById(R.id.textView_called_progress_2);
        this.textView_called_progress_3 = (TextView) this.findViewById(R.id.textView_called_progress_3);
        this.textView_called_progress_4 = (TextView) this.findViewById(R.id.textView_called_progress_4);
        this.textView_called_progress_5 = (TextView) this.findViewById(R.id.textView_called_progress_5);
        this.textView_called_progress_6 = (TextView) this.findViewById(R.id.textView_called_progress_6);

        this.textViews_progress_called = new TextView[]{this.textView_called_progress_1,this.textView_called_progress_2,this.textView_called_progress_3,this.textView_called_progress_4,this.textView_called_progress_5,this.textView_called_progress_6};

        this.textView_name_1 = (TextView) this.findViewById(R.id.textView_called_points_1);
        this.textView_name_2 = (TextView) this.findViewById(R.id.textView_called_points_2);
        this.textView_name_3 = (TextView) this.findViewById(R.id.textView_called_points_3);
        this.textView_name_4 = (TextView) this.findViewById(R.id.textView_called_points_4);
        this.textView_name_5 = (TextView) this.findViewById(R.id.textView_called_points_5);
        this.textView_name_6 = (TextView) this.findViewById(R.id.textView_called_points_6);

        textViews_names = new TextView[]{textView_name_1,textView_name_2,textView_name_3,textView_name_4,textView_name_5,textView_name_6};
        set_names();
        button_set_called_tricks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                set_called_tricks();
            }
        });

        this.seekBar_called_1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView_called_progress_1.setText(Integer.toString( (int) (progress/10.0*round)));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        this.seekBar_called_2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView_called_progress_2.setText(Integer.toString( (int) (progress/10.0*round)));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        this.seekBar_called_3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView_called_progress_3.setText(Integer.toString( (int) (progress/10.0*round)));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        this.seekBar_called_4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView_called_progress_4.setText(Integer.toString( (int) (progress/10.0*round)));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        this.seekBar_called_5.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView_called_progress_5.setText(Integer.toString( (int) (progress/10.0*round)));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        this.seekBar_called_6.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView_called_progress_6.setText(Integer.toString( (int) (progress/10.0*round)));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        for (int i = this.number_of_players;i<6;i++){
            this.textViews_progress_called[i].setFocusable(false);
            this.textViews_progress_called[i].setClickable(false);
            this.textViews_progress_called[i].setVisibility(View.INVISIBLE);

            this.seekBars[i].setFocusable(false);
            this.seekBars[i].setClickable(false);
            this.seekBars[i].setVisibility(View.INVISIBLE);

            this.textViews_names[i].setFocusable(false);
            this.textViews_names[i].setClickable(false);
            this.textViews_names[i].setVisibility(View.INVISIBLE);
        }

    }
    public void set_names(){
        for (int i = 0;i<this.number_of_players;i++){
            this.textViews_names[i].setText(this.names[i]);
        }
    }
    public void set_called_tricks(){
        this.called_tricks = new int[this.number_of_players];
        for (int i = 0;i<this.number_of_players;i++){
            this.called_tricks[i] = (int) (this.seekBars[i].getProgress()/10.0*round);
        }

        System.out.println("Heheyh: "+Integer.toString(this.called_tricks[0]));
        Intent intent_return = new Intent();
        intent_return.putExtra("called_tricks",this.called_tricks);
        setResult(Activity.RESULT_OK,intent_return);
        this.finish();




    }
}