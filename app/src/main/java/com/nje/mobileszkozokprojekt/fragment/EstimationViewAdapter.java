package com.nje.mobileszkozokprojekt.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.nje.mobileszkozokprojekt.R;
import com.nje.mobileszkozokprojekt.model.estimation.EstimationItem;

import java.util.List;

public class EstimationViewAdapter extends RecyclerView.Adapter<EstimationViewHolder> {

    private final List<EstimationItem> estimationItems;

    public EstimationViewAdapter(
            List<EstimationItem> estimationItems
    ) {
        this.estimationItems = estimationItems;
    }

    @NonNull
    @Override
    public EstimationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.list_row_estimation, parent, false);
        return new EstimationViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EstimationViewHolder holder, int position) {
        EstimationItem estimationItem = estimationItems.get(position);

        holder.valueTextView.setText(String.valueOf(estimationItem.getValue()));
        holder.dateTextView.setText(estimationItem.getMonthDate());
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
