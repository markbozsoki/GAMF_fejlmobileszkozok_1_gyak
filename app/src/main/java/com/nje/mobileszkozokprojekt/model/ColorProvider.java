package com.nje.mobileszkozokprojekt.model;

import android.graphics.Color;

import com.nje.mobileszkozokprojekt.R;

import java.util.ArrayList;

public class ChartColorProvider {

    public static ArrayList<Integer> getAll() {
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor(String.valueOf(R.color.vermilion_bird)));
        colors.add(Color.parseColor(String.valueOf(R.color.mellow_melon)));
        colors.add(Color.parseColor(String.valueOf(R.color.pink_spyro)));
        colors.add(Color.parseColor(String.valueOf(R.color.purple_emperor)));
        colors.add(Color.parseColor(String.valueOf(R.color.night_in_manchester)));
        colors.add(Color.parseColor(String.valueOf(R.color.karimun_blue)));
        colors.add(Color.parseColor(String.valueOf(R.color.democrat)));
        colors.add(Color.parseColor(String.valueOf(R.color.maldives)));
        colors.add(Color.parseColor(String.valueOf(R.color.approval_green)));
        colors.add(Color.parseColor(String.valueOf(R.color.devils_grass)));
        colors.add(Color.parseColor(String.valueOf(R.color.greenivorous)));
        colors.add(Color.parseColor(String.valueOf(R.color.pac_man)));
        colors.add(Color.parseColor(String.valueOf(R.color.marigold)));
        colors.add(Color.parseColor(String.valueOf(R.color.vitamin_c)));
        colors.add(Color.parseColor(String.valueOf(R.color.smashing_pumpkins)));
        return colors;
    }
}
