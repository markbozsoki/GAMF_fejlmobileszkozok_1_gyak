package com.nje.mobileszkozokprojekt.fragment.acquired;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import com.nje.mobileszkozokprojekt.R;
import com.nje.mobileszkozokprojekt.data.entity.AcquiredEntity;
import com.nje.mobileszkozokprojekt.data.repository.interfaces.IRepository;
import com.nje.mobileszkozokprojekt.model.acquired.AcquiredItem;
import com.nje.mobileszkozokprojekt.model.acquired.CategoryProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AcquiredMainFragment extends Fragment {

    @Inject
    IRepository<AcquiredEntity> acquiredRepository;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceBundle) {
        View view = inflater.inflate(R.layout.fragment_acquired_main, container, false);

        List<AcquiredEntity> entities = acquiredRepository.getAll();
        if (entities == null || entities.isEmpty()) return view;

        List<AcquiredItem> items = new ArrayList<>();
        for (AcquiredEntity entity : entities) {
            items.add(new AcquiredItem(
                    entity.getName(),
                    CategoryProvider.valueOf(entity.getType().toUpperCase(Locale.ROOT)),
                    CategoryProvider.valueOf(entity.getCategory().toUpperCase(Locale.ROOT)),
                    entity.getValue()
            ));
        }

        RecyclerView recyclerView = view.findViewById(R.id.acquiredListRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        AcquiredViewAdapter adapter = new AcquiredViewAdapter(items);
        recyclerView.setAdapter(adapter);

        BarChart chart = view.findViewById(R.id.acquiredDiagram);
        if (chart != null) {
            setupChartAppearance(chart);

            List<String> labels = new ArrayList<>();
            List<BarEntry> entries = new ArrayList<>();
            List<Integer> colors = new ArrayList<>();

            final int green = Color.rgb(110, 190, 102);
            final int red   = Color.rgb(211, 74, 88);

            for (int i = 0; i < items.size(); i++) {
                AcquiredItem it = items.get(i);
                labels.add(it.getName());

                float y = (float) it.getValue();
                if (it.getType() == CategoryProvider.OUTGOING) y = -Math.abs(y);

                entries.add(new BarEntry(i, y));
                colors.add(y >= 0 ? green : red);
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
}
