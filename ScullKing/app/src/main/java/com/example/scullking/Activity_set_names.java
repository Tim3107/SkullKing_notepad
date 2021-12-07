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

    private String[] names;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_names);
        Intent intent_got = getIntent();
        this.anzahl_spieler = intent_got.getIntExtra("number_of_players",3);
        System.out.println(this.anzahl_spieler + "Hier sollte mal nicht 3 stehen:D");

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
        DatabaseReference set_Punkte = database.getReference(name+"/Punkte");
        DatabaseReference set_Siege = database.getReference(name+"/Siege");
        DatabaseReference set_Spiele = database.getReference(name+"/Spiele");
        DatabaseReference set_Tore = database.getReference(name+"/Tore");
        DatabaseReference set_Name = database.getReference(name+"/Name");

        set_Punkte.setValue(0);
        set_Siege.setValue(1);
        set_Spiele.setValue(1);
        set_Tore.setValue(10);
        set_Name.setValue(name);
    }

}