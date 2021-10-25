package com.example.scullking;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Game game;

    private Activity_set_players activity_set_players;
    private Activity_set_names activity_set_names;
    private Activity_called_tricks activity_called_tricks;


    private Button button_new_game;

    private int number_of_players;
    private boolean risky_zero = false;
    private String[] names;
    private Gui gui;
    Intent intent_player_number;
    Intent intent_player_names;
    Intent intent_game_terminal;

    private Button button_input_new_tricks;
    private Button button_set_names;
    private Button button_set_players;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainActivity hhh = new MainActivity();

        this.intent_player_number = new Intent(this,Activity_set_players.class);
        this.intent_player_names = new Intent(this, Activity_set_names.class);
        this.intent_game_terminal = new Intent(this, Activity_game_screen_terminal.class);
        button_new_game = (Button) this.findViewById(R.id.NewGameButton);
        this.button_new_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open_activity_set_players();

            }
        });

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (0) : {
                if (resultCode == Activity.RESULT_OK) {
                    // TODO Extract the data returned from the child Activity.
                    this.number_of_players = data.getIntExtra("number_of_players",3);
                    this.risky_zero = data.getBooleanExtra("risky_zero",false);
                    open_activity_set_names();
                }
                break;
            }

            case (1) : {
                if (resultCode == Activity.RESULT_OK) {
                    // TODO Extract the data returned from the child Activity.
                    this.names = data.getStringArrayExtra("names");
                    open_game_screen_terminal();
                }
                break;
            }
            //screen after game is finished
            case (2) : {
                if (resultCode == Activity.RESULT_OK) {
                    // TODO Extract the data returned from the child Activity.
                    //this.names = data.
                }
                break;
            }

        }
    }

    public void open_activity_set_players(){
        startActivityForResult(this.intent_player_number,0);
    }

    public void open_activity_set_names(){
        this.intent_player_names.putExtra("number_of_players",this.number_of_players);
        startActivityForResult(this.intent_player_names,1);
    }

    public void open_game_screen_terminal(){
        this.intent_game_terminal.putExtra("number_of_players",this.number_of_players);
        this.intent_game_terminal.putExtra("risky_zero",this.risky_zero);
        this.intent_game_terminal.putExtra("names",this.names);
        startActivityForResult(this.intent_game_terminal,2);
    }

    public void close_Intent(){
        finish();
    }






}