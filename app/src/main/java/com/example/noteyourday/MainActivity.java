package com.example.noteyourday;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
//@BindView(R.id.startButton) Button saveButton;
//@BindView(R.id.dayEditText) EditText dayEdit;
//@BindView(R.id.nameOfApp) TextView nameOfApp;

    private Button startButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton = (Button) findViewById(R.id.startButton);
//dayEdit=(EditText) findViewById(R.id.dayEditText);

//        ButterKnife.bind(this);
        startButton.setOnClickListener(this);



//        feelingsOfDay.setText(myDay);
    }
    @Override
    public void onClick(View v) {

        if (v == startButton) {
            Intent day = new Intent(MainActivity.this, MyDiaryFeeling.class);

//            String myDay = day.getStringExtra("myDay");
//
//            day.putExtra("myDay",myDay);
            startActivity(day);
        }
    }
}
