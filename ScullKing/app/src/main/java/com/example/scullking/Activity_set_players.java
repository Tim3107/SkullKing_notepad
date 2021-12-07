package com.example.scullking;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Activity_set_players extends Activity {

    private Game game;
    private Button button_set_players;
    private CheckBox checkBox_risky_zero;
    private SeekBar seekBar_number_of_players;
    private TextView textView_seekbar_progress;
    private TextView test;
    private String[] names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_players);

        button_set_players = (Button) this.findViewById(R.id.button_set_players);
        seekBar_number_of_players = (SeekBar) this.findViewById(R.id.seekBar2);
        this.checkBox_risky_zero = (CheckBox) this.findViewById(R.id.checkBox_risky_zero);

        textView_seekbar_progress = (TextView) this.findViewById(R.id.seekbar_textview);

        button_set_players.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent();
                boolean risky_zero = checkBox_risky_zero.isChecked();
                int number_players = seekBar_number_of_players.getProgress();
                resultIntent.putExtra("risky_zero",risky_zero);
                resultIntent.putExtra("number_of_players",number_players+3);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });

        seekBar_number_of_players.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                textView_seekbar_progress.setText(String.valueOf(seekBar.getProgress()+3));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void close_window(){
        this.finish();
    }

    public String[] get_names(){
        return this.names;
    }





}