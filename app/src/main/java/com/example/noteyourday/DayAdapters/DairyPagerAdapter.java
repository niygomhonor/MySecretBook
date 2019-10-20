package com.example.noteyourday.DayAdapters;

import androidx.fragment.app.FragmentManager;

import com.example.noteyourday.models.Event;

import java.util.List;

public class DairyPagerAdapter {

    private List<Event> dayEvents;

    public DairyPagerAdapter (FragmentManager dayFm, List<Event> dayEvents) {
        this.dayEvents = dayEvents;
    }
}
