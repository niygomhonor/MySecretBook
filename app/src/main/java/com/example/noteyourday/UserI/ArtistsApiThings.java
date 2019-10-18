package com.example.noteyourday.UserI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.example.noteyourday.DayAdapters.DairyPagerAdapter;
import com.example.noteyourday.R;
import com.example.noteyourday.models.Message;

import butterknife.BindView;
import butterknife.ButterKnife;



    public  class ArtistsApiThings extends AppCompatActivity {
        private Message artistsMessage;
        @BindView(R.id.recyclerView)
        RecyclerView dairyRecyclerView;
        @BindView(R.id.artistProgressBar)
        ProgressBar dairyProgressBar;
        @BindView(R.id.nameOfArtPage) TextView artistDetails;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_artists_api_things);

            ButterKnife.bind(this);
            Intent intent=getIntent();
            String artist=intent.getStringExtra("artist");

            artistDetails.setText(artist);
    //        MusixMatchApi mClient=MusixMatchClient.getClient();
    //        Call<ArtistOfMyDay> artCall=mClient.getArtist(artist,"artists");

    startActivity(intent);

        }


    //    private void showUnsuccessfulMessage() {
    //        dairyProgressBar.setVisibility(View.GONE);
    //    }
    //
    //
    //    private void showArtist() {
    //        dairyRecyclerView.setVisibility(View.VISIBLE);
    //    }
    //
    //    private void hideProgressBar() {
    //        dairyProgressBar.setVisibility(View.GONE);
    //    }
    }

