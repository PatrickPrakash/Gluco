package com.projectx.gluco.MainActivities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.projectx.gluco.R;

public class ExeActivity extends AppCompatActivity {
    RecyclerView exerciseRecycler;
    DatabaseReference dataref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exe);
        exerciseRecycler = findViewById(R.id.exerciseRecycler);

    }
}
