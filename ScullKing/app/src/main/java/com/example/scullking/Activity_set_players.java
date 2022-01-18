package com.example.scullking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class Activity_set_players extends Activity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private String[] server_names;
    private String server_root = "";
    private Button[] buttons;
    private Button button_server;
    private Button b;
    private ScrollView scrollView_server;
    private LinearLayout linearLayout_server;
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
        this.scrollView_server = (ScrollView) findViewById(R.id.scrollView_server);

        this.linearLayout_server = (LinearLayout) this.findViewById(R.id.linearlayout_server);

        this.button_server = (Button) findViewById(R.id.button_server);
        this.button_server.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollView_server.setVisibility(View.VISIBLE);
            }
        });
        button_set_players.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent();
                boolean risky_zero = checkBox_risky_zero.isChecked();
                int number_players = seekBar_number_of_players.getProgress();
                resultIntent.putExtra("risky_zero",risky_zero);
                resultIntent.putExtra("number_of_players",number_players+3);
                resultIntent.putExtra("server_root",server_root);
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


        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                collectServerNames((Map<String,Object>) snapshot.getValue());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("Hallo");
            }
        });
    }

    private void collectServerNames(Map<String, Object> servers) {
            ArrayList<String> names = new ArrayList<>();

            //iterate through each user, ignoring their UID
            for (Map.Entry<String, Object> entry : servers.entrySet()){

                //Get user map
                //Map singleplayer = (Map) entry.getValue();
                //Get phone field and append to list
                names.add((String) entry.getKey());
            }

            create_Buttons(names);
        }

    private void create_Buttons(ArrayList<String> names){
        this.server_names = new String[names.size()];
        int size = names.size();
        buttons = new Button[size];
        System.out.println();
        for(int i = 0; i<size;i++) {
            this.server_names[i] = names.get(i);
            b = new Button(this);
            b.setText(names.get(i));
            b.setId(i);
            b.setTextSize(10);
            b.setPadding(3, 2, 3, 2);
            b.setTypeface(Typeface.SERIF, Typeface.BOLD_ITALIC);
            b.setBackgroundColor(Color.GRAY);
            b.setLayoutParams(new LinearLayout.LayoutParams(500,200));
            buttons[i] = b;

            this.linearLayout_server.addView(b);

            int finalI = i;
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        server_root = server_names[finalI];
                    }

                });
        }

        for (int i = 0;i<this.server_names.length;i++){
            System.out.println("Servernames: " + this.server_names[i]);
        }
    }

    public void close_window(){
        this.finish();
    }

    public String[] get_names(){
        return this.names;
    }

}
