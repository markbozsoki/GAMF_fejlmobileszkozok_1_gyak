package com.nje.mobileszkozokprojekt.fragment;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


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
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull EstimationViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
