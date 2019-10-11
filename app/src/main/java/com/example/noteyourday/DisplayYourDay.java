package com.example.noteyourday;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;

public class DisplayYourDay extends AppCompatActivity {
private TextView writeYourDayView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_your_day);
        Calendar getDate=Calendar.getInstance();
        String currentDate= DateFormat.getDateInstance(DateFormat.FULL).format(getDate.getTime());
        TextView showDate=findViewById(R.id.displayDayDate);
        writeYourDayView=(TextView) findViewById(R.id.displayDayTextView);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "font/delighter.otf");
        Intent intent=getIntent();
        String writings = intent.getStringExtra("writings");
        writeYourDayView.setText(writings);
        showDate.setText(currentDate);
    }
}
