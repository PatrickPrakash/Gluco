package com.projectx.gluco.MainActivities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.projectx.gluco.DataModels.Diet_model;
import com.projectx.gluco.R;
import com.projectx.gluco.ViewHolders.DietViewHolder;

public class DietActivity extends AppCompatActivity {
    RecyclerView dietRecycler;
    DatabaseReference databaseref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet);
        dietRecycler = findViewById(R.id.dietRecycler);
        databaseref = FirebaseDatabase.getInstance().getReference().child("Activities").child("DiabetesDiet").child("Diets");
        databaseref.keepSynced(true);

        final FirebaseRecyclerAdapter<Diet_model, DietViewHolder> FirebaseDietAdapter = new FirebaseRecyclerAdapter<Diet_model, DietViewHolder>(Diet_model.class, R.layout.activitydataitem, DietViewHolder.class, databaseref) {
            @Override
            protected void populateViewHolder(DietViewHolder viewHolder, Diet_model model, int position) {
                viewHolder.setHeading(model.getHeading());
                viewHolder.setDiet(model.getDiet());
            }
        };
        dietRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        dietRecycler.setAdapter(FirebaseDietAdapter);
        dietRecycler.setHasFixedSize(true);
        dietRecycler.setNestedScrollingEnabled(true);
    }
}
