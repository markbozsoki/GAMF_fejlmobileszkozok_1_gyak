package com.nje.mobileszkozokprojekt.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.nje.mobileszkozokprojekt.R;
import com.nje.mobileszkozokprojekt.data.entity.AcquiredEntity;
import com.nje.mobileszkozokprojekt.data.entity.BudgetingEntity;
import com.nje.mobileszkozokprojekt.data.entity.UpcomingEntity;
import com.nje.mobileszkozokprojekt.data.repository.interfaces.IRepository;

import java.util.List;

import javax.inject.Inject;

public class EstimationFragment extends Fragment {

    @Inject
    IRepository<AcquiredEntity> acquiredRepository;

    @Inject
    IRepository<BudgetingEntity> budgetingRepository;

    @Inject
    IRepository<UpcomingEntity> upcomingRepository;

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_estimation, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.estimationListRecyclerView);
        BarChart diagram = view.findViewById(R.id.estimationDiagram);

        return inflater.inflate(R.layout.fragment_estimation, container, false);
    }

    private double calculateOwnedAssetValue() {
        double ownedAssetValue = 0;
        List<AcquiredEntity> entities = acquiredRepository.getAll();
        for (AcquiredEntity entity : entities) {
            ownedAssetValue += entity.getValue();
        }
        return ownedAssetValue;
    }

    private double calculateGrowthRate() {
        double growthRate = 0;
        List<BudgetingEntity> entities = budgetingRepository.getAll();
        for (BudgetingEntity entity : entities) {
            growthRate += entity.getValue();
        }
        return growthRate;
    }
}
