package com.nje.mobileszkozokprojekt.fragment.budgeting;

import static java.lang.Double.parseDouble;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.nje.mobileszkozokprojekt.R;
import com.nje.mobileszkozokprojekt.data.entity.BudgetingEntity;
import com.nje.mobileszkozokprojekt.data.repository.interfaces.IRepository;
import com.nje.mobileszkozokprojekt.model.Direction;
import com.nje.mobileszkozokprojekt.model.budgeting.BudgetingItem;

import java.util.Arrays;
import java.util.List;

public class BudgetingViewAdapter extends RecyclerView.Adapter<BudgetingViewHolder> {

    private final List<BudgetingItem> budgetingItems;
    private final IRepository<BudgetingEntity> repository;
    private final View parentView;
    private final Context context;

    public BudgetingViewAdapter(
            List<BudgetingItem> budgetingItems,
            IRepository<BudgetingEntity> repository,
            View parentView,
            Context context
    ) {
        this.budgetingItems = budgetingItems;
        this.repository = repository;
        this.parentView = parentView;
        this.context = context;
    }

    @NonNull
    @Override
    public BudgetingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.list_row_budgeting, parent, false);
        return new BudgetingViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BudgetingViewHolder holder, int position) {
        BudgetingItem budgetingItem = budgetingItems.get(position);

        String name = budgetingItem.getName();
        String type = budgetingItem.getType().toString();
        String category = budgetingItem.getCategory().toString();
        String value = Double.toString(budgetingItem.getValue());

        holder.nameTextView.setText(name);
        holder.typeTextView.setText(type);
        holder.categoryTextView.setText(category);
        holder.valueTextView.setText(value);

        holder.deleteButton.setOnClickListener(v -> {
            BudgetingItem itemToRemove = budgetingItems.get(position);
            BudgetingEntity entityToRemove = repository.getById(String.valueOf(itemToRemove.getId()));
            repository.delete(entityToRemove);
            budgetingItems.remove(position);
            notifyItemRemoved(position);
        });

        holder.updateButton.setOnClickListener(v -> {
            BudgetingItem itemToUpdate = budgetingItems.get(position);

            TextInputEditText nameInputText = parentView.findViewById(R.id.budgetingNameTextInputEditText);
            Spinner categorySpinner = parentView.findViewById(R.id.budgetingCategorySpinner);
            EditText valueEditText = parentView.findViewById(R.id.budgetingValueEditTextNumberDecimal);
            ToggleButton typeToggleButton = parentView.findViewById(R.id.budgetingTypeToggleButton);

            nameInputText.setText(itemToUpdate.getName());

            String[] budgetingCategories = context.getResources().getStringArray(R.array.budgeting_category);
            int categorySpinnerPosition = Arrays.asList(budgetingCategories).indexOf(itemToUpdate.getCategory().toString());
            categorySpinner.setSelection(categorySpinnerPosition);

            valueEditText.setText(String.valueOf(itemToUpdate.getValue()));
            typeToggleButton.setChecked(itemToUpdate.getType() == Direction.INCOMING);
        });
    }

    @Override
    public int getItemCount() {
        return budgetingItems.size();
    }
}
