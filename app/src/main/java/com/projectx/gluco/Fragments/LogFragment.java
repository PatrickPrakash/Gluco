package com.projectx.gluco.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.projectx.gluco.DataModels.Modeli;
import com.projectx.gluco.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class LogFragment extends Fragment {
    RecyclerView recyclerView;
    DatabaseReference databaseReference, reference;
    FirebaseAuth firebaseAuth;

    public LogFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Inflate the Android Log fragment
        View rootView = inflater.inflate(R.layout.fragment_log, container, false);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser(); //To get uid
        String uid = user.getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        reference = databaseReference.child("User_Readings").child(uid).child("Blood_Glucose").child("06-02-2018");
        reference.keepSynced(true);
        recyclerView = rootView.findViewById(R.id.fragment_history_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Modeli, BloodViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Modeli, BloodViewHolder>(Modeli.class, R.layout.fragment_history_item, BloodViewHolder.class, reference) {
            public static final String TAG = "Fire";

            @Override
            protected void populateViewHolder(BloodViewHolder viewHolder, Modeli model, int position) {
                Log.v(TAG, "Value" + model.getDate());
                viewHolder.setGConcentration(model.getConcentration());
                viewHolder.setGDate(model.getDate());
                viewHolder.setGNotes(model.getNotes());
                viewHolder.setGTime(model.getTime());
            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);

    }

    public static class BloodViewHolder extends RecyclerView.ViewHolder {

        CardView mCardview;
        TextView item_history_time, item_history_date, item_history_notes, item_history_reading;

        public BloodViewHolder(View itemView) {
            super(itemView);
        }

        public BloodViewHolder(LayoutInflater inflater, ViewGroup container) {
            super(inflater.inflate(R.layout.fragment_history_item, container, false));
            mCardview = itemView.findViewById(R.id.item_history);

        }

        public void setGConcentration(String concentration) {
            item_history_reading = itemView.findViewById(R.id.item_history_reading);
            item_history_reading.setText(concentration);
        }

        public void setGDate(String date) {
            item_history_date = itemView.findViewById(R.id.item_history_date);
            item_history_date.setText(date);
        }

        public void setGTime(String time) {
            item_history_time = itemView.findViewById(R.id.item_history_time);
            item_history_time.setText(time);

        }

        public void setGNotes(String notes) {
            item_history_notes = itemView.findViewById(R.id.item_history_notes);
            item_history_notes.setText(notes);

        }
    }


}

