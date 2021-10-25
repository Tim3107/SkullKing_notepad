package com.example.scullking;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Gui {

    private MainActivity main_screen;
    private Activity_called_tricks called_tricks_screen;
    private Activity_set_names set_names_screen;

    private Button button_new_game;
    //private Button button_input_new_tricks;
    //private Button button_set_names;



    public Gui(){

    }
    public Gui(MainActivity main_screen){
        this.main_screen = main_screen;
        this.set_names_screen = new Activity_set_names();
        this.called_tricks_screen = new Activity_called_tricks();
        //this.called_tricks_screen = called_tricks_screen;
        //this.set_names_screen = set_names_screen;

        this.button_new_game = (Button) this.main_screen.findViewById(R.id.NewGameButton);
        Button button_input_new_tricks = (Button) this.called_tricks_screen.findViewById(R.id.set_Tricks);
        Button button_set_names = (Button) this.set_names_screen.findViewById(R.id.button_set_names);




        this.button_new_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //main_screen.open_activity();
            }
        });

        button_input_new_tricks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });




    }



}
