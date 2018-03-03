package com.projectx.gluco.ViewHolders;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.projectx.gluco.R;

public class BloodViewHolder extends RecyclerView.ViewHolder {

    CardView mCardview;
    TextView item_history_time, item_history_date, item_history_notes, item_history_reading;

    public BloodViewHolder(View itemView) {
        super(itemView);
    }

    public BloodViewHolder(LayoutInflater inflater, ViewGroup container) {
        super(inflater.inflate(R.layout.fragment_gluco_item, container, false));
        mCardview = itemView.findViewById(R.id.item_history);

    }

    public void setGConcentration(String concentration) {
        item_history_reading = itemView.findViewById(R.id.item_history_reading);
        item_history_reading.setText(concentration);
    }

    public void setGDate(String date) {
        item_history_date = itemView.findViewById(R.id.item_history_date);
        item_history_date.setText(date);
    }

    public void setGTime(String time) {
        item_history_time = itemView.findViewById(R.id.item_history_time);
        item_history_time.setText(time);

    }

    public void setGNotes(String notes) {
        item_history_notes = itemView.findViewById(R.id.item_history_notes);
        item_history_notes.setText(notes);

    }
}
