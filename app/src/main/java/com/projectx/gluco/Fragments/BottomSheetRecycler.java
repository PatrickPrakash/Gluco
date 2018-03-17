package com.projectx.gluco.Fragments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.BottomSheetDialogFragment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.projectx.gluco.DataModels.RecycleData;
import com.projectx.gluco.MainActivities.ConsoleActivity;
import com.projectx.gluco.R;

import java.util.ArrayList;
//import static com.projectx.gluco.Fragments.HomeFragment.lineChart;

/**
 * Created by Patrick Prakash on 07-Mar-18.
 */

public class BottomSheetRecycler extends BottomSheetDialogFragment {

    private TextView deletebtn;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        final View contentview = View.inflate(getContext(), R.layout.bottom_sheet_recycler, null);
        dialog.setContentView(contentview);
        deletebtn = contentview.findViewById(R.id.delete_btn);
        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> keyvalues = new ArrayList<>();
                String key = RecycleData.getKey();
                DatabaseReference ref = RecycleData.getReference();
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser(); //To get uid
                String uid = user.getUid();


                ref.removeValue();
                Log.v("BottomKey", "key" + key);
                Intent intent = new Intent(getActivity(), ConsoleActivity.class);
                startActivity(intent);
            }
        });
    }

}
