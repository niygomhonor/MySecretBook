package com.example.noteyourday.UserI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import com.example.noteyourday.DayAdapters.DairyPagerAdapter;
import com.example.noteyourday.R;
import com.example.noteyourday.models.Event;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventDetailActivity extends AppCompatActivity {
    @BindView(R.id.viewPager) ViewPager eventViewPager;
    private DairyPagerAdapter adapterViewPager;
    ArrayList<Event> dayEvents=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        ButterKnife.bind(this);
dayEvents= Parcels.unwrap(getIntent().getParcelableExtra("events"));

int beginningPosition=getIntent().getIntExtra("position",0);

adapterViewPager=new DairyPagerAdapter(getSupportFragmentManager(),dayEvents);
eventViewPager.setAdapter(adapterViewPager);
eventViewPager.setCurrentItem(beginningPosition);
    }
}
