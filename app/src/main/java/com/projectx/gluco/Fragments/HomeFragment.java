package com.projectx.gluco.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.projectx.gluco.DataModels.Gluco_value;
import com.projectx.gluco.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    TextView TipText, LastRead;
    DatabaseReference dref;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootview = inflater.inflate(R.layout.fragment_home, container, false);
        //Spinner date
        Spinner date_spinner = rootview.findViewById(R.id.date_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getActivity(), R.array.day_spinner, R.layout.spinner_layout);
        adapter.setDropDownViewResource(R.layout.spinner_layout);
        date_spinner.setAdapter(adapter);

        //Spinner medical
        Spinner med_spinner = rootview.findViewById(R.id.med_spinner);
        ArrayAdapter<CharSequence> med_adapter = ArrayAdapter.createFromResource(this.getActivity(), R.array.med_spinner, R.layout.spinner_layout);
        med_adapter.setDropDownViewResource(R.layout.spinner_layout);
        med_spinner.setAdapter(med_adapter);

        LastRead = rootview.findViewById(R.id.LastRead);
        TipText = rootview.findViewById(R.id.TipText);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String uid = user.getUid();
        dref = FirebaseDatabase.getInstance().getReference();

        Query query = dref.child("User_Readings").child(uid).child("Blood_Glucose").orderByKey().limitToLast(1);


        query.addValueEventListener(new ValueEventListener() {
            public static final String TAG = "LastValue";

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String concentration;
                Gluco_value gluco_value = new Gluco_value();
                dataSnapshot.getValue(Gluco_value.class);
                concentration = gluco_value.getConcentration();
                Log.v(TAG, "LastValue" + concentration);
                LastRead.setText(concentration);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return rootview;
    }

}
