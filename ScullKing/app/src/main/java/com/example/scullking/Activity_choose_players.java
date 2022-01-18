package com.example.scullking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ActionMenuView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Activity_choose_players extends AppCompatActivity {
    private int number_of_players;
    private int number_of_clicked_buttons = 0;
    Button[] buttons;
    String[] names_list;
    String server_root;
    Boolean[] clicked_buttons;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    LinearLayout linearLayout;
    private ScrollView scrollViewPlayers;
    private TextView textView_toomuchplayers;
    private Button button_close;
    private TextView textViewShow;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_players);
        Intent gotIntent = getIntent();
        this.number_of_players = gotIntent.getIntExtra("number_of_players",0);
        this.server_root = gotIntent.getStringExtra("server_root");

        this.textView_toomuchplayers = (TextView) findViewById(R.id.textView_toomuchplayers);
        textViewShow = (TextView) findViewById(R.id.textView_show);
        scrollViewPlayers = (ScrollView) this.findViewById(R.id.scrollViiewPlayers);
        linearLayout = (LinearLayout) this.findViewById(R.id.test_linear);
        linearLayout.setOrientation(LinearLayout.VERTICAL);


        button_close = (Button) this.findViewById(R.id.button_closing);
        button_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                extract_names_and_finish();
            }
        });

        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ref = database.child(server_root);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                collectPlayerNames((Map<String,Object>) snapshot.getValue());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("Hallo");
            }
        });

    }

    private void extract_names_and_finish() {
        List<String> return_names = new ArrayList<String>();
        for(int j=0;j<this.clicked_buttons.length;j++){
            if(this.clicked_buttons[j]){
                return_names.add(this.names_list[j]);
            }
        }

        Intent intent_return = new Intent();
        intent_return.putExtra("number_of_server_players",number_of_clicked_buttons);
        intent_return.putExtra("names_of_players",return_names.toArray(new String[0]));
        setResult(Activity.RESULT_OK,intent_return);
        this.finish();
    }

    private void collectPlayerNames(Map<String, Object> players) {
        ArrayList<String> names = new ArrayList<>();

        //iterate through each user, ignoring their UID
        for (Map.Entry<String, Object> entry : players.entrySet()){

            //Get user map
            Map singleplayer = (Map) entry.getValue();
            //Get phone field and append to list
            names.add((String) singleplayer.get("name"));
        }

        create_Buttons(names);
    }

    private void create_Buttons(ArrayList<String> names){
        int size = names.size();
        buttons = new Button[size];
        this.names_list = new String[size];
        this.clicked_buttons = new Boolean[size];
        System.out.println();
        for(int i = 0; i<size;i++) {
            this.names_list[i] = names.get(i);
            this.clicked_buttons[i] = false;
            b = new Button(this);
            b.setText(names.get(i));
            b.setId(i);
            b.setTextSize(10);
            b.setPadding(3, 2, 3, 2);
            b.setTypeface(Typeface.SERIF, Typeface.BOLD_ITALIC);
            b.setBackgroundColor(Color.GRAY);
            b.setLayoutParams(new LinearLayout.LayoutParams(500,200));
            buttons[i] = b;

            this.linearLayout.addView(b);

            int finalI = i;
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clicked_buttons[finalI] = !clicked_buttons[finalI];

                        if (clicked_buttons[finalI]) {
                            System.out.println(number_of_clicked_buttons+"number_of_clicked_buttons");
                            System.out.println(number_of_players+"number_of_players");
                            if (number_of_clicked_buttons < number_of_players) {
                                buttons[finalI].setBackgroundColor(Color.BLUE);
                                number_of_clicked_buttons++;
                                textView_toomuchplayers.setText("");
                            }

                            else {
                                textView_toomuchplayers.setText("You've already chosen all possible players");
                                clicked_buttons[finalI] = false;
                            }

                        } else {
                            buttons[finalI].setBackgroundColor(Color.GRAY);
                            textView_toomuchplayers.setText("");
                            number_of_clicked_buttons--;
                        }


                }
            });
        }
    }

}