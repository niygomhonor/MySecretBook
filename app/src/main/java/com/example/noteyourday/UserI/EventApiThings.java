package com.example.noteyourday.UserI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;


import com.example.noteyourday.DayAdapters.EventListAdapter;
import com.example.noteyourday.DiaryConstants;
import com.example.noteyourday.R;
import com.example.noteyourday.networks.YelpDayApi;
import com.example.noteyourday.networks.YelpDayClient;
import com.example.noteyourday.models.Event;
import com.example.noteyourday.models.MyDayEvent;
import com.example.noteyourday.networks.YelpDayServices;


import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EventApiThings extends AppCompatActivity {
    @BindView(R.id.locationTextView)
    TextView mLocationTextView;
    @BindView(R.id.recyclerView)
    RecyclerView dairyRecyclerView;
    @BindView(R.id.listView)
    ListView eventLocation;
    @BindView(R.id.eventProgressBar)
    ProgressBar dairyProgressBar;
    @BindView(R.id.errorTextView)
    TextView eventErrorTextView;
    private EventListAdapter dayAdapter;//
    private List<Event> events;
    private SharedPreferences daySharedPreferences;
    private SharedPreferences.Editor dayEditor;
    private String dayRecentAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        ButterKnife.bind(this);

        daySharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        dayEditor = daySharedPreferences.edit();

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                addToSharedPreferences(query);
                getEvents(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }    private void getEvents(String location) {
        final YelpDayServices yelpService = new YelpDayServices();
        yelpService.findEvents(location, new okhttp3.Callback() {

            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                events = yelpService.processResults(response);
                EventApiThings.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dayAdapter = new EventListAdapter(getApplicationContext(), events);
                        dairyRecyclerView.setAdapter(dayAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(EventApiThings.this);
                        dairyRecyclerView.setLayoutManager(layoutManager);
                        dairyRecyclerView.setHasFixedSize(true);
                    }

                });
            }
        });
    }
    private void addToSharedPreferences(String location) {
        dayEditor.putString(DiaryConstants.PREFERENCES_LOCATION_KEY, location).apply();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
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


