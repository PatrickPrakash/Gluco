package com.projectx.gluco.MainActivities;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.View;
import android.widget.ImageView;

import com.projectx.gluco.R;
import com.projectx.gluco.Readings.BloodGlucoActivity;

/**
 * Created by Patrick Prakash on 14-Feb-18.
 */

public class BottomSheet extends BottomSheetDialogFragment {
    public BottomSheet activity;
    Context context;
    private ImageView blood_read;

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View contentview = View.inflate(getContext(), R.layout.bottom_sheet,null);
        dialog.setContentView(contentview);

        blood_read = contentview.findViewById(R.id.blood_read);



        blood_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,BloodGlucoActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }
}

