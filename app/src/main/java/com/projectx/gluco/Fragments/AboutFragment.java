package com.projectx.gluco.Fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.projectx.gluco.Authentication.MainAuthActivity;
import com.projectx.gluco.R;


/**
 * A simple {@link Fragment} subclass.
 */

public class AboutFragment extends Fragment implements View.OnClickListener {
    Context context;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthstateListener;

    public AboutFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }

    @Override
    public void onDestroy() {
        //   MainAuthActivity auth = new MainAuthActivity();
        // auth.signOut();
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_about, container, false);

        //Get a reference to the layout
        Button msignout = rootview.findViewById(R.id.msignout);
        msignout.setOnClickListener(this);
        return rootview;

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.msignout:
                MainAuthActivity auth = new MainAuthActivity();
                auth.signOut();
                Intent i = new Intent(context, MainAuthActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
                break;
            default:
                break;
        }
    }
}