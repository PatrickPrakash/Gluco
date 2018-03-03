package com.projectx.gluco.ViewHolders;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.projectx.gluco.R;

public class PressureViewHolder extends RecyclerView.ViewHolder {

    CardView mCardview;
    TextView item_pressure_time, item_pressure_date, item_pressure_notes, item_pressure_max, item_pressure_min;

    public PressureViewHolder(View itemView) {
        super(itemView);
    }

    public PressureViewHolder(LayoutInflater inflater, ViewGroup container) {
        super(inflater.inflate(R.layout.fragment_pressure_item, container, false));
        mCardview = itemView.findViewById(R.id.item_pressure);

    }

    public void setGMax(String pressure_max) {
        item_pressure_max = itemView.findViewById(R.id.item_pressure_max);
        item_pressure_max.setText(pressure_max);
    }

    public void setGMin(String pressure_min) {
        item_pressure_min = itemView.findViewById(R.id.item_pressure_min);
        item_pressure_min.setText(pressure_min);
    }

    public void setGDate(String pressure_date) {
        item_pressure_date = itemView.findViewById(R.id.item_pressure_date);
        item_pressure_date.setText(pressure_date);
    }

    public void setGTime(String pressure_time) {
        item_pressure_time = itemView.findViewById(R.id.item_pressure_time);
        item_pressure_time.setText(pressure_time);

    }

    public void setGNotes(String pressure_notes) {
        item_pressure_notes = itemView.findViewById(R.id.item_pressure_notes);
        item_pressure_notes.setText(pressure_notes);

    }
}
