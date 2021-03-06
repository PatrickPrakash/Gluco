package com.projectx.gluco.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.projectx.gluco.DataModels.Activity_gluco;
import com.projectx.gluco.Listeners.OntouchListener;
import com.projectx.gluco.MainActivities.CalculatorActivity;
import com.projectx.gluco.MainActivities.DietActivity;
import com.projectx.gluco.MainActivities.ExeActivity;
import com.projectx.gluco.MainActivities.FruitsActivity;
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
        final FirebaseRecyclerAdapter<Activity_gluco, ActivityViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Activity_gluco, ActivityViewHolder>(Activity_gluco.class, R.layout.fragment_activity_item, ActivityViewHolder.class, dataref) {
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
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(firebaseRecyclerAdapter);
        recyclerView.addOnItemTouchListener(new OntouchListener(getActivity(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
               /* Toast.makeText(getActivity(), "Item Clicked", Toast.LENGTH_SHORT).show();*/
                if (position == 0) {
                    startActivity(new Intent(getActivity(), CalculatorActivity.class));
                } else if (position == 1) {
                    startActivity(new Intent(getActivity(), DietActivity.class));
                } else if (position == 2) {
                    startActivity(new Intent(getActivity(), ExeActivity.class));
                } else if (position == 3) {
                    startActivity(new Intent(getActivity(), FruitsActivity.class));
                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        return view;

    }

    public interface ClickListener {
        void onClick(View view, int position);
        void onLongClick(View view, int position);
    }

}
