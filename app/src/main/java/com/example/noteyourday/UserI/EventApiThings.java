package com.example.noteyourday.UserI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.example.noteyourday.DayAdapters.EventListAdapter;
import com.example.noteyourday.DayAdapters.MyEventArrayAdapter;
import com.example.noteyourday.R;
import com.example.noteyourday.YelpDayApi;
import com.example.noteyourday.YelpDayClient;
import com.example.noteyourday.models.Event;
import com.example.noteyourday.models.MyDayEvent;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public  class EventApiThings extends AppCompatActivity {
    @BindView(R.id.locationTextView) TextView mLocationTextView;
        @BindView(R.id.recyclerView) RecyclerView dairyRecyclerView;
        @BindView(R.id.listView)
    ListView eventLocation;
        @BindView(R.id.eventProgressBar)
        ProgressBar dairyProgressBar;
        private EventListAdapter dayAdapter;//
private List<Event> events;
@BindView(R.id.errorTextView) TextView eventErrorTextView;

//        @BindView(R.id.nameOfArtPage) TextView eventDetails;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_artists_api_things);
            ButterKnife.bind(this);

            Intent intent = getIntent();
            String location = intent.getStringExtra("location");

//             mLocationTextView.setText("Event location:"+location);
            YelpDayApi client = YelpDayClient.getClient();
            Call<MyDayEvent> call = client.getEvents(location);
            call.enqueue(new Callback<MyDayEvent>() {
                @Override
                public void onResponse(Call<MyDayEvent> call, Response<MyDayEvent> response) {
                    hideProgressBar();

                    if (response.isSuccessful()) {
                        events = response.body().getEvents();
//                        dayEvents = response.body().getEvents();
                        dayAdapter = new EventListAdapter(EventApiThings.this, events);
                        dairyRecyclerView.setAdapter(dayAdapter);
                        RecyclerView.LayoutManager layoutManager =
                                new LinearLayoutManager(EventApiThings.this);
                        dairyRecyclerView.setLayoutManager(layoutManager);
                        dairyRecyclerView.setHasFixedSize(true);
                        System.out.println(dayAdapter);
//                        List<Event> eventList =response.body().getEvents();
//
//                        String [] events= new String[eventList.size()];
//                        for (int i=0;i<events.length;i++){
//
//                            events[i]=eventList.get(i).getName();
//                        }
//                        ArrayAdapter adapter =new MyEventArrayAdapter(EventApiThings.this,android.R.layout.simple_list_item_1,events);
//                        eventLocation.setAdapter(adapter);
//                        System.out.println(eventList);
//                        System.out.println(adapter);
//                        System.out.println(events);
                        showEvents();

                    } else {
                        showUnsuccessfulMessage();
                    }
                }


                @Override
                public void onFailure(Call<MyDayEvent> call, Throwable t) {
                    hideProgressBar();
//                    showFailureMessage();
                }


            });

        }

        private void showUnsuccessfulMessage() {
            eventErrorTextView.setText("Something went wrong. Please try again later");
          eventErrorTextView.setVisibility(View.VISIBLE);
        }


        private void showEvents() {
            eventLocation.setVisibility(View.VISIBLE);
            mLocationTextView.setVisibility(View.VISIBLE);
        }

        private void hideProgressBar() {
            dairyProgressBar.setVisibility(View.GONE);
        }
    }


