package com.example.noteyourday.UserI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;


import com.example.noteyourday.DayAdapters.FirebaseEventListAdapter;
import com.example.noteyourday.DayAdapters.FirebaseEventViewHolder;
import com.example.noteyourday.DiaryConstants;
import com.example.noteyourday.R;
import com.example.noteyourday.models.Event;
import com.example.noteyourday.util.ItemTouchHelperAdapter;
import com.example.noteyourday.util.OnStartDragListener;
import com.example.noteyourday.util.SimpleItemTouchHelperCallback;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SavedEventListActivity extends AppCompatActivity  implements OnStartDragListener{
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private OnStartDragListener dayOnStartDragListener;
    private ItemTouchHelper dayItemTouchHelper;
    private FirebaseEventListAdapter dayFirebaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_event_list);
        ButterKnife.bind(this);

        setUpFirebaseAdapter();
    }
    private void setUpFirebaseAdapter(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        Query dayQuery = FirebaseDatabase.getInstance()
                .getReference(DiaryConstants.FIREBASE_CHILD_EVENTS)
                .child(uid)
                .orderByChild(DiaryConstants.FIREBASE_QUERY_INDEX);

//        dayEventReference= FirebaseDatabase.getInstance().getReference(DiaryConstants.FIREBASE_CHILD_EVENTS).child(uid);
        FirebaseRecyclerOptions<Event> options =
                new FirebaseRecyclerOptions.Builder<Event>()
                        .setQuery(dayQuery, Event.class)
                        .build();

        dayFirebaseAdapter = new FirebaseEventListAdapter(options, dayQuery, this, this);


        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(dayFirebaseAdapter);
        mRecyclerView.setHasFixedSize(true);
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback((ItemTouchHelperAdapter) dayFirebaseAdapter);
        dayItemTouchHelper = new ItemTouchHelper(callback);
        dayItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        dayFirebaseAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(dayFirebaseAdapter!= null) {
            dayFirebaseAdapter.stopListening();
        }
        }
    public void onStartDrag(RecyclerView.ViewHolder viewHolder){
        dayItemTouchHelper.startDrag(viewHolder);
    }
    }

