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
import com.example.noteyourday.models.Event;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
    @BindView(R.id.savedButton) Button savedEvent;
    private Event dayEvent;
    private DatabaseReference mSearchedLocationReference;
    private TextView writeYourDayView;

//private EditText eventLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        mSearchedLocationReference = FirebaseDatabase
//                .getInstance()
//                .getReference()
//                .child(DiaryConstants.FIREBASE_CHILD_SEARCHED_LOCATION);
//        mSearchedLocationReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot locationSnapshot : dataSnapshot.getChildren()) {
//                    String location = locationSnapshot.getValue().toString();
//                    Log.d("Locations updated", "location: " + location);
//                }
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
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
                Intent intent = new Intent(DisplayYourDay.this, EventListActivity.class);
                startActivity(intent);
            }

        });
        savedEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String uid = user.getUid();

                DatabaseReference eventRef = FirebaseDatabase
                        .getInstance()
                        .getReference(DiaryConstants.FIREBASE_CHILD_DAY).child(uid);
                DatabaseReference pushRef = eventRef.push();
                String pushId = pushRef.getKey();
//                dayEvent.setPushId(pushId);
                pushRef.setValue(dayEvent);
//              eventRef.push().setValue(dayEvent);
                Toast.makeText(DisplayYourDay.this, "HAPPY DAY", Toast.LENGTH_SHORT).show();
                System.out.println("Jesus love you");
                Intent intent = new Intent(DisplayYourDay.this, SavedEventListActivity.class);
                startActivity(intent);
            }
        });
    }
//    public void saveLocationToFirebase(String location) {
//        mSearchedLocationReference.push().setValue(location);
//    }
}
