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
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.projectx.gluco.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    TextView TipText, LastRead;
    DatabaseReference dref;
    LineChart lineChart;
    DatabaseReference mDataref;

    /*chart*/
    ArrayList<GraphData> graphDataObjects = new ArrayList<GraphData>();
    List<Entry> entries = new ArrayList<Entry>();
    String LastValue;
    int Last;

    public HomeFragment() {
        // Required empty public constructor
    }

    public void updateLineChart() {
        lineChart.invalidate();
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


        //Spinner medical
        Spinner med_spinner = rootview.findViewById(R.id.med_spinner);
        ArrayAdapter<CharSequence> med_adapter = ArrayAdapter.createFromResource(this.getActivity(), R.array.med_spinner, R.layout.spinner_layout);
        med_adapter.setDropDownViewResource(R.layout.spinner_layout);
        med_spinner.setAdapter(med_adapter);

        mDataref = FirebaseDatabase.getInstance().getReference().child("User_Readings").child(uid).child("Blood_Glucose");
        mDataref.keepSynced(true);
        renderUI();

        return rootview;
    }


    private void renderUI() {
        Log.v("RENDER UI =-=-=-=-=:", "Caaled");

        try {
            mDataref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        String date = child.child("date").getValue().toString();
                        String concentration = child.child("concentration").getValue().toString();
                        graphDataObjects.add(new GraphData(date, concentration));
                    }

                    for (GraphData graphData : graphDataObjects) {
                        entries.add(new Entry(graphData.getDateX(), graphData.getConcentrationY()));
                    }
                    LineDataSet dataSet = new LineDataSet(entries, "Dates");
                    LineData lineData = new LineData(dataSet);
                    if (!entries.isEmpty()) {
                        lineChart.setData(lineData);
                    }
                    updateLineChart(); // refresh


                /*XAxis Data formatter*/

                    IAxisValueFormatter formatter = new IAxisValueFormatter() {

                        @Override
                        public String getFormattedValue(float date, AxisBase axis) {
                            String dateOnX = "Err";
                            try {
                                dateOnX = DateBuilder.getDate(date, new SimpleDateFormat("dd-MM-yy"));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            return dateOnX;
                        }
                    };
                    XAxis xAxis = lineChart.getXAxis();
                    xAxis.setGranularityEnabled(false);
                    xAxis.setValueFormatter(formatter);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        } catch (Exception e) {
            Toast.makeText(getActivity(), "Insert a new value", Toast.LENGTH_SHORT).show();
        }

        //Last value Reading
        try {

            Query query = mDataref.orderByKey().limitToLast(1);
            query.addValueEventListener(new ValueEventListener() {
                private static final String TAG = "LastValue";

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

                    Toast.makeText(getActivity(), "Insert a value", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Toast.makeText(getActivity(), "No last value detected", Toast.LENGTH_SHORT).show();
        }
    }
}
