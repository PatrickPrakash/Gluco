package com.projectx.gluco.ViewHolders;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.projectx.gluco.R;

public class HbA1cViewHolder extends RecyclerView.ViewHolder {

    CardView mCardview;
    TextView item_hba1c_time, item_hba1c_date, item_hba1c_notes, item_hba1c_reading;

    public HbA1cViewHolder(View itemView) {
        super(itemView);
    }

    public HbA1cViewHolder(LayoutInflater inflater, ViewGroup container) {
        super(inflater.inflate(R.layout.fragment_hba1c_item, container, false));
        mCardview = itemView.findViewById(R.id.item_hba1c);

    }

    public void setGConcentration(String concentration) {
        item_hba1c_reading = itemView.findViewById(R.id.item_hba1c_reading);
        item_hba1c_reading.setText(concentration);
    }

    public void setGDate(String date) {
        item_hba1c_date = itemView.findViewById(R.id.item_hba1c_date);
        item_hba1c_date.setText(date);
    }

    public void setGTime(String time) {
        item_hba1c_time = itemView.findViewById(R.id.item_hba1c_time);
        item_hba1c_time.setText(time);

    }

    public void setGNotes(String notes) {
        item_hba1c_notes = itemView.findViewById(R.id.item_hba1c_notes);
        item_hba1c_notes.setText(notes);

    }
}
