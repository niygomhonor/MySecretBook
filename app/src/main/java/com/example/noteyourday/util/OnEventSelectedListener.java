package com.example.noteyourday.util;

import com.example.noteyourday.models.Event;

import java.util.ArrayList;

public interface OnEventSelectedListener {
    public void onEventSelected(Integer position, ArrayList<Event> events);
}
