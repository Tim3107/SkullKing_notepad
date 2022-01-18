package com.example.scullking;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;

public class Activity_game_screen_terminal extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myRef_name;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private int number_of_players;
    
    private int number_of_server;
    private boolean risky_zero;
    private boolean[] risky_zeros;
    private boolean[] server_players;
    private String server_root = "";
    private int[] bonus_points = {1,2,3,4,5,6};
    private boolean[] winner;
    private String[] names;
    private String[] sorted_names;
    private String[] server_names;
    private Player[] players;
    private String[][] stats;
    private Game game;
    private int[] called_tricks;
    private int[] actual_tricks;
    private int[] sorted_points;
    private Button button_next_round;
    private Button button_correction;
    private Button button_save;
    private Intent intent_called_tricks;
    private Intent intent_actual_tricks;
    private Intent intent_end_screen;
    private Intent intent_show_stats;
    private Intent intent_bonus_points_screen;
    private int[][] text_view_IDs = new int[][]{
        {18,19,25,26,21,24},
        {7,3,5,12,13,10},
        {72,73,70,71,76,77},
        {52,38,40,36,37,43},
        {225,227,217,219,215,216},
        {212,207,208,199,200,197},
        {254,255,250,252,246,247},
        {234,237,240,235,236,232},
        {152,154,161,162,156,159},
        {275,274,273,272,270,269},
};
    private TextView textView11;
    private TextView textView12;
    private TextView textView13;
    private TextView textView14;
    private TextView textView15;
    private TextView textView16;

    private TextView textView21;
    private TextView textView22;
    private TextView textView23;
    private TextView textView24;
    private TextView textView25;
    private TextView textView26;

    private TextView textView31;
    private TextView textView32;
    private TextView textView33;
    private TextView textView34;
    private TextView textView35;
    private TextView textView36;

    private TextView textView41;
    private TextView textView42;
    private TextView textView43;
    private TextView textView44;
    private TextView textView45;
    private TextView textView46;

    private TextView textView51;
    private TextView textView52;
    private TextView textView53;
    private TextView textView54;
    private TextView textView55;
    private TextView textView56;

    private TextView textView61;
    private TextView textView62;
    private TextView textView63;
    private TextView textView64;
    private TextView textView65;
    private TextView textView66;

    private TextView textView71;
    private TextView textView72;
    private TextView textView73;
    private TextView textView74;
    private TextView textView75;
    private TextView textView76;

    private TextView textView81;
    private TextView textView82;
    private TextView textView83;
    private TextView textView84;
    private TextView textView85;
    private TextView textView86;

    private TextView textView91;
    private TextView textView92;
    private TextView textView93;
    private TextView textView94;
    private TextView textView95;
    private TextView textView96;

    private TextView textView101;
    private TextView textView102;
    private TextView textView103;
    private TextView textView104;
    private TextView textView105;
    private TextView textView106;

    private TextView[][] textViews = new TextView[10][6];

    private TextView textView_name_1;
    private TextView textView_name_2;
    private TextView textView_name_3;
    private TextView textView_name_4;
    private TextView textView_name_5;
    private TextView textView_name_6;

    private Button button_player_1;
    private Button button_player_2;
    private Button button_player_3;
    private Button button_player_4;
    private Button button_player_5;
    private Button button_player_6;

    private Button[] buttons_player_names;
    private int number_of_server_players;

    //private TextView[] textView_names = new TextView[6];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen_terminal);

          textView11 = (TextView) this.findViewById(R.id.field11);
          textView12 = (TextView) this.findViewById(R.id.field12);
          textView13 = (TextView) this.findViewById(R.id.field13);
          textView14 = (TextView) this.findViewById(R.id.field14);
          textView15 = (TextView) this.findViewById(R.id.field15);
          textView16 = (TextView) this.findViewById(R.id.field16);

          textView21 = (TextView) this.findViewById(R.id.field21);
          textView22 = (TextView) this.findViewById(R.id.field22);
          textView23 = (TextView) this.findViewById(R.id.field23);
          textView24 = (TextView) this.findViewById(R.id.field24);
          textView25 = (TextView) this.findViewById(R.id.field25);
          textView26 = (TextView) this.findViewById(R.id.field26);

          textView31 = (TextView) this.findViewById(R.id.l);
          textView32 = (TextView) this.findViewById(R.id.field32);
          textView33 = (TextView) this.findViewById(R.id.field33);
          textView34 = (TextView) this.findViewById(R.id.field34);
          textView35 = (TextView) this.findViewById(R.id.field35);
          textView36 = (TextView) this.findViewById(R.id.field36);

          textView41 = (TextView) this.findViewById(R.id.field41);
          textView42 = (TextView) this.findViewById(R.id.field42);
          textView43 = (TextView) this.findViewById(R.id.field43);
          textView44 = (TextView) this.findViewById(R.id.field44);
          textView45 = (TextView) this.findViewById(R.id.field45);
          textView46 = (TextView) this.findViewById(R.id.field46);

          textView51 = (TextView) this.findViewById(R.id.field51);
          textView52 = (TextView) this.findViewById(R.id.field52);
          textView53 = (TextView) this.findViewById(R.id.field53);
          textView54 = (TextView) this.findViewById(R.id.field54);
          textView55 = (TextView) this.findViewById(R.id.field55);
          textView56 = (TextView) this.findViewById(R.id.field56);

          textView61 = (TextView) this.findViewById(R.id.field61);
          textView62 = (TextView) this.findViewById(R.id.field62);
          textView63 = (TextView) this.findViewById(R.id.field63);
          textView64 = (TextView) this.findViewById(R.id.field64);
          textView65 = (TextView) this.findViewById(R.id.field65);
          textView66 = (TextView) this.findViewById(R.id.field66);

          textView71 = (TextView) this.findViewById(R.id.field71);
          textView72 = (TextView) this.findViewById(R.id.field72);
          textView73 = (TextView) this.findViewById(R.id.field73);
          textView74 = (TextView) this.findViewById(R.id.field74);
          textView75 = (TextView) this.findViewById(R.id.field75);
          textView76 = (TextView) this.findViewById(R.id.field76);

          textView81 = (TextView) this.findViewById(R.id.field81);
          textView82 = (TextView) this.findViewById(R.id.field82);
          textView83 = (TextView) this.findViewById(R.id.field83);
          textView84 = (TextView) this.findViewById(R.id.field84);
          textView85 = (TextView) this.findViewById(R.id.field85);
          textView86 = (TextView) this.findViewById(R.id.field86);

          textView91 = (TextView) this.findViewById(R.id.field91);
          textView92 = (TextView) this.findViewById(R.id.field92);
          textView93 = (TextView) this.findViewById(R.id.field93);
          textView94 = (TextView) this.findViewById(R.id.field94);
          textView95 = (TextView) this.findViewById(R.id.field95);
          textView96 = (TextView) this.findViewById(R.id.field96);

          textView101 = (TextView) this.findViewById(R.id.field101);
          textView102 = (TextView) this.findViewById(R.id.field102);
          textView103 = (TextView) this.findViewById(R.id.field103);
          textView104 = (TextView) this.findViewById(R.id.field104);
          textView105 = (TextView) this.findViewById(R.id.field105);
          textView106 = (TextView) this.findViewById(R.id.field106);

        this.textViews = new TextView[][]{
            {textView11,textView12,textView13,textView14,textView15,textView16},
            {textView21,textView22,textView23,textView24,textView25,textView26},
            {textView31,textView32,textView33,textView34,textView35,textView36},
            {textView41,textView42,textView43,textView44,textView45,textView46},
            {textView51,textView52,textView53,textView54,textView55,textView56},
            {textView61,textView62,textView63,textView64,textView65,textView66},
            {textView71,textView72,textView73,textView74,textView75,textView76},
            {textView81,textView82,textView83,textView84,textView85,textView86},
            {textView91,textView92,textView93,textView94,textView95,textView96},
            {textView101,textView102,textView103,textView104,textView105,textView106}
        };
/*
        this.textView_name_1 = (TextView) this.findViewById(R.id.name_1);
        this.textView_name_2 = (TextView) this.findViewById(R.id.name_2);
        this.textView_name_3 = (TextView) this.findViewById(R.id.name_3);
        this.textView_name_4 = (TextView) this.findViewById(R.id.name_4);
        this.textView_name_5 = (TextView) this.findViewById(R.id.name_5);
        this.textView_name_6 = (TextView) this.findViewById(R.id.name_6);

 */

        //this.textView_names = new TextView[]{this.textView_name_1,this.textView_name_2,this.textView_name_3,this.textView_name_4,this.textView_name_5,this.textView_name_6};


        Intent intent_got = getIntent();
        this.server_root = intent_got.getStringExtra("server_root");
        if(this.server_root != ""){
            this.database = FirebaseDatabase.getInstance();
            this.myRef_name = database.getReference().child(this.server_root);
            this.myRef_name.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (!server_root.isEmpty()) {
                        System.out.println("hier"+server_root+"hier");
                        collectPlayerstats((Map<String, Object>) snapshot.getValue());
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

        this.button_next_round = (Button) this.findViewById(R.id.button_next_round);
        this.button_save = (Button) this.findViewById(R.id.button_save);

        this.button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    save_game();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        this.button_next_round.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start_new_round();
            }
        });
        this.button_correction = (Button) this.findViewById(R.id.button_correction);
        this.button_correction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                correction_step();
            }
        });

        this.number_of_players = intent_got.getIntExtra("number_of_players",3);
        this.names = intent_got.getStringArrayExtra("names");
        this.server_names = intent_got.getStringArrayExtra("server_names");
        this.number_of_server_players = intent_got.getIntExtra("number_of_server_players",3);

        this.stats = new String[this.number_of_players][11];
        this.risky_zero = intent_got.getBooleanExtra("risky_zero",false);
        players = new Player[this.number_of_players];
        this.risky_zeros = new boolean[this.number_of_players];
        for (int i = 0;i<this.number_of_players;i++){
            players[i] = new Player(i,names[i],0);
            this.risky_zeros[i] = false;
        }
        this.intent_end_screen = new Intent(this,Activity_end_screen.class);
        this.intent_called_tricks = new Intent(this,Activity_called_tricks.class);
        this.intent_actual_tricks = new Intent(this,Activity_set_actual_tricks.class);
        this.intent_show_stats = new Intent(this,Activity_show_stats.class);
        this.intent_bonus_points_screen = new Intent(this,Activity_extra_points.class);
        this.game = new Game(this.number_of_players,this.players,this.intent_called_tricks,this.risky_zero);

        this.button_player_1 = (Button) this.findViewById(R.id.name1);
        this.button_player_2 = (Button) this.findViewById(R.id.name2);
        this.button_player_3 = (Button) this.findViewById(R.id.name3);
        this.button_player_4 = (Button) this.findViewById(R.id.name4);
        this.button_player_5 = (Button) this.findViewById(R.id.name5);
        this.button_player_6 = (Button) this.findViewById(R.id.name6);

        this.buttons_player_names = new Button[]{button_player_1,button_player_2,button_player_3,button_player_4,button_player_5,button_player_6};

        for (int i = 0;i<this.number_of_players;i++){
            int finalI_loop = i;
            this.buttons_player_names[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(finalI_loop<number_of_server_players) {
                        show_player_stats(finalI_loop);
                    }


                }
            });
        }

        this.set_names();


    }

    private void collectPlayerstats(Map<String, Object> players) {
            //iterate through each user, ignoring their UID
        System.out.println(this.server_root+"serverroot");
        ArrayList<String> temp = new ArrayList<>(Arrays.asList(this.server_names));
        int i = 0;
        int index = 0;
            for (Map.Entry<String, Object> entry : players.entrySet()){
                if(Arrays.asList(this.server_names).contains(String.valueOf(((Map) entry.getValue()).get("name")))) {
                    //Get user map
                    Map singleplayer = (Map) entry.getValue();
                    index = (temp.indexOf(singleplayer.get("name")));
                    //Get phone field and append to list
                    this.stats[index][0] = String.valueOf(singleplayer.get("called tricks"));
                    this.stats[index][1] = String.valueOf(singleplayer.get("correct called tricks"));
                    this.stats[index][2] = String.valueOf(singleplayer.get("points"));
                    this.stats[index][3] = String.valueOf(singleplayer.get("name"));
                    this.stats[index][4] = String.valueOf(singleplayer.get("risky zeros"));
                    this.stats[index][5] = String.valueOf(singleplayer.get("failed zeros"));
                    this.stats[index][6] = String.valueOf(singleplayer.get("wins"));
                    this.stats[index][7] = String.valueOf(singleplayer.get("called zeros"));
                    this.stats[index][8] = String.valueOf(singleplayer.get("played games"));
                    this.stats[index][9] = String.valueOf(singleplayer.get("correct predicted rounds"));
                    this.stats[index][10] = String.valueOf(singleplayer.get("bonus points"));
                    i++;
                }
            }
            this.number_of_server = i;
    }

    private void show_player_stats(int number_of_player) {
        String[] show_stats;
        if(this.server_root != ""){
            this.intent_show_stats.putExtra("strings_to_show",this.stats[number_of_player]);
        }
        startActivity(this.intent_show_stats);
    }

    public void start_new_round(){
        this.intent_called_tricks.putExtra("number_of_players",this.number_of_players);
        this.intent_called_tricks.putExtra("names",this.names);
        this.intent_called_tricks.putExtra("round",this.game.get_round());
        startActivityForResult(this.intent_called_tricks,4);


    }

    public void set_names(){
        int anzahl_characters = 0;
        for (int i = 0;i<this.number_of_players;i++){
            anzahl_characters = this.names[i].length();
            if(anzahl_characters<3){
                this.buttons_player_names[i].setText(this.names[i]);
            }

            else if(anzahl_characters<5){
                this.buttons_player_names[i].setTextSize(8);
                this.buttons_player_names[i].setText(this.names[i]);
            }

            else{
                this.buttons_player_names[i].setTextSize(6);
                this.buttons_player_names[i].setText(this.names[i]);
            }
            //this.textView_names[i].setText(this.names[i].substring(0,1));
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (4) : {
                if (resultCode == Activity.RESULT_OK) {

                    // TODO Extract the data returned from the child Activity.
                    this.called_tricks = data.getIntArrayExtra("called_tricks");
                    game.set_called_tricks(this.called_tricks);
                    this.intent_actual_tricks.putExtra("number_of_players",this.number_of_players);
                    this.intent_actual_tricks.putExtra("names",this.names);
                    this.intent_actual_tricks.putExtra("round",this.game.get_round());
                    startActivityForResult(this.intent_actual_tricks,10);

                }
                break;
            }
            case (10) : {
                if (resultCode == Activity.RESULT_OK) {
                    // TODO Extract the data returned from the child Activity.
                    this.actual_tricks = data.getIntArrayExtra("actual_tricks");
                    game.set_actual_tricks(this.actual_tricks);
                    this.intent_bonus_points_screen.putExtra("number_of_players",this.number_of_players);
                    this.intent_bonus_points_screen.putExtra("names",this.names);
                    this.intent_bonus_points_screen.putExtra("risky_zero",this.risky_zero);
                    this.intent_bonus_points_screen.putExtra("risky_zeros",this.risky_zeros);
                    this.intent_bonus_points_screen.putExtra("round",this.game.get_round());
                    startActivityForResult(this.intent_bonus_points_screen,100);
                }
                break;
            }

            case (100) : {
                if(resultCode == Activity.RESULT_OK){
                    boolean[] risky_zeros_bonus = data.getBooleanArrayExtra("risky_zeros");
                    this.bonus_points = data.getIntArrayExtra("bonus_points");
                    this.game.set_bonus_points(this.bonus_points,risky_zeros_bonus);
                    this.update_risky_zeros(risky_zeros_bonus);
                    System.out.println("Die Runde beträgt: " + this.game.get_round());
                    game.run_round();
                    this.set_points_in_table();
                    this.game.increment_round();

                    if(game.get_round()> 10){
                        Intent parameters = new Intent();
                        this.intent_end_screen.putExtra("name","Tim");
                        this.intent_end_screen.putExtra("points",100);
                        Player sorted_players[] = new Player[this.number_of_players];
                        sorted_players = this.game.sort_by_points();
                        this.sorted_names = new String[this.number_of_players];
                        this.sorted_points = new int[this.number_of_players];
                        for (int i = 0;i<this.number_of_players;i++){
                            sorted_names[i] = sorted_players[i].get_name();
                            sorted_points[i] = sorted_players[i].get_points();
                        }
                        this.intent_end_screen.putExtra("sorted_names",sorted_names);
                        this.intent_end_screen.putExtra("sorted_points",sorted_points);
                        this.intent_end_screen.putExtra("number_of_players",this.number_of_players);
                        this.intent_end_screen.putExtra("server_names",this.server_names);
                        startActivityForResult(this.intent_end_screen,11);
                    }
                }
            break;
            }

            case (11) : {
                if (resultCode == Activity.RESULT_OK) {
                    if(this.server_root != "") {
                        upload_and_finish();
                    }
                    else{
                        finish();
                    }

                }
                break;
            }
        }
    }

    public void set_points_in_table(){
        int position = game.get_round() - 1;
        for (int i = 0;i<this.number_of_players;i++){
            this.textViews[position][i].setText(Integer.toString(game.get_player_points(i)));

        }
    }

    public void update_risky_zeros(boolean[] risky_zeros_bonus) {
        for (int i = 0;i<this.number_of_players;i++){
            if(risky_zeros_bonus[i]){
                this.players[i].set_risky_zero();
                this.risky_zeros[i] = true;
            }
        }
    }

    public void correction_step() {
        if (this.game.get_round() > 1) {
            game.correction();
            for (int i = 0; i < this.number_of_players; i++) {
                this.textViews[this.game.get_round() - 1][i].setText("");
                this.risky_zeros[i] = players[i].get_risky_zero();
            }
        }
    }

    public void save_game() throws IOException {



        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH)+1;
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        int hour = Calendar.getInstance().get(Calendar.HOUR);
        int minute = Calendar.getInstance().get(Calendar.MINUTE);

        String filename = Integer.toString(year) + "_" + Integer.toString(month) + "_" + Integer.toString(day) + "_" + Integer.toString(hour) + "_" + Integer.toString(minute);
        //File path = new File(this.getFilesDir(), "textfiles");
        this.verifyStoragePermissions(this);
        java.io.File path = new java.io.File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + "/"+filename+".txt");
        path.createNewFile();
        //File file = new File(path, "/"+"Tim"+".txt");

        path.setWritable(true);

        FileOutputStream fOut = new FileOutputStream(path);
        OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);



        myOutWriter.append(Integer.toString(this.number_of_players)+"\n");
        myOutWriter.append(Integer.toString(this.game.get_round())+"\n");
        for (int i = 0;i<this.number_of_players;i++){

            myOutWriter.append(this.names[i]+"|");
            for (int j = 0;j<this.game.get_round();j++){
                myOutWriter.append(Integer.toString(this.players[i].get_points(j))+"|");
            }
            myOutWriter.append("\n");
        }


        myOutWriter.close();
        fOut.close();
    }

    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so Prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void upload_and_finish(){
        int[] final_points = this.game.get_points();
        this.winner = new boolean[this.number_of_players];
        get_winners();
        ArrayList<String> arrayList_names = new ArrayList<>(Arrays.asList(this.server_names));
        for(int i = 0;i<this.number_of_players;i++){
            if(arrayList_names.contains(this.names[i])) {
                DatabaseReference myref_local = myRef_name.child(this.names[i]).child("points");         //Points
                myref_local.setValue(ServerValue.increment(final_points[i]));
                myref_local = myRef_name.child(this.names[i]).child("played games");                     //Games
                myref_local.setValue(ServerValue.increment(1));
                if(this.winner[i]) {
                    myref_local = myRef_name.child(this.names[i]).child("wins");                         //wins
                    myref_local.setValue(ServerValue.increment(1));
                }
                myref_local = myRef_name.child(this.names[i]).child("called tricks");
                myref_local.setValue(ServerValue.increment(this.players[i].called_tricks()));            //called_tricks
                myref_local = myRef_name.child(this.names[i]).child("correct called tricks");
                myref_local.setValue(ServerValue.increment(this.players[i].succesfully_called_tricks()));            //succesfully_called_tricks
                myref_local = myRef_name.child(this.names[i]).child("failed zeros");
                myref_local.setValue(ServerValue.increment(this.players[i].failed_zeros()));            //failed_zeros
                myref_local = myRef_name.child(this.names[i]).child("called zeros");
                myref_local.setValue(ServerValue.increment(this.players[i].called_zeros()));            //called_zeros
                myref_local = myRef_name.child(this.names[i]).child("correct predicted rounds");
                
                myref_local.setValue(ServerValue.increment(this.players[i].correctPredictedRounds()));            //correct predicted rounds
                myref_local = myRef_name.child(this.names[i]).child("risky zeros");
                myref_local.setValue(ServerValue.increment(this.players[i].riskyZero()));            //correct predicted rounds
                myref_local = myRef_name.child(this.names[i]).child("bonus points");
                myref_local.setValue(ServerValue.increment(this.players[i].getBonusPoints()));            //correct predicted rounds


            }
        }

        finish();
    }

    private void get_winners() {
        this.winner = new boolean[this.number_of_players];
        int max_value = 0;//getMax(this.sorted_points);
        for (int i = 0;i<this.number_of_players;i++){
            max_value = Math.max(max_value,this.sorted_points[i]);
        }

        for(int i = 0;i<this.number_of_players;i++){
            if(this.sorted_points[i] == max_value){
                this.winner[i] = true;
            }
            else {
                this.winner[i] = false;
            }

        }

    }
}

