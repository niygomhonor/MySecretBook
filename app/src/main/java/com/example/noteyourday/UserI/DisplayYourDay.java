package com.example.noteyourday.UserI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.noteyourday.R;

import java.text.DateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DisplayYourDay extends AppCompatActivity implements View.OnClickListener{


//private EditText writeYourDayArtistView;
//private  ProgressBar dairyProgressBar;
//private  RecyclerView dairyRecyclerView;
//private Button searchArtistOfDay;
private TextView writeYourDayView;
@BindView(R.id.displayDayTextView) TextView displayYourDay;
    @BindView(R.id.searchButton) Button  searchArtistOfDay;
    @BindView(R.id.eventProgressBar)
    ProgressBar dairyProgressBar;
@BindView(R.id.recyclerView)  RecyclerView dairyRecyclerView;

//    @BindView(R.id.artistProgressBar) ProgressBar dairyProgressBar;
//    @BindView(R.id.SearchDayArtist) EditText writeYourDayArtistView;
private EditText writeYourDayArtistView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_your_day);


        Calendar getDate=Calendar.getInstance();
        String currentDate= DateFormat.getDateInstance(DateFormat.FULL).format(getDate.getTime());
        TextView showDate=findViewById(R.id.displayDayDate);
        showDate.setText(currentDate);
        ButterKnife.bind(this);
        writeYourDayView=(TextView) findViewById(R.id.displayDayTextView);
        Intent intent=getIntent();

        String writings = intent.getStringExtra("writings");
//        Intent intent1=new Intent(DisplayYourDay.this, EventApiThings.class);
        writeYourDayView.setText(writings);
//All about retrieving data from Api
       writeYourDayArtistView=(EditText) findViewById(R.id.SearchDayEvent);
        searchArtistOfDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    Intent intent=new Intent(DisplayYourDay.this, EventApiThings.class);
                startActivity(intent);
                    String artist=writeYourDayArtistView.getText().toString();
                    Toast.makeText(DisplayYourDay.this, artist, Toast.LENGTH_LONG).show();
                    intent.putExtra("artist",artist);


            }
        });

    }

    @Override
    public void onClick(View v) {



    }



}
