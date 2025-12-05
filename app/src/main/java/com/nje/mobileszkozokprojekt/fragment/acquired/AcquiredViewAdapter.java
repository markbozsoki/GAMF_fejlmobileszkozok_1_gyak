package com.nje.mobileszkozokprojekt.fragment.acquired;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.nje.mobileszkozokprojekt.R;
import com.nje.mobileszkozokprojekt.data.entity.AcquiredEntity;
import com.nje.mobileszkozokprojekt.data.repository.interfaces.IRepository;
import com.nje.mobileszkozokprojekt.model.acquired.AcquiredItem;
import com.nje.mobileszkozokprojekt.model.acquired.CategoryProvider;

import java.util.Arrays;
import java.util.List;

public class AcquiredViewAdapter extends RecyclerView.Adapter<AcquiredViewHolder> {

    private final List<AcquiredItem> acquiredItems;
    private final IRepository<AcquiredEntity> repository;
    private final View parentView;
    private final Context context;

    public AcquiredViewAdapter(
            List<AcquiredItem> acquiredItems,
            IRepository<AcquiredEntity> repository,
            View parentView,
            Context context
    ) {
        this.acquiredItems = acquiredItems;
        this.repository = repository;
        this.parentView = parentView;
        this.context = context;
    }

    @NonNull @Override
    public AcquiredViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_acquired, parent, false);
        return new AcquiredViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AcquiredViewHolder holder, int position) {
        AcquiredItem acquiredItem = acquiredItems.get(position);

        String name = acquiredItem.getName();
        String type = acquiredItem.getType().toString();
        String category = acquiredItem.getCategory().toString();
        String value = Double.toString(acquiredItem.getValue());

        holder.nameTextView.setText(name);
        holder.valueTextView.setText(value);
        holder.categoryTextView.setText(category);
        holder.typeTextView.setText(type);

        holder.deleteButton.setOnClickListener(v-> {
            if (position == RecyclerView.NO_POSITION) return;
            AcquiredItem itemToRemove = acquiredItems.get(position);
            AcquiredEntity entityToRemove = repository.getById(String.valueOf(itemToRemove.getId()));
            if (entityToRemove != null) {
                repository.delete(entityToRemove);
            }
            acquiredItems.remove(position);
            notifyItemRemoved(position);
        });

        holder.updateButton.setOnClickListener(v-> {
            if (position == RecyclerView.NO_POSITION) return;
            AcquiredItem itemToUpdate = acquiredItems.get(position);

            TextInputEditText nameInputText = parentView.findViewById(R.id.acquiredNameTextInputEditText);
            Spinner categorySpinner = parentView.findViewById(R.id.acquiredCategorySpinner);
            EditText valueEditText = parentView.findViewById(R.id.acquiredValueEditTextNumberDecimal);
            ToggleButton typeToggleButton = parentView.findViewById(R.id.acquiredTypeToggleButtonton);

            nameInputText.setText(itemToUpdate.getName());

            String[] acquiredCategories = context.getResources().getStringArray(R.array.acquired_category);
            int categorySpinnerPosition = Arrays.asList(acquiredCategories).indexOf(itemToUpdate.getCategory().toString());
            categorySpinner.setSelection(categorySpinnerPosition);

            valueEditText.setText(String.valueOf(itemToUpdate.getValue()));
            typeToggleButton.setChecked(itemToUpdate.getType() == CategoryProvider.OUTGOING);
        });
    }

    @Override
    public int getItemCount() { return acquiredItems.size(); }
}
