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

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.projectx.gluco.R;

import java.util.ArrayList;
import java.util.List;

import static com.projectx.gluco.Readings.BloodGlucoActivity.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    TextView TipText, LastRead;
    DatabaseReference dref;
    LineChart lineChart;
    DatabaseReference mDataref;
    List<Entry> entries = new ArrayList<Entry>();
    String LastValue;
    int Last;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootview = inflater.inflate(R.layout.fragment_home, container, false);


        lineChart = rootview.findViewById(R.id.mgraph);
        LastRead = rootview.findViewById(R.id.LastRead);
        TipText = rootview.findViewById(R.id.TipText);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser(); //To get uid
        String uid = user.getUid();
        mDataref = FirebaseDatabase.getInstance().getReference().child("User_Readings").child(uid).child("Blood_Glucose");
        mDataref.keepSynced(true);

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

        mDataref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    final String dates = child.child("date").getValue().toString();
                    final ArrayList<String> datearray = new ArrayList<String>();
                    datearray.add(dates);
                   /* dates = dates.replace("-","/");*/
                    Float concentration = Float.parseFloat(child.child("concentration").getValue().toString());
                    /*Float date = Float.parseFloat(child.child("date").getValue().toString());*/
                    Log.v(TAG, "Concentration" + concentration);
                   /* Log.v(TAG, "Date" + date);*/

                    entries.add(new Entry(concentration, concentration));
                    LineDataSet dataSet = new LineDataSet(entries, dates);
                    LineData lineData = new LineData(dataSet);
                    lineChart.setData(lineData);
                    lineChart.invalidate(); // refresh

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //Last value Reading

        dref = FirebaseDatabase.getInstance().getReference().child("User_Readings").child(uid).child("Blood_Glucose");

        Query query = dref.orderByKey().limitToLast(1);

        query.addValueEventListener(new ValueEventListener() {
            public static final String TAG = "LastValue";

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    LastValue = child.child("concentration").getValue().toString();
                    LastRead.setText(LastValue);
                    Log.v(TAG, "The last value " + LastValue);
                    Last = Integer.parseInt(LastValue);
                    Log.v(TAG, "Integer value" + Last);
                    if (Last <= 200 && Last >= 70) {
                        TipText.setText("when eating out,eat the same portion sizes you would at home and take the leftovers go");
                    } else if (Last > 200) {
                        TipText.setText("Did you know that, Diabetes can be controlled with a proper Diet");
                    } else if (Last < 70) {
                        TipText.setText("Eat regular meals and snacks. Your meal plan is key to preventing hypoglycemia.");
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return rootview;
    }

}
