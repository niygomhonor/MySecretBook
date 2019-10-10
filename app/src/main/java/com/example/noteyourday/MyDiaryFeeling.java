package com.example.noteyourday;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MyDiaryFeeling extends AppCompatActivity {
//@BindView(R.id.dayEditText) TextView dayEdit;
private EditText dayEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_diary);
        dayEdit=(EditText) findViewById(R.id.dayEditText);
        Intent day =getIntent();
        String myDay=day.getStringExtra("myDay");
//        ButterKnife.bind(this);
        Toast.makeText(MyDiaryFeeling.this,myDay,Toast.LENGTH_LONG).show();


        dayEdit.setText("My Day:"+ myDay);
    }
}
