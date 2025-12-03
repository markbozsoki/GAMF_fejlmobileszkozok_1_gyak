package com.nje.mobileszkozokprojekt.fragment.budgeting;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ToggleButton;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.android.material.textfield.TextInputEditText;
import com.nje.mobileszkozokprojekt.R;
import com.nje.mobileszkozokprojekt.data.entity.BudgetingEntity;
import com.nje.mobileszkozokprojekt.data.repository.interfaces.IRepository;
import com.nje.mobileszkozokprojekt.model.ColorProvider;
import com.nje.mobileszkozokprojekt.model.Direction;
import com.nje.mobileszkozokprojekt.model.budgeting.BudgetingItem;
import com.nje.mobileszkozokprojekt.model.budgeting.Category;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class BudgetingMainFragment extends Fragment {

    private TextInputEditText nameInputText;
    private Spinner categorySpinner;
    private EditText valueEditText;
    private ToggleButton typeToggleButton;
    private Button addItemButton;
    private Button clearButton;

    @Inject
    IRepository<BudgetingEntity> budgetingRepository;

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_budgeting_main, container, false);

        nameInputText = view.findViewById(R.id.budgetingNameTextInputEditText);
        categorySpinner = view.findViewById(R.id.budgetingCategorySpinner);
        valueEditText = view.findViewById(R.id.budgetingValueEditTextNumberDecimal);
        typeToggleButton = view.findViewById(R.id.budgetingTypeToggleButton);

        addItemButton = view.findViewById(R.id.budgetingAddItemButton);
        clearButton = view.findViewById(R.id.budgetingClearAllInputButton);

        addItemButton.setOnClickListener(v -> {

        });

        clearButton.setOnClickListener(v -> {
            clearInputs();
        });

        List<BudgetingEntity> entities = budgetingRepository.getAll();
        List<BudgetingItem> items = new ArrayList<>();
        for (BudgetingEntity entity : entities) {
            BudgetingItem budgetingItem = new BudgetingItem(
                    entity.getName(),
                    Direction.valueOf(entity.getType().toUpperCase()),
                    Category.valueOf(entity.getCategory().toUpperCase()),
                    entity.getValue()
            );
            items.add(budgetingItem);
        }

        RecyclerView recyclerView = view.findViewById(R.id.budgetingListRecyclerView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        BudgetingViewAdapter adapter = new BudgetingViewAdapter(items);
        recyclerView.setAdapter(adapter);


        double fullIncome = (double) 0;
        double fullCost = (double) 0;

        Map<String, Double> costs = new HashMap<>();
        for (BudgetingItem item : items) {
            Category itemCategory = item.getCategory();
            if (itemCategory == Category.INCOME) {
                fullIncome += item.getValue();
            } else {
                fullCost += item.getValue();
                costs.put(item.getName(), item.getValue());
            }
        }

        PieChart diagram = view.findViewById(R.id.budgetingDiagram);
        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        for (String key: costs.keySet()) {
            pieEntries.add(new PieEntry(Objects.requireNonNull(costs.get(key)).floatValue(), key));
        }

        PieDataSet pieDataSet = new PieDataSet(pieEntries, "");
        pieDataSet.setColors(ColorProvider.CHART_TEMPLATE());

        PieData pieData = new PieData(pieDataSet);
        pieData.setValueTextSize(10);
        pieData.setValueTypeface(Typeface.DEFAULT_BOLD);
        diagram.setData(pieData);

        diagram.setCenterText(fullIncome + " USD");
        diagram.setHoleRadius(50);
        if (fullCost >= fullIncome) {
            diagram.setHoleColor(Color.RED);
        } else {
            diagram.setHoleColor(Color.GREEN);
        }
        diagram.setDrawEntryLabels(false);

        diagram.getDescription().setEnabled(false);

        return view;
    }

    private void clearInputs(){
        Objects.requireNonNull(nameInputText.getText()).clear();
        valueEditText.getText().clear();
        categorySpinner.setSelection(0);
        String typeToggleButtonOffText = getResources().getString(R.string.cost_label);
        typeToggleButton.setText(typeToggleButtonOffText);
    }
}
