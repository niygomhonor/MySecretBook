package com.example.noteyourday.UserI;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.noteyourday.DiaryConstants;
import com.example.noteyourday.R;
import com.example.noteyourday.models.Event;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class EventDetailsFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.timeTextView)
    TextView beginningTime;
    @BindView(R.id.descTextView)
    TextView eventDescription;
    @BindView(R.id.addressTextView)
    TextView eventAddress;
    @BindView(R.id.saveDayEventButton)
    TextView dayEventButton;
    @BindView(R.id.websiteTextView)
    TextView eventWeb;
    @BindView(R.id.eventImageView)
    ImageView eventImage;
    @BindView(R.id.eventNameTextView)
    TextView eventName;
    private static final int REQUEST_IMAGE_CAPTURE = 111;
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
        dayEvent = Parcels.unwrap(getArguments().getParcelable("event"));
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_photos, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_photo:
                onLaunchCamera();
            default:
                break;
        }
        return false;
    }

    private void onLaunchCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == getActivity().RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            eventImage.setImageBitmap(imageBitmap);
            encodeBitmapAndSaveToFirebase(imageBitmap);
        }
    }

    public void encodeBitmapAndSaveToFirebase(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        String imageEncoded = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference(DiaryConstants.FIREBASE_CHILD_EVENTS)
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child(dayEvent.getPushId())
                .child("imageUrl");
        ref.setValue(imageEncoded);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_details, container, false);
        ButterKnife.bind(this, view);
        if (!dayEvent.getImageUrl().contains("http")) {
            Bitmap image = decodeFromFirebaseBase64(dayEvent.getImageUrl());
            eventImage.setImageBitmap(image);
        } else {
            // This block of code should already exist, we're just moving it to the 'else' statement:
            Picasso.get()
                    .load(dayEvent.getImageUrl())
                    .into(eventImage);
        }
        eventImage.setOnClickListener(this);
        eventDescription.setOnClickListener(this);
        beginningTime.setOnClickListener(this);
        dayEventButton.setOnClickListener(this);
        Picasso.get().load(dayEvent.getImageUrl()).into(eventImage);
        eventName.setText(dayEvent.getName());
        eventDescription.setText(dayEvent.getDescription());
//        eventAddress.setText(dayEvent.getLocation().toString());
        beginningTime.setText(dayEvent.getTimeStart());
        return view;
    }

    private Bitmap decodeFromFirebaseBase64(String imageUrl) {
        byte[] decodedByteArray = android.util.Base64.decode(imageUrl, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.length);
    }


    @Override
    public void onClick(View v) {
        if (v == eventDescription) {

            Intent mapIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("The ending time:" + dayEvent.getTimeEnd()));
            Uri.parse("More info:" + dayEvent.getEventSiteUrl());
            startActivity(mapIntent);
        }
        if (v == eventAddress) {

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
            String name = dayEvent.getName();
            eventRef.orderByChild("name").equalTo(name).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        Toast.makeText(getContext(), "Currently Selected Restaurant already exists", Toast.LENGTH_LONG).show();
                        return;
                    } else {
                        DatabaseReference pushRef = eventRef.push();
                        String pushId = pushRef.getKey();
                        dayEvent.setPushId(pushId);
                        pushRef.setValue(dayEvent);
//              eventRef.push().setValue(dayEvent);
                        Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
                        System.out.println("Jesus love you");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {


                }

            });
        }

    }
}



