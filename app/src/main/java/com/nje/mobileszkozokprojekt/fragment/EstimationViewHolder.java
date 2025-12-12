package com.nje.mobileszkozokprojekt.fragment;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nje.mobileszkozokprojekt.R;

public class EstimationViewHolder extends RecyclerView.ViewHolder {

    public TextView valueTextView;
    public TextView dateTextView;
    public EstimationViewHolder(@NonNull View itemView) {
        super(itemView);
        valueTextView = itemView.findViewById(R.id.estimationValueTextView);
        dateTextView = itemView.findViewById(R.id.estimationDateTextView);
    }
}
