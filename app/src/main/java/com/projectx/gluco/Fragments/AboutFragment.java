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
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.projectx.gluco.Authentication.MainAuthActivity;
import com.projectx.gluco.DataModels.Profile;
import com.projectx.gluco.R;


/**
 * A simple {@link Fragment} subclass.
 */

public class AboutFragment extends Fragment implements View.OnClickListener {
    Context context;
    private DatabaseReference mDatabase;
    private TextView name_txt,age_txt,gender_txt,medcon_txt;
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
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        name_txt = rootview.findViewById(R.id.name_txt);
        age_txt = rootview.findViewById(R.id.age_txt);
        medcon_txt = rootview.findViewById(R.id.medcon_txt);
        gender_txt = rootview.findViewById(R.id.gen_txt);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(uid);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String name,age,gender,medcon;
                Profile profile = dataSnapshot.getValue(Profile.class);
                name = profile.getName();
                age = profile.getAge();
                gender = profile.getGender();
                medcon = profile.getMedcon();

                name_txt.setText(name);
                age_txt.setText(age);
                gender_txt.setText(gender);
                medcon_txt.setText(medcon);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        mDatabase.keepSynced(true);


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