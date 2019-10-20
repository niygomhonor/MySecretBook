package com.example.noteyourday;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Parcel;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.noteyourday.models.Event;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;


public class EventDetailsFragment extends Fragment implements View.OnClickListener {

@BindView(R.id.descTextView) TextView eventDescription;
@BindView(R.id.addressTextView) TextView eventAddress;
@BindView(R.id.saveEventButton) Button eventButton;
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
        // Inflate the layout for this fragment
  View view= inflater.inflate(R.layout.fragment_artist_details, container, false);

        ButterKnife.bind(this, view);

        Picasso.get().load(dayEvent.getImageUrl()).into(eventImage);
        eventName.setText(dayEvent.getName());
        eventDescription.setText(dayEvent.getDescription());
        eventAddress.setText(dayEvent.getLocation().toString());

        return  view;
    }



    @Override
    public void onClick(View v) {
if(v==eventAddress){

    Intent mapIntent=new Intent(Intent.ACTION_VIEW,Uri.parse(dayEvent.getImageUrl()));
    startActivity(mapIntent);
}

    }
    @Override
    public void onDetach() {
        super.onDetach();

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
}

