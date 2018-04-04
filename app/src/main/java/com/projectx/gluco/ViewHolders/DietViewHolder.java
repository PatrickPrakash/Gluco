package com.projectx.gluco.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.projectx.gluco.R;

/**
 * Created by Patrick Prakash on 18-Mar-18.
 */

public class DietViewHolder extends RecyclerView.ViewHolder {

    public DietViewHolder(View itemView) {
        super(itemView);
    }

    public DietViewHolder(LayoutInflater inflater, ViewGroup container) {
        super(inflater.inflate(R.layout.activitydataitem, container, false));
    }

    public void setHeading(String heading) {
        TextView textView = itemView.findViewById(R.id.headrecycler);
        textView.setText(heading);
    }

    public void setDiet(String diet) {
        TextView diettext = itemView.findViewById(R.id.datarecycler);
        diettext.setText(diet);
    }
}
