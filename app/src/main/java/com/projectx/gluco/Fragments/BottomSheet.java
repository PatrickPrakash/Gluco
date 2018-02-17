package com.projectx.gluco.Fragments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.projectx.gluco.R;
import com.projectx.gluco.Readings.BloodGlucoActivity;
import com.projectx.gluco.Readings.BloodPressureActivity;
import com.projectx.gluco.Readings.Hba1cReadActivity;

/**
 * Created by Patrick Prakash on 14-Feb-18.
 */

public class BottomSheet extends BottomSheetDialogFragment implements View.OnClickListener {
    Context context;
    private ImageView blood_read;
    private ImageView pressure_read;
    private ImageView hba1c_read;
    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }

    @SuppressLint({"RestrictedApi"})
    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View contentview = View.inflate(getContext(), R.layout.bottom_sheet,null);
        dialog.setContentView(contentview);

        blood_read = contentview.findViewById(R.id.blood_read);
        pressure_read = contentview.findViewById(R.id.pressure_read);
        hba1c_read = contentview.findViewById(R.id.hba1c_read);


        //OnClickListeners
        blood_read.setOnClickListener(this);
        hba1c_read.setOnClickListener(this);
        pressure_read.setOnClickListener(this);
    }

    //OnClick Switch Statement

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.blood_read :
                Intent intent1 = new Intent(context,BloodGlucoActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent1);
                break;

            case R.id.pressure_read :
                Intent intent2 = new Intent(context,BloodPressureActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent2);
                break;


            case R.id.hba1c_read :
                Intent intent3 = new Intent(context,Hba1cReadActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent3);
                break;

            default:
                    break;

        }
    }
}

