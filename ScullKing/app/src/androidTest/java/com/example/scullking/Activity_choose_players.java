package com.example.scullking;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.FirebaseDatabase;

public class Activity_choose_players extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private ScrollView scrollViewPlayers;
    private Button button_close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_players);


        scrollViewPlayers = (ScrollView) this.findViewById(R.id.scrollViiewPlayers);
        button_close = (Button) this.findViewById(R.id.button_close);
        button_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}
