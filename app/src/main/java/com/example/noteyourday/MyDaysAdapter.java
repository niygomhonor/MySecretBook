package com.example.noteyourday;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.ArrayAdapter;
public class MyDaysAdapter extends ArrayAdapter {
    private Context dayContext;
    private  String[] dayFeel;

    public MyDaysAdapter( Context dayContext,  int resource,String[] dayFeel ) {
        super(dayContext, resource);
        this.dayContext = dayContext;
        this.dayFeel = dayFeel;

    }


    @Override
    public Object getItem(int index) {
        String feelings=dayFeel[index];

        return String.format("%s",feelings);
    }

    @Override
    public int getCount() {
        return dayFeel.length;
    }
}
