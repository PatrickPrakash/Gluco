package com.projectx.gluco.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.projectx.gluco.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


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
        return rootview;
    }

}
