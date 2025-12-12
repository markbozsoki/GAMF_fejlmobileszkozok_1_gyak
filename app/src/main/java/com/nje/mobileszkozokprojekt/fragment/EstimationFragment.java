package com.nje.mobileszkozokprojekt.fragment;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.nje.mobileszkozokprojekt.R;
import com.nje.mobileszkozokprojekt.data.entity.AcquiredEntity;
import com.nje.mobileszkozokprojekt.data.entity.BudgetingEntity;
import com.nje.mobileszkozokprojekt.data.entity.UpcomingEntity;
import com.nje.mobileszkozokprojekt.data.repository.interfaces.IRepository;
import com.nje.mobileszkozokprojekt.model.estimation.EstimationItem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class EstimationFragment extends Fragment {

    @Inject
    IRepository<AcquiredEntity> acquiredRepository;

    @Inject
    IRepository<BudgetingEntity> budgetingRepository;

    @Inject
    IRepository<UpcomingEntity> upcomingRepository;

    List<EstimationItem> items = new ArrayList<>();
    EstimationViewAdapter adapter;

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_estimation, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.estimationListRecyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        BarChart diagram = view.findViewById(R.id.estimationDiagram);

        List<BarEntry> barEntries = new ArrayList<>();
        List<Integer> barColors = new ArrayList<>();
        List<String> barLabels = new ArrayList<>();

        double ownedAssetValue = calculateOwnedAssetValue();
        double growthRate = calculateGrowthRate();

        items = getBaseEstimationItems(ownedAssetValue, growthRate);


        for (int i = 0; i < items.size(); i++) {
            EstimationItem item = items.get(i);
            float value = (float) item.getValue();

            BarEntry entry = new BarEntry(i, value);
            entry.setData(item);
            barEntries.add(entry);

            barColors.add(item.getValue() > 0 ?
                    ContextCompat.getColor(requireContext(), android.R.color.holo_green_dark) :
                    ContextCompat.getColor(requireContext(), android.R.color.holo_red_dark)
            );

            barLabels.add(item.getMonthDate());
        }

        adapter = new EstimationViewAdapter(items);
        recyclerView.setAdapter(adapter);

        BarDataSet dataSet = new BarDataSet(barEntries, "");
        dataSet.setColors(barColors);
        dataSet.setValueTextSize(10f);
        dataSet.setDrawValues(true);
        dataSet.setValueFormatter(new NameValueBarFormatter());

        BarData data = new BarData(dataSet);
        data.setBarWidth(0.6f);

        diagram.setData(data);
        diagram.getDescription().setEnabled(false);
        diagram.getLegend().setEnabled(false);

        XAxis xAxis = diagram.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new IndexAxisFormatter(barLabels));
        xAxis.setDrawGridLines(false);

        YAxis leftAxis = diagram.getAxisLeft();
        leftAxis.setAxisMinimum(-getMaxValue(items));
        leftAxis.setAxisMaximum(getMaxValue(items));
        leftAxis.setDrawGridLines(true);
        diagram.getAxisRight().setEnabled(false);

        diagram.animateY(1000);
        diagram.invalidate();

        return view;
    }

    private double calculateOwnedAssetValue() {
        double ownedAssetValue = 0;
        List<AcquiredEntity> entities = this.acquiredRepository.getAll();
        for (AcquiredEntity entity : entities) {
            if (Objects.equals(entity.getCategory(), "outgoing")) {
                ownedAssetValue -= entity.getValue();
            } else {
                ownedAssetValue += entity.getValue();
            }
        }
        return ownedAssetValue;
    }

    private double calculateGrowthRate() {
        double growthRate = 0;
        List<BudgetingEntity> entities = this.budgetingRepository.getAll();
        for (BudgetingEntity entity : entities) {
            if (Objects.equals(entity.getCategory(), "outgoing")) {
                growthRate -= entity.getValue();
            } else {
                growthRate += entity.getValue();
            }
        }
        return growthRate;
    }

    private List<EstimationItem> getBaseEstimationItems(double ownedAssetValue, double growthRate) {
        List<EstimationItem> baseItems = new ArrayList<>();
        String currentDate = getCurrentDate();
        baseItems.add(getFirstEstimationItem(ownedAssetValue, currentDate));

        double currentValue = ownedAssetValue;
        for (int i = 1; i < 12; i++) {
            currentValue += growthRate;
            EstimationItem item = new EstimationItem(
                    currentValue,
                    incrementDate(currentDate, i)
            );
            baseItems.add(item);
        }

        return baseItems;
    }

    private EstimationItem getFirstEstimationItem(double ownedAssetValue, String date) {
        return new EstimationItem(
                ownedAssetValue,
                date
        );
    }

    private String getCurrentDate() {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(currentDate);
    }

    private String incrementDate(String baseDate, int increment) {
        String[] components = baseDate.split("-");
        int month = Integer.parseInt(components[1]) + increment;
        month = month % 12;
        return components[0] + month + components[2];
    }

    private float getMaxValue(List<EstimationItem> items) {
        float max = 0f;
        for (EstimationItem item : items) {
            max = Math.max(max, (float)item.getValue());
        }
        return max * 1.2f;
    }

    private static class NameValueBarFormatter extends ValueFormatter {
        @Override
        public String getBarLabel(BarEntry barEntry) {
            Object data = barEntry.getData();
            if (data instanceof EstimationItem) {
                EstimationItem item = (EstimationItem) data;
                return item.getMonthDate() + ": " + String.format(Locale.getDefault(), "%.2f", Math.abs(item.getValue()));
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
