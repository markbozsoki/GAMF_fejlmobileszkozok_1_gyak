package com.nje.mobileszkozokprojekt.fragment.budgeting;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nje.mobileszkozokprojekt.R;

public class BudgetingViewHolder extends RecyclerView.ViewHolder {
    public TextView nameTextView;
    public TextView typeTextView;
    public TextView categoryTextView;
    public TextView valueTextView;
    public Button deleteButton;
    public Button updateButton;

    public BudgetingViewHolder(View itemView) {
        super(itemView);
        nameTextView = itemView.findViewById(R.id.budgetingNameTextView);
        typeTextView = itemView.findViewById(R.id.budgetingTypeTextView);
        categoryTextView = itemView.findViewById(R.id.budgetingCategoryTextView);
        valueTextView = itemView.findViewById(R.id.budgetingValueTextView);

        deleteButton = itemView.findViewById(R.id.budgetingDeleteButton);
        updateButton = itemView.findViewById(R.id.budgetingUpdateButton);
    }
}
