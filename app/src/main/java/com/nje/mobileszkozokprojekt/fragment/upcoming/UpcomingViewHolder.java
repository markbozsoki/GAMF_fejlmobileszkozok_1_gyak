package com.nje.mobileszkozokprojekt.fragment.upcoming;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nje.mobileszkozokprojekt.R;

public class UpcomingViewHolder extends RecyclerView.ViewHolder {
    public TextView nameTextView;
    public TextView typeTextView;
    public TextView valueTextView;
    public TextView dueDateTextView;

    public UpcomingViewHolder(View itemView) {
        super(itemView);
        nameTextView = itemView.findViewById(R.id.upcomingNameTextView);
        typeTextView = itemView.findViewById(R.id.upcomingTypeTextView);
        valueTextView = itemView.findViewById(R.id.upcomingValueTextView);
        dueDateTextView = itemView.findViewById(R.id.upcomingDueDateTextView);
    }
}
