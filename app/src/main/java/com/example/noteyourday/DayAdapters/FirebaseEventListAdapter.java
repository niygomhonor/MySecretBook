package com.example.noteyourday.DayAdapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.noteyourday.R;
import com.example.noteyourday.UserI.EventDetailActivity;
import com.example.noteyourday.UserI.EventDetailsFragment;
import com.example.noteyourday.models.Event;
import com.example.noteyourday.util.ItemTouchHelperAdapter;
import com.example.noteyourday.util.OnStartDragListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Collections;

public class FirebaseEventListAdapter extends FirebaseRecyclerAdapter<Event, FirebaseEventViewHolder> implements ItemTouchHelperAdapter {
    private DatabaseReference dayRef;
    private OnStartDragListener dayOnStartDragListener;
    private Context dayContext;
    private ChildEventListener dayChildEventListener;
    private int mOrientation;
    private ArrayList<Event>dayEvents = new ArrayList<>();
    public FirebaseEventListAdapter(FirebaseRecyclerOptions<Event> options,
                                    Query ref,
                                    OnStartDragListener onStartDragListener,
                                    Context context){
        super(options);
        dayRef = ref.getRef();
        dayOnStartDragListener = onStartDragListener;
        dayContext = context;
        dayChildEventListener=dayRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                dayEvents.add(dataSnapshot.getValue(Event.class));


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



    @Override
    protected void onBindViewHolder( final FirebaseEventViewHolder eventViewHolder, int i, Event event) {
        eventViewHolder.bindEvent(event);
        mOrientation = eventViewHolder.itemView.getResources().getConfiguration().orientation;
        if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            createDetailFragment(0);
        }

        eventViewHolder.eventImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
                    dayOnStartDragListener.onStartDrag(eventViewHolder);
                }
                return false;
            }
        });
        eventViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemPosition = eventViewHolder.getAdapterPosition();
                if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                    createDetailFragment(itemPosition);
                } else {
                    Intent intent = new Intent(dayContext, EventDetailActivity.class);
                    intent.putExtra("position", eventViewHolder.getAdapterPosition());
                    intent.putExtra("events", Parcels.wrap(dayEvents));
                    dayContext.startActivity(intent);
                }
            }
        });

    }
    private void createDetailFragment(int position) {
        // Creates new RestaurantDetailFragment with the given position:
       EventDetailsFragment detailFragment = EventDetailsFragment.newInstance(dayEvents, position);
        // Gathers necessary components to replace the FrameLayout in the layout with the RestaurantDetailFragment:
        FragmentTransaction ft = ((FragmentActivity) dayContext).getSupportFragmentManager().beginTransaction();
        //  Replaces the FrameLayout with the RestaurantDetailFragment:
        ft.replace(R.id.restaurantDetailContainer, detailFragment);
        // Commits these changes:
        ft.commit();
    }
    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(dayEvents, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        setIndexInForebase();
        return false;
    }
    private void setIndexInForebase() {
        for (Event event : dayEvents) {
            int index = dayEvents.indexOf(event);
            DatabaseReference mReference = getRef(index);
            event.setIndex(Integer.toString(index));
            mReference.setValue(event);
        }
    }
    @Override
    public void onItemDismiss(int position) {
        getRef(position).removeValue();
    }

    @Override
    public void cleanup() {

    }

    @NonNull
    @Override
    public FirebaseEventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_list_item, parent, false);
        return new FirebaseEventViewHolder(view);
    }

}
