package com.nje.mobileszkozokprojekt.model;


import android.graphics.Color;

import androidx.core.content.ContextCompat;

import com.nje.mobileszkozokprojekt.R;

import java.util.ArrayList;

public class ColorProvider {

    public static ArrayList<Integer> MONOCHROMES() {
        ArrayList<Integer> monochromes = new ArrayList<>();
        monochromes.add(Color.BLACK);
        monochromes.add(Color.DKGRAY);
        monochromes.add(Color.GRAY);
        monochromes.add(Color.LTGRAY);
        monochromes.add(Color.WHITE);
        return monochromes;
    }

    public static ArrayList<Integer> RGB() {
        ArrayList<Integer> rgb = new ArrayList<>();
        rgb.add(Color.RED);
        rgb.add(Color.GREEN);
        rgb.add(Color.BLUE);
        return rgb;
    }

    public static ArrayList<Integer> CMY() {
        ArrayList<Integer> cmy = new ArrayList<>();
        cmy.add(Color.CYAN);
        cmy.add(Color.MAGENTA);
        cmy.add(Color.YELLOW);
        return cmy;
    }

    public static ArrayList<Integer> PRIMITIVES() {
        ArrayList<Integer> primitives = new ArrayList<>();
        primitives.addAll(RGB());
        primitives.addAll(CMY());
        return primitives;
    }

    public static ArrayList<Integer> ALL() {
        ArrayList<Integer> colors = new ArrayList<>();
        colors.addAll(MONOCHROMES());
        colors.addAll(RGB());
        colors.addAll(CMY());
        return colors;
    }

    public static ArrayList<Integer> CHART_TEMPLATE() {
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#E91E63"));
        colors.add(Color.parseColor("#9C27B0"));
        colors.add(Color.parseColor("#673AB7"));
        colors.add(Color.parseColor("#3F51B5"));
        colors.add(Color.parseColor("#2196F3"));
        colors.add(Color.parseColor("#00BCD4"));
        colors.add(Color.parseColor("#009688"));
        colors.add(Color.parseColor("#4CAF50"));
        colors.add(Color.parseColor("#FFEB3B"));
        colors.add(Color.parseColor("#FF9800"));
        colors.add(Color.parseColor("#FF5722"));

        return colors;
    }
}
