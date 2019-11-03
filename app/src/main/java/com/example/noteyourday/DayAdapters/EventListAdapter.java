package com.example.noteyourday.DayAdapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noteyourday.R;

//import com.example.noteyourday.UserI.EventApiThings;
import com.example.noteyourday.UserI.EventDetailActivity;
import com.example.noteyourday.UserI.EventDetailsFragment;
import com.example.noteyourday.models.Event;
import com.example.noteyourday.util.OnEventSelectedListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;


import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.EventViewHolder> {

    private ArrayList<Event> dayEvents=new  ArrayList<>();
    private Context dayContext;
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;
    private OnEventSelectedListener mOnEventSelectedListener;
    public EventListAdapter(Context context, ArrayList<Event> events,OnEventSelectedListener dayOnEventSelectedListener) {
        dayEvents = events;
        dayContext = context;
        mOnEventSelectedListener=dayOnEventSelectedListener;
    }

    @Override
    public EventListAdapter.EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_list_item, parent, false);
        EventViewHolder viewHolder = new EventViewHolder(view,dayEvents,mOnEventSelectedListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(EventListAdapter.EventViewHolder holder, int position) {
        holder.bindEvent(dayEvents.get(position));
    }
//    @Override
//    public Fragment getItem(int position) {
//        return EventDetailsFragment.newInstance(dayEvents, position);
//    }

    @Override
    public int getItemCount() {
        return dayEvents.size();
    }

    public class EventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private int dayOrientation;
        @BindView(R.id.costTextView)
        TextView costOfEvent;
        //        @BindView(R.id.addressTextView) TextView eventAddress;
        @BindView(R.id.eventImageView)
        ImageView eventImage;
        @BindView(R.id.eventNameTextView)
        TextView eventName;
        @BindView(R.id.startTextView)
        TextView beginningTime;
        private Context dayContext;

        public EventViewHolder(View itemView, ArrayList<Event> dayEvents, OnEventSelectedListener mOnEventSelectedListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.dayContext = itemView.getContext();
            itemView.setOnClickListener(this);
            dayOrientation = itemView.getResources().getConfiguration().orientation;
            if (dayOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                createDetailFragment(0);
            }
        }


        private void createDetailFragment(int position) {
            // Creates new RestaurantDetailFragment with the given position:
            EventDetailsFragment detailFragment =EventDetailsFragment.newInstance(dayEvents, position);
            // Gathers necessary components to replace the FrameLayout in the layout with the RestaurantDetailFragment:
            FragmentTransaction ft = ((FragmentActivity) dayContext).getSupportFragmentManager().beginTransaction();
            //  Replaces the FrameLayout with the RestaurantDetailFragment:
            ft.replace(R.id.eventDetailContainer, detailFragment);
            // Commits these changes:
            ft.commit();
        }

        public void bindEvent(Event event) {
            eventName.setText(event.getName());
            beginningTime.setText("Start Time:" + event.getTimeStart());
            costOfEvent.setText("The cost is " + event.getCost());
//eventAddress.setText(event.getLocation().toString());
            Picasso.get().load(event.getImageUrl()).into(eventImage);
        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            if (dayOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                createDetailFragment(itemPosition);
            }
            else {
                Intent intent = new Intent(dayContext, EventDetailActivity.class);
                intent.putExtra("position", itemPosition);
                intent.putExtra("events", Parcels.wrap(dayEvents));
                dayContext.startActivity(intent);
            }
        }
    }
}