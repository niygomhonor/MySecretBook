package com.example.noteyourday;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyDiaryFeeling extends AppCompatActivity {
@BindView(R.id.feelingView) ListView feelView;
//private EditText dayEdit;
//private TextView myDayFeelings;
//private ListView feelView;\
@BindView(R.id.myDayFeeling) TextView feelingsOfDay;


//    private TextView feelingsOfDay;
//    private ListView feelView;
    private String[] dayFeelings = new String[]{"Happy", "Proud", "Loved", "Fantastic", "Great", "Crazy",
            "Relaxed", "Angry", "Devilish", "Blessed", "Cheerful", "Energetic", "Beautiful", "Excited", "Lucky", "Disappointed", "Sleepy", "In love", "Satisfied"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_diary);
//        saveButton = (Button) findViewById(R.id.saveButton);
//dayEdit=(EditText) findViewById(R.id.dayEditText);
//        feelingsOfDay = (TextView) findViewById(R.id.myDayFeeling);
//        feelView = (ListView) findViewById(R.id.feelingView);
        ButterKnife.bind(this);
//        saveButton.setOnClickListener(this);
        Intent day = getIntent();
        String myDay = day.getStringExtra("myDay");
        MyDaysAdapter daysAdapter = new MyDaysAdapter(this, android.R.layout.simple_list_item_1, dayFeelings);
        Toast.makeText(this, ""+dayFeelings, Toast.LENGTH_SHORT).show();
        feelView.setAdapter(daysAdapter);
        feelView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String feelings = ((TextView) view).getText().toString();
                Toast.makeText(MyDiaryFeeling.this, feelings, Toast.LENGTH_LONG).show();
                Intent dayFeel=new Intent(MyDiaryFeeling.this,MyDays.class);
//                feelingsOfDay.setText(feelings);
                startActivity(dayFeel);

            }
        });
//        feelingsOfDay.setText(myDay);
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_my_diary);
//        dayEdit=(EditText) findViewById(R.id.dayEditText);
//        myDayFeelings=(TextView) findViewById(R.id.myDayFeeling);
//        Intent day =getIntent();
//        String myDay=day.getStringExtra("myDay");
////        ButterKnife.bind(this);
//        Toast.makeText(MyDiaryFeeling.this,myDay,Toast.LENGTH_LONG).show();
//
//        Toast.makeText(this, "Hello"+day, Toast.LENGTH_LONG).show();
//        myDayFeelings.setText("My Day:"+ myDay);
//    }
}
