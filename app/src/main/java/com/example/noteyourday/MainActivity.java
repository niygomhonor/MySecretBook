package com.example.noteyourday;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.startButton)
    Button startButton;

//    private Button startButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        startButton = (Button) findViewById(R.id.startButton);


        ButterKnife.bind(this);
        startButton.setOnClickListener(this);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "font/delighter.otf");


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
