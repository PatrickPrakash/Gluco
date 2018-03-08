package com.projectx.gluco.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.projectx.gluco.DataModels.Activity_gluco;
import com.projectx.gluco.Listeners.OntouchListener;
import com.projectx.gluco.R;
import com.projectx.gluco.ViewHolders.ActivityViewHolder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActivityFragment extends Fragment {
    private RecyclerView recyclerView;
    private DatabaseReference dataref;

    public ActivityFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_activity, container, false);


        dataref = FirebaseDatabase.getInstance().getReference().child("Activities");
        dataref.keepSynced(true);
        FirebaseRecyclerAdapter<Activity_gluco, ActivityViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Activity_gluco, ActivityViewHolder>(Activity_gluco.class, R.layout.fragment_activity_item, ActivityViewHolder.class, dataref) {
            @Override
            protected void populateViewHolder(ActivityViewHolder viewHolder, Activity_gluco model, int position) {
                String heading = model.getHeading();
                String image = model.getImagelink();
                viewHolder.setHeading(heading);
                viewHolder.setImage(getContext(), image);
            }
        };

        recyclerView = view.findViewById(R.id.activity_recyclerview);
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(firebaseRecyclerAdapter);

        recyclerView.addOnItemTouchListener(new OntouchListener(getActivity(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(getActivity(), "Item Clicked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getActivity(), "Item clicked", Toast.LENGTH_SHORT).show();
            }
        }));
        return view;

    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

}
