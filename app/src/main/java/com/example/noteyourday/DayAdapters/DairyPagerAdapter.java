package com.example.noteyourday.DayAdapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.noteyourday.UserI.EventDetailsFragment;
import com.example.noteyourday.models.Event;

import java.util.List;

public class DairyPagerAdapter extends FragmentPagerAdapter {

    private List<Event> dayEvents;

    public DairyPagerAdapter(FragmentManager dayFm, List<Event> events) {
        super(dayFm);
        dayEvents = events;
    }

    @Override
    public int getCount() {
        return dayEvents.size();
    }
    @Override
    public Fragment getItem(int position) {
        return EventDetailsFragment.newInstance(dayEvents.get(position));
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return dayEvents.get(position).getName();
    }
}