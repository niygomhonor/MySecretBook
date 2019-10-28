package com.example.noteyourday.UserI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.noteyourday.R;

public class MyDays extends AppCompatActivity {

    private EditText writeYourDay;
    private TextView pageHeader;
    private Button saveWritings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_days);

        writeYourDay = (EditText) findViewById(R.id.dayEditText);
        pageHeader = (TextView) findViewById(R.id.pageHeading);
        saveWritings = (Button) findViewById(R.id.saveButton);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "font/delighter.otf");
        saveWritings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == saveWritings) {
                    String writings = writeYourDay.getText().toString();
                    Intent intent = new Intent(MyDays.this, DisplayYourDay.class);

//
                    Toast.makeText(MyDays.this, writings, Toast.LENGTH_LONG).show();
                    intent.putExtra("writings", writings);
                    startActivity(intent);
                }


            }

        });


    }
}
