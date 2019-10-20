package com.example.noteyourday.DayAdapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noteyourday.R;

import com.example.noteyourday.UserI.EventApiThings;
import com.example.noteyourday.models.Event;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public  class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.EventViewHolder>  {

    private List<Event> dayEvents=new ArrayList<>();
private Context dayContext;

    public EventListAdapter(List<Event> dayEvents, Context dayContext) {
        this.dayEvents = dayEvents;
        this.dayContext = dayContext;
    }

    @NonNull
    @Override
    public EventListAdapter.EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.artist_list_item,parent,false);
        EventViewHolder viewHolder=new EventViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EventListAdapter.EventViewHolder holder, int position) {
holder.bindEvent(dayEvents.get(position));
    }


    @Override
    public int getItemCount() {
        return dayEvents.size();
    }

    public class EventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.descTextView)
        TextView eventDescription;
        @BindView(R.id.addressTextView) TextView eventAddress;
        @BindView(R.id.eventImageView)
        ImageView eventImage;
        @BindView(R.id.eventNameTextView) TextView eventName;
private Context dayContext;

        public EventViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.dayContext =itemView.getContext();
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(dayContext, EventApiThings.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("events", Parcels.wrap(dayEvents));
            dayContext.startActivity(intent);
        }

        public void bindEvent(Event event) {
eventName.setText(event.getName());
eventAddress.setText(event.getLocation().toString());
            Picasso.get().load(event.getImageUrl()).into(eventImage);
        }
    }
}