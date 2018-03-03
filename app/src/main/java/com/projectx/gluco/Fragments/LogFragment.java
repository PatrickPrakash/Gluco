package com.projectx.gluco.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.projectx.gluco.DataModels.Blood_gluco;
import com.projectx.gluco.DataModels.Hba1c_gluco;
import com.projectx.gluco.DataModels.Pressure_gluco;
import com.projectx.gluco.R;
import com.projectx.gluco.ViewHolders.BloodViewHolder;
import com.projectx.gluco.ViewHolders.HbA1cViewHolder;
import com.projectx.gluco.ViewHolders.PressureViewHolder;


/**
 * A simple {@link Fragment} subclass.
 */
public class LogFragment extends Fragment {
    RecyclerView recyclerView;
    DatabaseReference reference;
    FirebaseAuth firebaseAuth;
    Spinner history_spinner;
    public LogFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Inflate the Android Log fragment
        View rootView = inflater.inflate(R.layout.fragment_log, container, false);


        Spinner date_spinner = rootView.findViewById(R.id.history_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getActivity(), R.array.med_spinner, R.layout.spinner_layout);
        adapter.setDropDownViewResource(R.layout.spinner_layout);
        date_spinner.setAdapter(adapter);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser(); //To get uid
        String uid = user.getUid();

        date_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (id == 0) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser(); //To get uid
                    String uid = user.getUid();
                    reference = FirebaseDatabase.getInstance().getReference().child("User_Readings").child(uid).child("Blood_Glucose");
                    reference.keepSynced(true);
                    FirebaseRecyclerAdapter<Blood_gluco, BloodViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Blood_gluco, BloodViewHolder>(Blood_gluco.class, R.layout.fragment_gluco_item, BloodViewHolder.class, reference) {
                        public static final String TAG = "Fire";

                        @Override
                        protected void populateViewHolder(BloodViewHolder viewHolder, Blood_gluco model, int position) {
                            Log.v(TAG, "Value" + model.getDate());
                            Log.v(TAG, "Value of concentration" + model.getConcentration());
                            viewHolder.setGConcentration(model.getConcentration());
                            viewHolder.setGDate(model.getDate());
                            viewHolder.setGNotes(model.getNotes());
                            viewHolder.setGTime(model.getTime());
                        }
                    };
                    recyclerView.setAdapter(firebaseRecyclerAdapter);
                }

                if (id == 1) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser(); //To get uid
                    String uid = user.getUid();
                    reference = FirebaseDatabase.getInstance().getReference().child("User_Readings").child(uid).child("Hba1c_Readings");
                    reference.keepSynced(true);
                    FirebaseRecyclerAdapter<Hba1c_gluco, HbA1cViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Hba1c_gluco, HbA1cViewHolder>(Hba1c_gluco.class, R.layout.fragment_hba1c_item, HbA1cViewHolder.class, reference) {
                        public static final String TAG = "Fire";

                        @Override
                        protected void populateViewHolder(HbA1cViewHolder viewHolder, Hba1c_gluco model, int position) {
                            Log.v(TAG, "Value" + model.getHba1c_con());
                            Log.v(TAG, "Value of concentration" + model.getHba1c_con());
                            viewHolder.setGConcentration(model.getHba1c_con());
                            viewHolder.setGDate(model.getHba1c_date());
                            viewHolder.setGNotes(model.getHba1c_notes());
                            viewHolder.setGTime(model.getHba1c_time());
                        }
                    };
                    recyclerView.setAdapter(firebaseRecyclerAdapter);
                }
                if (id == 2) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser(); //To get uid
                    String uid = user.getUid();
                    reference = FirebaseDatabase.getInstance().getReference().child("User_Readings").child(uid).child("Pressure_Readings");
                    reference.keepSynced(true);
                    FirebaseRecyclerAdapter<Pressure_gluco, PressureViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Pressure_gluco, PressureViewHolder>(Pressure_gluco.class, R.layout.fragment_pressure_item, PressureViewHolder.class, reference) {
                        public static final String TAG = "Fire";

                        @Override
                        protected void populateViewHolder(PressureViewHolder viewHolder, Pressure_gluco model, int position) {
                            Log.v(TAG, "Value" + model.getPressure_max());
                            Log.v(TAG, "Value of date" + model.getPressure_date());
                            viewHolder.setGMax(model.getPressure_max());
                            viewHolder.setGMin(model.getPressure_min());
                            viewHolder.setGDate(model.getPressure_date());
                            viewHolder.setGNotes(model.getPressure_notes());
                            viewHolder.setGTime(model.getPressure_time());
                        }
                    };
                    recyclerView.setAdapter(firebaseRecyclerAdapter);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });

        /*reference = FirebaseDatabase.getInstance().getReference().child("User_Readings").child(uid).child("Blood_Glucose");
        reference.keepSynced(true);*/
        recyclerView = rootView.findViewById(R.id.fragment_history_recycler_view);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();


    }



}

