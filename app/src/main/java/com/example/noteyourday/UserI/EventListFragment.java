package com.example.noteyourday.UserI;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.noteyourday.DayAdapters.EventListAdapter;
import com.example.noteyourday.DiaryConstants;
import com.example.noteyourday.R;
import com.example.noteyourday.models.Event;
import com.example.noteyourday.networks.YelpDayServices;
import com.example.noteyourday.util.OnEventSelectedListener;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class EventListFragment extends Fragment {
    @BindView(R.id.recyclerView)
    RecyclerView dairyRecyclerView;
    private EventListAdapter dayAdapter;//
    private ArrayList<Event> events = new ArrayList<>();

    private SharedPreferences daySharedPreferences;
    private SharedPreferences.Editor dayEditor;
    private String dayRecentAddress;
    private OnEventSelectedListener mOnEventSelectedListener;
    public EventListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        daySharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        dayEditor = daySharedPreferences.edit();

        // Instructs fragment to include menu options:
        setHasOptionsMenu(true);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mOnEventSelectedListener = (OnEventSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + e.getMessage());
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_event_list, container, false);
        ButterKnife.bind(this, view);

        dayRecentAddress = daySharedPreferences.getString(DiaryConstants.PREFERENCES_LOCATION_KEY, null);

        if (dayRecentAddress != null) {
            getEvents(dayRecentAddress);
        }

        return view;
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
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dayAdapter = new EventListAdapter(getActivity(), events);
                        dairyRecyclerView.setAdapter(dayAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                        dairyRecyclerView.setLayoutManager(layoutManager);
                        dairyRecyclerView.setHasFixedSize(true);
                    }

                });
            }
        });
    }
       @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){

        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_search,menu);
           MenuItem menuItem=menu.findItem(R.id.action_search);
           SearchView searchView=(SearchView) MenuItemCompat.getActionView(menuItem);
           searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
               @Override
               public boolean onQueryTextSubmit(String query) {
                               addTosharedPreferences(query);
                               getEvents(query);
                   return false;
               }

               @Override
               public boolean onQueryTextChange(String newText) {
                   return false;
               }
           });
       }
            @Override
           public boolean onOptionsItemSelected(MenuItem item)    {

        return super.onOptionsItemSelected(item);
            }
   private void  addTosharedPreferences(String location){

        dayEditor.putString(DiaryConstants.PREFERENCES_LOCATION_KEY,location).apply();
   }
}