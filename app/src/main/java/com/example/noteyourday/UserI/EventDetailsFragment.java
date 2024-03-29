package com.example.noteyourday.UserI;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.noteyourday.DiaryConstants;
import com.example.noteyourday.R;
import com.example.noteyourday.models.Event;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;


public class EventDetailsFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.timeTextView)
    TextView beginningTime;
@BindView(R.id.descTextView) TextView eventDescription;
@BindView(R.id.addressTextView) TextView eventAddress;
@BindView(R.id.saveDayEventButton) TextView dayEventButton;
@BindView(R.id.websiteTextView) TextView eventWeb;
@BindView(R.id.eventImageView) ImageView eventImage;
@BindView(R.id.eventNameTextView) TextView eventName;

private Event dayEvent;

    public EventDetailsFragment() {
        // Required empty public constructor
    }



    public static EventDetailsFragment newInstance(Event event) {
        EventDetailsFragment eventDetailsFragment = new EventDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable("event", Parcels.wrap(event));
        eventDetailsFragment.setArguments(args);
        return eventDetailsFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    dayEvent=Parcels.unwrap(getArguments().getParcelable("event"));

        }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
  View view= inflater.inflate(R.layout.fragment_event_details, container, false);
        ButterKnife.bind(this, view);
        eventImage.setOnClickListener(this);
        eventDescription.setOnClickListener(this);
        beginningTime.setOnClickListener(this);
        dayEventButton.setOnClickListener(this);
        Picasso.get().load(dayEvent.getImageUrl()).into(eventImage);
        eventName.setText(dayEvent.getName());
        eventDescription.setText(dayEvent.getDescription());
//        eventAddress.setText(dayEvent.getLocation().toString());
        beginningTime.setText(dayEvent.getTimeStart());
        return  view;
    }



    @Override
    public void onClick(View v) {
if(v==eventDescription){

    Intent mapIntent=new Intent(Intent.ACTION_VIEW,
            Uri.parse("The ending time:"+dayEvent.getTimeEnd()));
    Uri.parse("More info:"+dayEvent.getEventSiteUrl());
    startActivity(mapIntent);
}
if(v==eventAddress){

    Intent mapIntent = new Intent(Intent.ACTION_VIEW,
            Uri.parse("geo:" + dayEvent.getLatitude()
                    + "," + dayEvent.getLongitude()
                    + "?q=(" + dayEvent.getName() + ")"));
    startActivity(mapIntent);
}

          if (v == dayEventButton) {
              FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
              String uid = user.getUid();

              DatabaseReference eventRef = FirebaseDatabase
                .getInstance()
                .getReference(DiaryConstants.FIREBASE_CHILD_EVENTS).child(uid);
              DatabaseReference pushRef = eventRef.push();
              String pushId = pushRef.getKey();
              dayEvent.setPushId(pushId);
              pushRef.setValue(dayEvent);
//              eventRef.push().setValue(dayEvent);
        Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        System.out.println("Jesus love you");
    }



    }
}



