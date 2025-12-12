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
import com.github.mikephil.charting.data.BarEntry;
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
import java.util.Objects;

import javax.inject.Inject;

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

            barLabels.add(item.getFormatedDate());
        }

        adapter = new EstimationViewAdapter(items);
        recyclerView.setAdapter(adapter);

        return inflater.inflate(R.layout.fragment_estimation, container, false);
    }

    private double calculateOwnedAssetValue() {
        double ownedAssetValue = 0;
        List<AcquiredEntity> entities = acquiredRepository.getAll();
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
        List<BudgetingEntity> entities = budgetingRepository.getAll();
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
        return components[0] + month + components[2];
    }
}
