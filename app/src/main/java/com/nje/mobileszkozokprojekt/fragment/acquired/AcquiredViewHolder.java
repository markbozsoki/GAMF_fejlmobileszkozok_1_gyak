package com.nje.mobileszkozokprojekt.fragment.acquired;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nje.mobileszkozokprojekt.R;

public class AcquiredViewHolder extends RecyclerView.ViewHolder {

    public TextView nameTextView;
    public TextView typeTextView;
    public TextView categoryTextView;
    public TextView valueTextView;

    public AcquiredViewHolder(View itemView) {
        super(itemView);

        nameTextView = itemView.findViewById(R.id.acquiredNameTextView);
        typeTextView = itemView.findViewById(R.id.acquiredTypeTextView);
        categoryTextView = itemView.findViewById(R.id.acquiredCategoryTextView);
        valueTextView = itemView.findViewById(R.id.acquiredValueTextView);
    }
}
