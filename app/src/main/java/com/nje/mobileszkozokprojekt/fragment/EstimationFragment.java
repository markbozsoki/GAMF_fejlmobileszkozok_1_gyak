package com.nje.mobileszkozokprojekt.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nje.mobileszkozokprojekt.R;
import com.nje.mobileszkozokprojekt.data.entity.AcquiredEntity;
import com.nje.mobileszkozokprojekt.data.entity.BudgetingEntity;
import com.nje.mobileszkozokprojekt.data.entity.UpcomingEntity;
import com.nje.mobileszkozokprojekt.data.repository.interfaces.IRepository;

import javax.inject.Inject;

public class EstimationFragment extends Fragment {

    @Inject
    IRepository<AcquiredEntity> acquiredRepository;

    @Inject
    IRepository<BudgetingEntity> budgetingRepository;

    @Inject
    IRepository<UpcomingEntity> upcomingRepository;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_estimation, container, false);
    }
}
