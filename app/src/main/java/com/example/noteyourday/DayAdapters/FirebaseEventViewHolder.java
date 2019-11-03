package com.example.noteyourday.DayAdapters;


import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.noteyourday.DiaryConstants;
import com.example.noteyourday.R;
import com.example.noteyourday.UserI.EventDetailActivity;
import com.example.noteyourday.models.Event;
import com.example.noteyourday.util.ItemTouchHelperAdapter;
import com.example.noteyourday.util.ItemTouchHelperViewHolder;

import com.squareup.picasso.Picasso;


public class FirebaseEventViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {
    View mView;
    Context mContext;

    public ImageView eventImageView;
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;
    public FirebaseEventViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
//        itemView.setOnClickListener(this);
    }

    public void bindEvent(Event event) {
        eventImageView = mView.findViewById(R.id.eventImageView);
        TextView nameTextView = mView.findViewById(R.id.eventNameTextView);
        TextView startTime = mView.findViewById(R.id.timeTextView);
        TextView descOfEvent = mView.findViewById(R.id.descTextView);


        nameTextView.setText(event.getName());
//        startTime.setText(event.getTimeStart());
//        descOfEvent.setText(event.getDescription());
        Picasso.get().load(event.getImageUrl()).into(eventImageView);
        Toast.makeText(mContext, "Hey" + eventImageView, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemSelected() {
        Log.d("Animation", "onItemSelected");
//        itemView.animate()
//                .alpha(0.7f)
//                .scaleX(0.9f)
//                .scaleY(0.9f)
//                .setDuration(500);
        // we will add animations here
        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(mContext,
                R.animator.drag_scale_on);
        set.setTarget(itemView);
        set.start();
    }

    @Override
    public void onItemClear() {
        Log.d("Animation", "onItemClear");
//        itemView.animate()
//                .alpha(1f)
//                .scaleX(1f)
//                .scaleY(1f);
//        // we will add animations here
        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(mContext,
                R.animator.drag_scale_off);
        set.setTarget(itemView);
        set.start();
    }

}