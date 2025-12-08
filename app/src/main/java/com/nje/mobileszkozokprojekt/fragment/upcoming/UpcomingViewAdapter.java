package com.nje.mobileszkozokprojekt.fragment.upcoming;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.nje.mobileszkozokprojekt.R;
import com.nje.mobileszkozokprojekt.data.entity.UpcomingEntity;
import com.nje.mobileszkozokprojekt.data.repository.interfaces.IRepository;
import com.nje.mobileszkozokprojekt.model.Direction;
import com.nje.mobileszkozokprojekt.model.upcoming.UpcomingItem;

import java.util.List;

public class UpcomingViewAdapter extends RecyclerView.Adapter<UpcomingViewHolder> {

    private final List<UpcomingItem> upcomingItems;
    private final IRepository<UpcomingEntity> repository;
    private final View parentView;

    public UpcomingViewAdapter(
            List<UpcomingItem> upcomingItems,
            IRepository<UpcomingEntity> repository,
            View parentView
    ) {
        this.upcomingItems = upcomingItems;
        this.repository = repository;
        this.parentView = parentView;
    }

    @NonNull
    @Override
    public UpcomingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.list_row_upcoming, parent, false);
        return new UpcomingViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull UpcomingViewHolder holder, int position) {
        UpcomingItem item = upcomingItems.get(position);

        holder.nameTextView.setText(item.getName());
        holder.typeTextView.setText(item.getType().toString());
        holder.valueTextView.setText(Double.toString(item.getValue()));
        holder.dueDateTextView.setText(item.getDueDate());

        holder.deleteButton.setOnClickListener(v -> {
            UpcomingItem itemToRemove = upcomingItems.get(position);
            UpcomingEntity entityToRemove = repository.getById(String.valueOf(itemToRemove.getId()));
            repository.delete(entityToRemove);
            upcomingItems.remove(position);
            notifyItemRemoved(position);
        });

        holder.updateButton.setOnClickListener(v -> {
            UpcomingItem itemToUpdate = upcomingItems.get(position);

            TextInputEditText nameInputText = parentView.findViewById(R.id.upcomingNameTextInputEditText);
            TextInputEditText dueDateEditText = parentView.findViewById(R.id.upcomingDueDateEditText);
            EditText valueEditText = parentView.findViewById(R.id.upcomingValueEditTextNumberDecimal);
            ToggleButton typeToggleButton = parentView.findViewById(R.id.upcomingTypeToggleButton);

            nameInputText.setText(itemToUpdate.getName());
            dueDateEditText.setText(itemToUpdate.getDueDate());
            valueEditText.setText(String.valueOf(itemToUpdate.getValue()));
            typeToggleButton.setChecked(itemToUpdate.getType() == Direction.INCOMING);
        });
    }

    @Override
    public int getItemCount() {
        return upcomingItems.size();
    }
}
