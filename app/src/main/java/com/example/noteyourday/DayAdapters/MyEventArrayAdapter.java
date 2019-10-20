package com.example.noteyourday.DayAdapters;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyEventArrayAdapter extends ArrayAdapter {
    private String[] dayEvents;
    private Context dayContext;

    public MyEventArrayAdapter( Context dayContext, int resource, String[] dayEvents) {
        super(dayContext, resource);
        this.dayEvents = dayEvents;
        this.dayContext = dayContext;
    }



    @Override
    public int getCount() {
        return dayEvents.length;
    }

    @Nullable
    @Override
    public Object getItem(int position) {
       String event=dayEvents[position];

       return String.format(event);
    }
}
