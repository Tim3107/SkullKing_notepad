package com.example.scullking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DatabaseRegistrar;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    FirebaseStorage storage;
    StorageReference storageReference;

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    DatabaseReference myRef_name = database.getReference("Tim/Punkte");
    DatabaseReference myRef_value = database.getReference("Test");

    private Game game;

    private Activity_set_players activity_set_players;
    private Activity_set_names activity_set_names;
    private Activity_called_tricks activity_called_tricks;


    private Button button_new_game;

    private boolean save_to_server = false;
    private String server_root;
    private int number_of_players;
    private String[] server_names;
    private boolean risky_zero = false;
    private String[] names;
    private Gui gui;
    Intent intent_player_number;
    Intent intent_player_names;
    Intent intent_game_terminal;

    private Button button_input_new_tricks;
    private Button button_set_names;
    private Button button_set_players;
    private int number_of_server_players;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainActivity hhh = new MainActivity();

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

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

    protected void onStart(){
        super.onStart();
        myRef_name.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        myRef_value.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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
                    this.server_root = data.getStringExtra("server_root");
                    if(this.server_root != ""){
                        this.save_to_server = true;
                    }
                    open_activity_set_names();
                }
                break;
            }

            case (1) : {
                if (resultCode == Activity.RESULT_OK) {
                    // TODO Extract the data returned from the child Activity.
                    this.names = data.getStringArrayExtra("names");
                    this.server_names = data.getStringArrayExtra("server_names");
                    this.number_of_server_players = data.getIntExtra("number_of_server_players",3);
                    open_game_screen_terminal();
                }
                break;
            }

        }
    }

    public void open_activity_set_players(){
        // Create a storage reference from our app
        /*
        StorageReference storageRef = storage.getReference();

        // Create a reference to "mountains.jpg"
        StorageReference mountainsRef = storageRef.child("Tim.txt");

        Uri file = Uri.fromFile(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + "/"+"Tim"+".txt"));
        StorageReference testing = storageRef.child(file.getLastPathSegment());
        mountainsRef.putFile(file);

         */

        startActivityForResult(this.intent_player_number,0);


    }

    public void open_activity_set_names(){
        this.intent_player_names.putExtra("number_of_players",this.number_of_players);
        this.intent_player_names.putExtra("server_root",this.server_root);
        startActivityForResult(this.intent_player_names,1);
    }

    public void open_game_screen_terminal(){
        this.intent_game_terminal.putExtra("number_of_players",this.number_of_players);
        this.intent_game_terminal.putExtra("risky_zero",this.risky_zero);
        this.intent_game_terminal.putExtra("names",this.names);
        this.intent_game_terminal.putExtra("server_names",server_names);
        this.intent_game_terminal.putExtra("server_root",this.server_root);
        this.intent_game_terminal.putExtra("number_of_server_players",this.number_of_server_players);
        startActivityForResult(this.intent_game_terminal,2);
    }

    public void close_Intent(){
        finish();
    }











}