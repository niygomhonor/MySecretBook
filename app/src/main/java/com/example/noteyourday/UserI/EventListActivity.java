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
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.noteyourday.DayAdapters.EventListAdapter;
import com.example.noteyourday.DiaryConstants;
import com.example.noteyourday.R;
import com.example.noteyourday.models.Event;
import com.example.noteyourday.networks.YelpDayServices;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class EventListActivity extends AppCompatActivity {
    @BindView(R.id.locationTextView)
    TextView mLocationTextView;
    @BindView(R.id.recyclerView)
    RecyclerView dairyRecyclerView;
    @BindView(R.id.listView)
    ListView eventLocation;
//    @BindView(R.id.eventProgressBar)
//    ProgressBar dairyProgressBar;
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
        getEvents(location);
        daySharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        dayRecentAddress = daySharedPreferences.getString(DiaryConstants.PREFERENCES_LOCATION_KEY, null);
        if (dayRecentAddress != null) {
            getEvents(dayRecentAddress);

        }
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
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


    private void getEvents(String location) {
        final YelpDayServices yelpService = new YelpDayServices();
        yelpService.findEvents(location, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                events = yelpService.processResults(response);
               EventListActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dayAdapter = new EventListAdapter(getApplicationContext(), events);
                        dairyRecyclerView.setAdapter(dayAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(EventListActivity.this);
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
}
