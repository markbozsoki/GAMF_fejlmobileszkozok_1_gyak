package com.nje.mobileszkozokprojekt.fragment.acquired;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nje.mobileszkozokprojekt.R;
import com.nje.mobileszkozokprojekt.model.acquired.AcquiredItem;

import java.util.List;

public class AcquiredViewAdapter extends RecyclerView.Adapter<AcquiredViewHolder> {

    private final List<AcquiredItem> acquiredItems;

    public AcquiredViewAdapter(List<AcquiredItem> acquiredItems) {
        this.acquiredItems = acquiredItems;
    }

    @NonNull @Override
    public AcquiredViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_acquired, parent, false);
        return new AcquiredViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AcquiredViewHolder h, int position) {
        AcquiredItem it = acquiredItems.get(position);

        h.nameTextView.setText(it.getName());
        h.valueTextView.setText(String.valueOf(it.getValue()));
        h.categoryTextView.setText(it.getCategory().toString());
        h.typeTextView.setText(it.getType().toString());
    }

    @Override
    public int getItemCount() { return acquiredItems.size(); }
}

