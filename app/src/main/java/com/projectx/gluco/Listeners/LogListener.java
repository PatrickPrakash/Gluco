package com.projectx.gluco.Listeners;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.projectx.gluco.Fragments.LogFragment;

/**
 * Created by Patrick Prakash on 08-Mar-18.
 */

public class LogListener implements RecyclerView.OnItemTouchListener {

    private LogFragment.ActivityListener activityListener;
    private GestureDetector gestureDetector;

    public LogListener(Context context, final RecyclerView recycleView, final LogFragment.ActivityListener activityListener) {

        this.activityListener = activityListener;
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View child = recycleView.findChildViewUnder(e.getX(), e.getY());
                if (child != null && activityListener != null) {
                    activityListener.onLongClick(child, recycleView.getChildAdapterPosition(child));
                }
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View child = rv.findChildViewUnder(e.getX(), e.getY());
        if ((child != null) && (activityListener != null) && gestureDetector.onTouchEvent(e)) {
            activityListener.onClick(child, rv.getChildAdapterPosition(child));
        }

        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
