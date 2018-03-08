package com.projectx.gluco.ViewHolders;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.projectx.gluco.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Patrick Prakash on 06-Mar-18.
 */

public class ActivityViewHolder extends RecyclerView.ViewHolder {

    public ActivityViewHolder(View itemview) {
        super(itemview);
    }

    public ActivityViewHolder(LayoutInflater inflater, ViewGroup container) {
        super(inflater.inflate(R.layout.fragment_activity_item, container, false));
        CardView cardView = itemView.findViewById(R.id.fragment_activity_item);
    }

    public void setImage(Context ctx, String image) {
        ImageView imageView = itemView.findViewById(R.id.activity_image);
        Picasso.with(ctx).load(image).into(imageView);
           /* Glide.with(itemView).load("https://www.creativecontrast.com/wp-content/uploads/2017/02/8A-Computer.jpg").into(imageView);*/

    }

    public void setHeading(String Heading) {
        TextView heading = itemView.findViewById(R.id.activity_heading);
        heading.setText(Heading);

    }


}
