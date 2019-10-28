package com.example.noteyourday.UserI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.noteyourday.DiaryConstants;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.example.noteyourday.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.internal.Constants;

public class DisplayYourDay extends AppCompatActivity {
    @BindView(R.id.displayDayTextView)
    TextView displayYourDay;
    @BindView(R.id.searchButton)
    Button searchEventOfDay;
    private DatabaseReference mSearchedLocationReference;
    private TextView writeYourDayView;

//private EditText eventLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mSearchedLocationReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(DiaryConstants.FIREBASE_CHILD_SEARCHED_LOCATION);
        mSearchedLocationReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot locationSnapshot : dataSnapshot.getChildren()) {
                    String location = locationSnapshot.getValue().toString();
                    Log.d("Locations updated", "location: " + location);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_your_day);


        Calendar getDate = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(getDate.getTime());
        TextView showDate = findViewById(R.id.displayDayDate);
        showDate.setText(currentDate);
        ButterKnife.bind(this);
        writeYourDayView = (TextView) findViewById(R.id.displayDayTextView);
        Intent intent = getIntent();

        String writings = intent.getStringExtra("writings");

        writeYourDayView.setText(writings);

        searchEventOfDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayYourDay.this, EventApiThings.class);

                startActivity(intent);
            }
        });
    }
    public void saveLocationToFirebase(String location) {
        mSearchedLocationReference.push().setValue(location);
    }
}
