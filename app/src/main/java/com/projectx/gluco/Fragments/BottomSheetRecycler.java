package com.projectx.gluco.Fragments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.View;

import com.projectx.gluco.R;

/**
 * Created by Patrick Prakash on 07-Mar-18.
 */

public class BottomSheetRecycler extends BottomSheetDialogFragment {

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
    }
}
