package com.example.noteyourday.UserI;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;


import com.example.noteyourday.R;
import com.example.noteyourday.models.Event;
import com.example.noteyourday.util.OnEventSelectedListener;

import java.util.ArrayList;


public class EventListActivity extends AppCompatActivity implements OnEventSelectedListener {
    private Integer dayPosition;
    ArrayList<Event> dayEvents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

    }
    @Override
    public void onEventSelected(Integer position, ArrayList<Event> events) {
        dayPosition = position;
        dayEvents = events;
    }
}
