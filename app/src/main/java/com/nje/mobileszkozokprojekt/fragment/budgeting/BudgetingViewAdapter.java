package com.nje.mobileszkozokprojekt.fragment.budgeting;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nje.mobileszkozokprojekt.R;
import com.nje.mobileszkozokprojekt.model.budgeting.BudgetingItem;

import java.util.List;

public class BudgetingViewAdapter extends RecyclerView.Adapter<BudgetingViewHolder> {

    private final List<BudgetingItem> budgetingItems;

    public BudgetingViewAdapter(
            List<BudgetingItem> budgetingItems
    ) {
        this.budgetingItems = budgetingItems;
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
    }

    @Override
    public int getItemCount() {
        return budgetingItems.size();
    }
}
