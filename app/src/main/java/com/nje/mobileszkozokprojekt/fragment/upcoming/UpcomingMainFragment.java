package com.nje.mobileszkozokprojekt.fragment.upcoming;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nje.mobileszkozokprojekt.R;
import com.nje.mobileszkozokprojekt.data.entity.UpcomingEntity;
import com.nje.mobileszkozokprojekt.data.repository.interfaces.IRepository;
import com.nje.mobileszkozokprojekt.model.upcoming.UpcomingItem;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class UpcomingMainFragment extends Fragment {

    @Inject
    IRepository<UpcomingEntity> upcomingRepository;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_upcoming_main, container, false);

        final EditText dueDateEditText = view.findViewById(R.id.upcomingDueDateEditText);
        dueDateEditText.setFocusable(false);
        dueDateEditText.setFocusableInTouchMode(false);
        dueDateEditText.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

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
        List<UpcomingItem> items = new ArrayList<>();
        for (UpcomingEntity entity : entities) {
            items.add(new UpcomingItem(entity));
        }

        RecyclerView recyclerView = view.findViewById(R.id.upcomingListRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        UpcomingViewAdapter adapter = new UpcomingViewAdapter(items);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
