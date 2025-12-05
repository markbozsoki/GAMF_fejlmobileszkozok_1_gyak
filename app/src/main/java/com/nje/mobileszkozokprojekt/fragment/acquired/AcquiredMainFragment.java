package com.nje.mobileszkozokprojekt.fragment.acquired;

import static java.lang.Double.parseDouble;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.google.android.material.textfield.TextInputEditText;
import com.nje.mobileszkozokprojekt.R;
import com.nje.mobileszkozokprojekt.data.entity.AcquiredEntity;
import com.nje.mobileszkozokprojekt.data.repository.interfaces.IRepository;
import com.nje.mobileszkozokprojekt.model.acquired.AcquiredItem;
import com.nje.mobileszkozokprojekt.model.acquired.CategoryProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AcquiredMainFragment extends Fragment {

    private TextInputEditText nameInputText;
    private Spinner categorySpinner;
    private EditText valueEditText;
    private ToggleButton typeToggleButton;

    List<AcquiredItem> items = new ArrayList<>();
    AcquiredViewAdapter adapter;

    @Inject
    IRepository<AcquiredEntity> acquiredRepository;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceBundle) {
        View view = inflater.inflate(R.layout.fragment_acquired_main, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.acquiredListRecyclerView);

        nameInputText = view.findViewById(R.id.acquiredNameTextInputEditText);
        categorySpinner = view.findViewById(R.id.acquiredCategorySpinner);
        valueEditText = view.findViewById(R.id.acquiredValueEditTextNumberDecimal);
        typeToggleButton = view.findViewById(R.id.acquiredTypeToggleButtonton);

        Button addItemButton = view.findViewById(R.id.acquiredAddItemButton);
        Button clearButton = view.findViewById(R.id.acquiredClearAllInputButton);

        addItemButton.setOnClickListener(v-> {
            AcquiredItem newItem = createNewEntity();
            items.add(newItem);
            adapter.notifyItemInserted(items.size() - 1);
            clearInputs();
            Toast.makeText(this.getActivity(), "\"" + newItem.getName() + "\" added!", Toast.LENGTH_SHORT).show();
        });

        clearButton.setOnClickListener(v-> {
            clearInputs();
        });

        List<AcquiredEntity> entities = acquiredRepository.getAll();
        for (AcquiredEntity entity : entities) {
            AcquiredItem acquiredItem = acquiredEntityToItem(entity);
            items.add(acquiredItem);
        }

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new AcquiredViewAdapter(items, acquiredRepository, view, getActivity());
        recyclerView.setAdapter(adapter);

        BarChart chart = view.findViewById(R.id.acquiredDiagram);
        if (chart != null) {
            setupChartAppearance(chart);

            List<String> labels = new ArrayList<>();
            List<BarEntry> entries = new ArrayList<>();
            List<Integer> colors = new ArrayList<>();

            final int green = Color.rgb(110, 190, 102);

            for (int i = 0; i < items.size(); i++) {
                AcquiredItem it = items.get(i);

                if (it.getType() == CategoryProvider.OUTGOING) {
                    continue;
                }

                labels.add(it.getName());

                float y = (float) it.getValue();
                entries.add(new BarEntry(labels.size() - 1, y));
                colors.add(green);
            }

            BarDataSet set = new BarDataSet(entries, "Values");
            set.setColors(colors);
            set.setValueTextColors(colors);

            BarData data = new BarData(set);
            data.setBarWidth(0.8f);
            data.setValueTextSize(12f);
            XAxis xAxis = chart.getXAxis();
            xAxis.setValueFormatter(new com.github.mikephil.charting.formatter.ValueFormatter() {
                @Override
                public String getAxisLabel(float value, AxisBase axis) {
                    int i = Math.max(0, Math.min((int) value, labels.size() - 1));
                    return labels.get(i);
                }
            });
            xAxis.setLabelCount(Math.min(labels.size(), 5), false);
            xAxis.setGranularity(1f);

            chart.setData(data);
            chart.invalidate();
        }

        return view;
    }

    private void setupChartAppearance(BarChart chart) {
        chart.setBackgroundColor(Color.WHITE);
        chart.setExtraTopOffset(-30f);
        chart.setExtraBottomOffset(10f);
        chart.setExtraLeftOffset(70f);
        chart.setExtraRightOffset(70f);

        chart.setDrawBarShadow(false);
        chart.setDrawValueAboveBar(true);
        chart.getDescription().setEnabled(false);
        chart.setPinchZoom(false);
        chart.setDrawGridBackground(false);
        chart.getLegend().setEnabled(false);

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setTextColor(Color.LTGRAY);
        xAxis.setTextSize(13f);
        xAxis.setCenterAxisLabels(false);

        YAxis left = chart.getAxisLeft();
        left.setDrawLabels(false);
        left.setSpaceTop(25f);
        left.setSpaceBottom(25f);
        left.setDrawAxisLine(false);
        left.setDrawGridLines(false);
        left.setDrawZeroLine(true);
        left.setZeroLineColor(Color.GRAY);
        left.setZeroLineWidth(0.7f);

        chart.getAxisRight().setEnabled(false);
    }

    private AcquiredItem acquiredEntityToItem(AcquiredEntity entity) {
        return new AcquiredItem(
                entity.getId(),
                entity.getName(),
                CategoryProvider.valueOf(entity.getType().toUpperCase()),
                CategoryProvider.valueOf(entity.getCategory().toUpperCase()),
                entity.getValue()
        );
    }

    private void clearInputs() {
        Objects.requireNonNull(nameInputText.getText()).clear();
        valueEditText.getText().clear();
        categorySpinner.setSelection(0);
        typeToggleButton.setChecked(false);
    }

    private AcquiredItem createNewEntity() {
        AcquiredEntity newEntity = new AcquiredEntity();

        newEntity.setName(Objects.requireNonNull(nameInputText.getText()).toString());
        newEntity.setCategory(categorySpinner.getSelectedItem().toString());

        String valueString = valueEditText.getText().toString();
        if (valueString.isBlank()) {
            newEntity.setValue(0);
        } else {
            newEntity.setValue(parseDouble(valueString));
        }

        newEntity.setType(typeToggleButton.isChecked() ? CategoryProvider.OUTGOING.toString() : CategoryProvider.ACQUIRED.toString());

        acquiredRepository.insert(newEntity);
        return acquiredEntityToItem(newEntity);
    }
}
