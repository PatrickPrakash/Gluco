package com.projectx.gluco.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.projectx.gluco.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LogFragment extends Fragment {
    RecyclerView recyclerView;

    public LogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // TODO Complete Recycler View and Card view Integrated with Firebase.
        View rootview = inflater.inflate(R.layout.fragment_log, container, false);

        recyclerView = rootview.findViewById(R.id.fragment_history_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new RecyclerViewAdapter());
        return rootview;
    }

    private class RecyclerViewHolder extends RecyclerView.ViewHolder

    {
        private CardView item_history;
        private TextView item_history_time, item_history_date, item_history_reading, item_history_notes;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
        }

        public RecyclerViewHolder(LayoutInflater inflater, ViewGroup container) {
            super(inflater.inflate(R.layout.fragment_history_item, container, false));
            item_history = itemView.findViewById(R.id.item_history);
            item_history_date = itemView.findViewById(R.id.item_history_date);
            item_history_time = itemView.findViewById(R.id.item_history_time);
            item_history_reading = itemView.findViewById(R.id.item_history_reading);
            item_history_notes = itemView.findViewById(R.id.item_history_notes);

        }
    }

    private class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {


        @Override
        public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new RecyclerViewHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(RecyclerViewHolder holder, int position) {

        }


        @Override
        public int getItemCount() {
            return 10;
        }
    }
}
