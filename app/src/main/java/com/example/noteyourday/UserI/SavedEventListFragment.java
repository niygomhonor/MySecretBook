package com.example.noteyourday.UserI;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.noteyourday.DayAdapters.FirebaseEventListAdapter;
import com.example.noteyourday.DiaryConstants;
import com.example.noteyourday.R;
import com.example.noteyourday.models.Event;
import com.example.noteyourday.util.ItemTouchHelperAdapter;
import com.example.noteyourday.util.OnStartDragListener;
import com.example.noteyourday.util.SimpleItemTouchHelperCallback;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SavedEventListFragment extends Fragment implements OnStartDragListener {
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private ItemTouchHelper dayItemTouchHelper;
    private FirebaseEventListAdapter dayFirebaseAdapter;
    public SavedEventListFragment() {
        // Required empty public constructor
    }


    public static SavedEventListFragment newInstance(String param1, String param2) {
        SavedEventListFragment fragment = new SavedEventListFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_saved_event_list, container, false);
        ButterKnife.bind(this, view);
        setUpFirebaseAdapter();
        return view;

        // Inflate the layout for this fragment
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

        dayFirebaseAdapter = new FirebaseEventListAdapter(options, dayQuery,  this, getActivity());


        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(dayFirebaseAdapter);
        mRecyclerView.setHasFixedSize(true);
//        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback((ItemTouchHelperAdapter) dayFirebaseAdapter);
//        dayItemTouchHelper = new ItemTouchHelper(callback);
//        dayItemTouchHelper.attachToRecyclerView(mRecyclerView);

        dayFirebaseAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                dayFirebaseAdapter.notifyDataSetChanged();
            }
        });

    }
    // TODO: Rename method, update argument and hook method into UI event
    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        dayItemTouchHelper.startDrag(viewHolder);
    }

    @Override
    //method is now public
    public void onDestroy() {
        super.onDestroy();
        dayFirebaseAdapter.cleanup();
    }
}



