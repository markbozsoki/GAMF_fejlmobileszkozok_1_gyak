package com.nje.mobileszkozokprojekt.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nje.mobileszkozokprojekt.R;

public class TestFragment extends Fragment { // TODO: rename this to "EstimationFragment"

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_test, container, false);
    }
}
