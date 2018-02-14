package com.projectx.gluco.MainActivities;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.View;

import com.projectx.gluco.R;

/**
 * Created by Patrick Prakash on 14-Feb-18.
 */

public class BottomSheet extends BottomSheetDialogFragment {
    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View contentview = View.inflate(getContext(), R.layout.bottom_sheet,null);
        dialog.setContentView(contentview);

    }
}

