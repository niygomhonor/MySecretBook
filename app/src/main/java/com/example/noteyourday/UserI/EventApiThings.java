package com.example.noteyourday.UserI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.telecom.Call;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.example.noteyourday.DayAdapters.EventListAdapter;
import com.example.noteyourday.R;
import com.example.noteyourday.YelpDayApi;
import com.example.noteyourday.YelpDayClient;
import com.example.noteyourday.models.Event;
import com.example.noteyourday.models.MyDayEvent;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;



    public  class EventApiThings extends AppCompatActivity {

        @BindView(R.id.recyclerView)
        RecyclerView dairyRecyclerView;
        @BindView(R.id.eventProgressBar)
        ProgressBar dairyProgressBar;
        private EventListAdapter dayAdapter;//
private List<Event> dayEvents;

        @BindView(R.id.nameOfArtPage) TextView eventDetails;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_artists_api_things);

            ButterKnife.bind(this);
            Intent intent=getIntent();
            String location=intent.getStringExtra("event");

            eventDetails.setText(location);
            YelpDayApi client = YelpDayClient.getClient();
            Call<MyDayEvent> call=client.getEvents(location, "restaurants");



        }


    //    private void showUnsuccessfulMessage() {
    //        dairyProgressBar.setVisibility(View.GONE);
    //    }
    //
    //
    //    private void showArtist() {
    //        dairyRecyclerView.setVisibility(View.VISIBLE);
    //    }
    //
    //    private void hideProgressBar() {
    //        dairyProgressBar.setVisibility(View.GONE);
    //    }
    }

