package com.projectx.gluco.Fragments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.support.design.widget.BottomSheetDialogFragment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.projectx.gluco.R;

import static com.projectx.gluco.Readings.BloodGlucoActivity.TAG;

/**
 * Created by Patrick Prakash on 07-Mar-18.
 */

public class BottomSheetRecycler extends BottomSheetDialogFragment {

    private TextView deletebtn;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;
    private int position;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View contentview = View.inflate(getContext(), R.layout.bottom_sheet_recycler, null);
        dialog.setContentView(contentview);
        deletebtn = contentview.findViewById(R.id.delete_btn);

        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void getPosition(int position) {
        this.position = position;
        Log.v(TAG, "Position:" + position);
    }

    public void getReference(DatabaseReference reference) {
        this.reference = reference;
    }
}
