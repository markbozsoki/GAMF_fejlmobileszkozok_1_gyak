package com.nje.mobileszkozokprojekt.fragment;

import static androidx.navigation.Navigation.findNavController;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.nje.mobileszkozokprojekt.R;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button acquiredNavButton = view.findViewById(R.id.acquiredNavButton);
        acquiredNavButton.setOnClickListener(v -> {
            findNavController(view).navigate(R.id.action_homeFragment_to_acquiredFragment);
        });

        Button budgetingNavButton = view.findViewById(R.id.budgetingNavButton);
        budgetingNavButton.setOnClickListener(v -> {
            findNavController(view).navigate(R.id.action_homeFragment_to_budgetingFragment);
        });

        Button upcomingNavButton = view.findViewById(R.id.upcomingNavButton);
        upcomingNavButton.setOnClickListener(v -> {
            findNavController(view).navigate(R.id.action_homeFragment_to_upcomingFragment);
        });

        /*
        Button estimationNavButton = view.findViewById(R.id.estimationNavButton);
        estimationNavButton.setOnClickListener(v -> {
            findNavController(view).navigate(R.id.action_homeFragment_to_estimationFragment);
        });
         */
    }
}
