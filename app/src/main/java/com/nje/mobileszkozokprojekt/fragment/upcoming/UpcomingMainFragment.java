package com.nje.mobileszkozokprojekt.fragment.upcoming;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.google.android.material.textfield.TextInputEditText;
import com.nje.mobileszkozokprojekt.R;
import com.nje.mobileszkozokprojekt.data.entity.UpcomingEntity;
import com.nje.mobileszkozokprojekt.data.repository.interfaces.IRepository;
import com.nje.mobileszkozokprojekt.model.Direction;
import com.nje.mobileszkozokprojekt.model.upcoming.UpcomingItem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class UpcomingMainFragment extends Fragment {

    private TextInputEditText nameInputText;
    private TextInputEditText dueDateEditText;
    private EditText valueEditText;
    private ToggleButton typeToggleButton;
    private Button addItemButton;
    private Button clearButton;

    @Inject
    IRepository<UpcomingEntity> upcomingRepository;

    private final SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    private final SimpleDateFormat sdfDisplay = new SimpleDateFormat("MMM dd", Locale.getDefault());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_upcoming_main, container, false);

        nameInputText = view.findViewById(R.id.upcomingNameTextInputEditText);
        dueDateEditText = view.findViewById(R.id.upcomingDueDateEditText);
        valueEditText = view.findViewById(R.id.upcomingValueEditTextNumberDecimal);
        typeToggleButton = view.findViewById(R.id.upcomingTypeToggleButton);

        addItemButton = view.findViewById(R.id.upcomingAddItemButton);
        clearButton = view.findViewById(R.id.upcomingClearAllInputButton);

        addItemButton.setOnClickListener(v -> {

        });

        clearButton.setOnClickListener(v -> {
            clearInputs();
        });

        final EditText dueDateEditText = view.findViewById(R.id.upcomingDueDateEditText);
        dueDateEditText.setFocusable(false);
        dueDateEditText.setFocusableInTouchMode(false);

        dueDateEditText.setOnClickListener(v -> {
            java.util.Calendar calendar = java.util.Calendar.getInstance();
            int year = calendar.get(java.util.Calendar.YEAR);
            int month = calendar.get(java.util.Calendar.MONTH);
            int day = calendar.get(java.util.Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    requireContext(),
                    (view1, selectedYear, selectedMonth, selectedDay) -> {
                        String date = selectedYear + "-" + (selectedMonth + 1) + "-" + selectedDay;
                        dueDateEditText.setText(date);
                    },
                    year, month, day
            );
            datePickerDialog.show();
        });

        List<UpcomingEntity> entities = upcomingRepository.getAll();
        if (entities == null || entities.isEmpty()) return view;

        List<UpcomingItem> items = new ArrayList<>();
        List<String> labels = new ArrayList<>();

        for (UpcomingEntity entity : entities) {
            items.add(new UpcomingItem(
                    entity.getName(),
                    Direction.valueOf(entity.getType().toUpperCase()),
                    entity.getValue(),
                    entity.getDueDate()
            ));
            labels.add(formatDate(entity.getDueDate()));
        }

        RecyclerView recyclerView = view.findViewById(R.id.upcomingListRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        UpcomingViewAdapter adapter = new UpcomingViewAdapter(items);
        recyclerView.setAdapter(adapter);

        BarChart chart = view.findViewById(R.id.upcomingDiagram);
        List<BarEntry> entries = new ArrayList<>();
        List<Integer> colors = new ArrayList<>();

        for (int i = 0; i < items.size(); i++) {
            UpcomingItem item = items.get(i);
            float value = (float) item.getValue();
            if (item.getType() == Direction.OUTGOING) value *= -1;

            BarEntry entry = new BarEntry(i, value);
            entry.setData(item);
            entries.add(entry);

            colors.add(item.getType() == Direction.INCOMING ?
                    ContextCompat.getColor(requireContext(), android.R.color.holo_green_dark) :
                    ContextCompat.getColor(requireContext(), android.R.color.holo_red_dark)
            );
        }

        BarDataSet dataSet = new BarDataSet(entries, "");
        dataSet.setColors(colors);
        dataSet.setValueTextSize(10f);
        dataSet.setDrawValues(true);
        dataSet.setValueFormatter(new NameValueBarFormatter());

        BarData data = new BarData(dataSet);
        data.setBarWidth(0.6f);

        chart.setData(data);
        chart.getDescription().setEnabled(false);
        chart.getLegend().setEnabled(false);

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new IndexAxisFormatter(labels));
        xAxis.setDrawGridLines(false);

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setAxisMinimum(-getMaxValue(items));
        leftAxis.setAxisMaximum(getMaxValue(items));
        leftAxis.setDrawGridLines(true);
        chart.getAxisRight().setEnabled(false);

        chart.animateY(1000);
        chart.invalidate();

        return view;
    }

    private void clearInputs(){
        Objects.requireNonNull(nameInputText.getText()).clear();
        Objects.requireNonNull(dueDateEditText.getText()).clear();
        valueEditText.getText().clear();
        typeToggleButton.setChecked(false);
    }

    private String formatDate(String dateStr) {
        try {
            Date date = sdfInput.parse(dateStr);
            assert date != null;
            return sdfDisplay.format(date);
        } catch (ParseException e) {
            return dateStr;
        }
    }

    private float getMaxValue(List<UpcomingItem> items) {
        float max = 0f;
        for (UpcomingItem item : items) {
            max = Math.max(max, (float)item.getValue());
        }
        return max * 1.2f;
    }

    private static class NameValueBarFormatter extends ValueFormatter {
        @Override
        public String getBarLabel(BarEntry barEntry) {
            Object data = barEntry.getData();
            if (data instanceof UpcomingItem) {
                UpcomingItem item = (UpcomingItem) data;
                return item.getName() + ": " + String.format(Locale.getDefault(), "%.2f", Math.abs(item.getValue()));
            }
            return String.format(Locale.getDefault(), "%.2f", Math.abs(barEntry.getY()));
        }
    }

    private static class IndexAxisFormatter extends ValueFormatter {
        private final List<String> labels;
        public IndexAxisFormatter(List<String> labels) { this.labels = labels; }
        @Override
        public String getFormattedValue(float value) {
            int index = (int) value;
            if (index >= 0 && index < labels.size()) return labels.get(index);
            return "";
        }
    }
}
