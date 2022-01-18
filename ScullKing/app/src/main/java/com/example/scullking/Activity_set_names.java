package com.example.scullking;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Activity_set_names extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private int anzahl_spieler;
    private String server_root;

    private String[] names;
    private String[] server_names;

    private EditText editText_player_1;
    private EditText editText_player_2;
    private EditText editText_player_3;
    private EditText editText_player_4;
    private EditText editText_player_5;
    private EditText editText_player_6;

    private EditText create_player;

    private  EditText[] editTexts;

    private Button button_set_names;
    private Button button_create_player;
    private Button button_choose_players;

    private Intent intent_choose_players;
    private int number_of_server_players;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_names);
        Intent intent_got = getIntent();
        this.anzahl_spieler = intent_got.getIntExtra("number_of_players",3);
        this.server_root = intent_got.getStringExtra("server_root");

        editText_player_1 = (EditText) this.findViewById(R.id.editTextTextPersonName1);
        editText_player_2 = (EditText) this.findViewById(R.id.editTextTextPersonName2);
        editText_player_3 = (EditText) this.findViewById(R.id.editTextTextPersonName3);
        editText_player_4 = (EditText) this.findViewById(R.id.editTextTextPersonName4);
        editText_player_5 = (EditText) this.findViewById(R.id.editTextTextPersonName5);
        editText_player_6 = (EditText) this.findViewById(R.id.editTextTextPersonName6);

        create_player = (EditText) this.findViewById(R.id.create_player_name);

        editTexts = new EditText[]{editText_player_1,editText_player_2,editText_player_3,editText_player_4,editText_player_5,editText_player_6};

        for (int i = this.anzahl_spieler;i<6;i++){
            editTexts[i].setFocusable(false);
            editTexts[i].setClickable(false);
            editTexts[i].setVisibility(View.INVISIBLE);
        }

        intent_choose_players = new Intent(this,Activity_choose_players.class);
        intent_choose_players.putExtra("number_of_players",anzahl_spieler);

        button_set_names = (Button) this.findViewById(R.id.button_set_names);
        button_set_names.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name_1 = editText_player_1.getText().toString();
                String name_2 = editText_player_2.getText().toString();
                String name_3 = editText_player_3.getText().toString();
                String name_4 = editText_player_4.getText().toString();
                String name_5 = editText_player_5.getText().toString();
                String name_6 = editText_player_6.getText().toString();

                String[] names = {name_1,name_2,name_3,name_4,name_5,name_6};
                Intent resultIntent = new Intent();
                resultIntent.putExtra("names", names);
                resultIntent.putExtra("server_names",server_names);
                resultIntent.putExtra("number_of_server_players",number_of_server_players);

                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });

        button_create_player = (Button) this.findViewById(R.id.button_create_player);
        button_create_player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                create_player(create_player.getText().toString());
            }
        });

        button_choose_players = (Button) this.findViewById(R.id.buttonchoosePlayer);
        button_choose_players.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent_choose_players.putExtra("server_root",server_root);
                startActivityForResult(intent_choose_players,3107);
            }
        });




    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (3107) : {
                if (resultCode == Activity.RESULT_OK) {
                    // TODO Extract the data returned from the child Activity.
                    this.server_names = data.getStringArrayExtra("names_of_players");
                    this.number_of_server_players = data.getIntExtra("number_of_server_players",3);
                    for(int i = 0; i<this.server_names.length;i++){
                        this.editTexts[i].setText(this.server_names[i]);
                    }

                }
                break;
            }

        }
    }
    public void set_names(String[] names){
        this.names = names;
    }

    public String[] get_names(){
        return this.names;
    }

    public void create_player(String name){
        DatabaseReference set_Punkte = database.getReference(this.server_root+"/"+name+"/points");
        DatabaseReference set_Siege = database.getReference(this.server_root+"/"+name+"/wins");
        DatabaseReference set_Spiele = database.getReference(this.server_root+"/"+name+"/played games");
        DatabaseReference set_Name = database.getReference(this.server_root+"/"+name+"/name");
        DatabaseReference set_zeros = database.getReference(this.server_root+"/"+name+"/called zeros");
        DatabaseReference set_failedzeros = database.getReference(this.server_root+"/"+name+"/failed zeros");
        DatabaseReference set_riskyzeros = database.getReference(this.server_root+"/"+name+"/risky zeros");
        DatabaseReference set_called_tricks = database.getReference(this.server_root+"/"+name+"/called tricks");
        DatabaseReference set_correct_prediction = database.getReference(this.server_root+"/"+name+"/correct called tricks");
        DatabaseReference set_correct_points_per_games = database.getReference(this.server_root+"/"+name+"/points per game");
        DatabaseReference set_correct_predicted_rounds = database.getReference(this.server_root+"/"+name+"/correct predicted rounds");
        DatabaseReference set_bonus_points = database.getReference(this.server_root+"/"+name+"/bonus points");




        set_Punkte.setValue(0);
        set_Siege.setValue(0);
        set_Spiele.setValue(0);
        set_Name.setValue(name);
        set_zeros.setValue(0);
        set_failedzeros.setValue(0);
        set_riskyzeros.setValue(0);
        set_called_tricks.setValue(0);
        set_correct_prediction.setValue(0);
        set_correct_points_per_games.setValue(0);
        set_correct_predicted_rounds.setValue(0);
        set_bonus_points.setValue(0);
    }

}