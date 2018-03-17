package com.projectx.gluco.ViewHolders;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.projectx.gluco.R;

public class BloodViewHolder extends RecyclerView.ViewHolder {

    CardView mCardview;
    TextView item_history_time, item_history_date, item_history_notes, item_history_reading, item_history_value;
    Context context;

    public BloodViewHolder(View itemView) {
        super(itemView);
    }


    public BloodViewHolder(LayoutInflater inflater, ViewGroup container) {
        super(inflater.inflate(R.layout.fragment_gluco_item, container, false));
        mCardview = itemView.findViewById(R.id.item_history);
    }

    public void setGConcentration(String concentration) {
        item_history_reading = itemView.findViewById(R.id.item_history_reading);
        item_history_value = itemView.findViewById(R.id.item_history_value);
        Integer new_concentration = Integer.parseInt(concentration);

        if (new_concentration < 70) {
            item_history_reading.setText(concentration);
            item_history_reading.setTextColor(Color.parseColor("#6f4eab"));
            item_history_value.setTextColor(Color.parseColor("#6f4eab"));

        } else if (new_concentration >= 70 && new_concentration <= 200) {

            item_history_reading.setText(concentration);
            item_history_reading.setTextColor(Color.parseColor("#749756"));
            item_history_value.setTextColor(Color.parseColor("#749756"));
        } else if (new_concentration > 200) {

            item_history_reading.setText(concentration);
            item_history_reading.setTextColor(Color.parseColor("#E86445"));
            item_history_value.setTextColor(Color.parseColor("#E86445"));
        }

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

    public void getContext(Context ctx) {
        ctx = context;
    }

}

