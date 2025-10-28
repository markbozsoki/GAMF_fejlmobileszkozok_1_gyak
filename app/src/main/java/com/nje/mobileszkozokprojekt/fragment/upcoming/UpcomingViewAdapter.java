package com.nje.mobileszkozokprojekt.fragment.upcoming;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nje.mobileszkozokprojekt.R;
import com.nje.mobileszkozokprojekt.model.upcoming.UpcomingItem;

import java.util.List;

public class UpcomingViewAdapter extends RecyclerView.Adapter<UpcomingViewHolder> {

    private final List<UpcomingItem> upcomingItems;

    public UpcomingViewAdapter(List<UpcomingItem> upcomingItems) {
        this.upcomingItems = upcomingItems;
    }

    @NonNull
    @Override
    public UpcomingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.list_row_upcoming, parent, false);
        return new UpcomingViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UpcomingViewHolder holder, int position) {
        UpcomingItem item = upcomingItems.get(position);

        holder.nameTextView.setText(item.getName());
        holder.typeTextView.setText(item.getType().toString());
        holder.valueTextView.setText(Double.toString(item.getValue()));
        holder.dueDateTextView.setText(item.getDueDate());
    }

    @Override
    public int getItemCount() {
        return upcomingItems.size();
    }
}
