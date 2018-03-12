package com.projectx.gluco.Fragments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.support.design.widget.BottomSheetDialogFragment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
    private String key;
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
        /*final Query dataquery = reference.orderByChild("").equalTo(key);*/
      /*  Log.v(TAG,"Reference"+dataquery);*/
        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /* reference.child(key).removeValue();*/
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser(); //To get uid
                String uid = user.getUid();
                reference = FirebaseDatabase.getInstance().getReference().child("User_Readings").child(uid).child("Blood_Glucose");

                Log.v(TAG, "Reference Value" + reference);
                Log.v(TAG, "Key value" + key);
            }
        });
    }

    public void setKey(String key) {
        this.key = key;
        Log.v(TAG, "Position:" + key);
    }

    public void setReference(DatabaseReference reference) {
        this.reference = reference;
    }
}
