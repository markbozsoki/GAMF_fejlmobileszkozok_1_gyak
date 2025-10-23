package com.nje.mobileszkozokprojekt.fragment.budgeting;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nje.mobileszkozokprojekt.R;
import com.nje.mobileszkozokprojekt.data.FinDatabase;
import com.nje.mobileszkozokprojekt.data.entity.BudgetingEntity;
import com.nje.mobileszkozokprojekt.data.repository.BudgetingRepository;
import com.nje.mobileszkozokprojekt.data.repository.interfaces.IRepository;
import com.nje.mobileszkozokprojekt.model.budgeting.BudgetingItem;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class BudgetingMainFragment extends Fragment {

    @Inject
    IRepository<BudgetingEntity> budgetingRepository;

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_budgeting_main, container, false);

        List<BudgetingEntity> entities = budgetingRepository.getAll();
        List<BudgetingItem> items = Collections.emptyList();
        for (BudgetingEntity entity : entities) {
            items.add(new BudgetingItem(entity));
        }

        RecyclerView recyclerView = view.findViewById(R.id.budgetingListRecyclerView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        BudgetingViewAdapter adapter = new BudgetingViewAdapter(items);
        recyclerView.setAdapter(adapter);

        return view;
    }
}