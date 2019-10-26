package com.example.noteyourday.UserI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.noteyourday.DayAdapters.FirebaseEventViewHolder;
import com.example.noteyourday.DiaryConstants;
import com.example.noteyourday.R;
import com.example.noteyourday.models.Event;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SavedEventListActivity extends AppCompatActivity {
    private DatabaseReference dayEventReference;
    private FirebaseRecyclerAdapter<Event, FirebaseEventViewHolder> dayFirebaseAdapter;
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        ButterKnife.bind(this);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        dayEventReference = FirebaseDatabase
                .getInstance()
                .getReference(DiaryConstants.FIREBASE_CHILD_EVENTS)
                .child(uid);
        dayEventReference= FirebaseDatabase.getInstance().getReference(DiaryConstants.FIREBASE_CHILD_EVENTS);
        setUpFirebaseAdapter();
    }
    private void setUpFirebaseAdapter(){
        FirebaseRecyclerOptions<Event> options =
                new FirebaseRecyclerOptions.Builder<Event>()
                        .setQuery(dayEventReference, Event.class)
                        .build();
        dayFirebaseAdapter = new FirebaseRecyclerAdapter<Event, FirebaseEventViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FirebaseEventViewHolder firebaseRestaurantViewHolder, int position, @NonNull Event event) {
                firebaseRestaurantViewHolder.bindEvent(event);
            }

            @NonNull
            @Override
            public FirebaseEventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_list_item, parent, false);
                return new FirebaseEventViewHolder(view);
            }
        };
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(dayFirebaseAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        dayFirebaseAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(dayFirebaseAdapter!= null) {
            dayFirebaseAdapter.stopListening();
        }
        }
    }

