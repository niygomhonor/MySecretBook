package com.example.noteyourday.DayAdapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.example.noteyourday.R;

//import com.example.noteyourday.UserI.EventApiThings;
import com.example.noteyourday.UserI.EventDetailActivity;
import com.example.noteyourday.models.Event;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.EventViewHolder> {

    private List<Event> dayEvents;
    private Context dayContext;

    public EventListAdapter(Context context, List<Event> events) {
        dayEvents = events;
        dayContext = context;
    }

    @Override
    public EventListAdapter.EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_list_item, parent, false);
        EventViewHolder viewHolder = new EventViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(EventListAdapter.EventViewHolder holder, int position) {
        holder.bindEvent(dayEvents.get(position));
    }


    @Override
    public int getItemCount() {
        return dayEvents.size();
    }

    public class EventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

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

        public EventViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.dayContext = itemView.getContext();
            itemView.setOnClickListener(this);
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
            Intent intent = new Intent(dayContext, EventDetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("events", Parcels.wrap(dayEvents));
            dayContext.startActivity(intent);
        }
    }
}