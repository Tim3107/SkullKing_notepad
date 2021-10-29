package com.example.scullking;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.TestLooperManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class Activity_extra_points extends AppCompatActivity {

    private int number_of_players;
    private String[] names;
    private boolean[] risky_zero_returns;
    private boolean risky_zero;
    private int round;

    private int[] bonus_points;
    private Intent intent_return;

    private TextView textView_bonus_name_1;
    private TextView textView_bonus_name_2;
    private TextView textView_bonus_name_3;
    private TextView textView_bonus_name_4;
    private TextView textView_bonus_name_5;
    private TextView textView_bonus_name_6;

    private TextView[] textViews_bonus_name;

    private EditText editText_bonus_1;
    private EditText editText_bonus_2;
    private EditText editText_bonus_3;
    private EditText editText_bonus_4;
    private EditText editText_bonus_5;
    private EditText editText_bonus_6;

    private EditText[] editTexts_bonus;

    private CheckBox checkBox_1;
    private CheckBox checkBox_2;
    private CheckBox checkBox_3;
    private CheckBox checkBox_4;
    private CheckBox checkBox_5;
    private CheckBox checkBox_6;

    private CheckBox[] checkBoxes;

    private Button button_set_bonus;

    private TextView textView_exception;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra_points);
        Intent get_Intent = getIntent();
        this.number_of_players = get_Intent.getIntExtra("number_of_players",3);
        this.round = get_Intent.getIntExtra("round",1);
        this.names = get_Intent.getStringArrayExtra("names");
        boolean[] risky_zeros = get_Intent.getBooleanArrayExtra("risky_zeros");
        this.risky_zero = get_Intent.getBooleanExtra("risky_zero",false);

        this.textView_bonus_name_1 = (TextView) this.findViewById(R.id.textView_bonus_names_1);
        this.textView_bonus_name_2 = (TextView) this.findViewById(R.id.textView_bonus_names_2);
        this.textView_bonus_name_3 = (TextView) this.findViewById(R.id.textView_bonus_names_3);
        this.textView_bonus_name_4 = (TextView) this.findViewById(R.id.textView_bonus_names_4);
        this.textView_bonus_name_5 = (TextView) this.findViewById(R.id.textView_bonus_names_5);
        this.textView_bonus_name_6 = (TextView) this.findViewById(R.id.textView_bonus_names_6);

        this.textViews_bonus_name = new TextView[] {this.textView_bonus_name_1,this.textView_bonus_name_2,this.textView_bonus_name_3,this.textView_bonus_name_4,this.textView_bonus_name_5,this.textView_bonus_name_6};

        this.editText_bonus_1 = (EditText) this.findViewById(R.id.editText_bonus_1);
        this.editText_bonus_2 = (EditText) this.findViewById(R.id.editText_bonus_2);
        this.editText_bonus_3 = (EditText) this.findViewById(R.id.editText_bonus_3);
        this.editText_bonus_4 = (EditText) this.findViewById(R.id.editText_bonus_4);
        this.editText_bonus_5 = (EditText) this.findViewById(R.id.editText_bonus_5);
        this.editText_bonus_6 = (EditText) this.findViewById(R.id.editText_bonus_6);

        this.editTexts_bonus = new EditText[]{this.editText_bonus_1,this.editText_bonus_2,this.editText_bonus_3,this.editText_bonus_4,this.editText_bonus_5,this.editText_bonus_6};

        this.checkBox_1 = (CheckBox) this.findViewById(R.id.checkBox_1);
        this.checkBox_2= (CheckBox) this.findViewById(R.id.checkBox_2);
        this.checkBox_3 = (CheckBox) this.findViewById(R.id.checkBox_3);
        this.checkBox_4 = (CheckBox) this.findViewById(R.id.checkBox_4);
        this.checkBox_5 = (CheckBox) this.findViewById(R.id.checkBox_5);
        this.checkBox_6 = (CheckBox) this.findViewById(R.id.checkBox_6);

        this.checkBoxes = new CheckBox[]{this.checkBox_1,this.checkBox_2,this.checkBox_3,this.checkBox_4,this.checkBox_5,this.checkBox_6};

        this.button_set_bonus = (Button) this.findViewById(R.id.Button_bonus_set);
        this.textView_exception = (TextView) this.findViewById(R.id.textView_exception);

        this.bonus_points = new int[this.number_of_players];
        this.risky_zero_returns = new boolean[this.number_of_players];

        this.setNames();

        this.button_set_bonus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                get_Bonus();
            }
        });


        for (int i = this.number_of_players;i<6;i++){
            this.textViews_bonus_name[i].setFocusable(false);
            this.textViews_bonus_name[i].setClickable(false);
            this.textViews_bonus_name[i].setVisibility(View.INVISIBLE);

            this.editTexts_bonus[i].setFocusable(false);
            this.editTexts_bonus[i].setClickable(false);
            this.editTexts_bonus[i].setVisibility(View.INVISIBLE);

            this.checkBoxes[i].setFocusable(false);
            this.checkBoxes[i].setClickable(false);
            this.checkBoxes[i].setVisibility(View.INVISIBLE);
        }

        for (int i = 0; i<this.number_of_players;i++){
            this.risky_zero_returns[i] = false;

            if(risky_zeros[i] || !this.risky_zero || this.round <6){
                this.checkBoxes[i].setFocusable(false);
                this.checkBoxes[i].setClickable(false);
                this.checkBoxes[i].setVisibility(View.INVISIBLE);
            }
        }
    }

    public void setNames(){
        for (int i = 0;i<this.number_of_players;i++){
            this.textViews_bonus_name[i].setText(names[i]);
        }
    }

    public void get_Bonus(){
        try {
            for (int i = 0; i < this.number_of_players; i++) {
                this.bonus_points[i] = Integer.valueOf(this.editTexts_bonus[i].getText().toString());
                this.risky_zero_returns[i] = this.checkBoxes[i].isChecked();
            }


            Intent intent_return = new Intent();
            intent_return.putExtra("bonus_points", this.bonus_points);
            intent_return.putExtra("risky_zeros", this.risky_zero_returns);
            setResult(Activity.RESULT_OK, intent_return);
            this.finish();

        }
        catch (Exception NumberFormatException){
            this.textView_exception.setText("A given String is not convertible to an integer.\n " +
                    "Please change your input.");
        }
    }


}