package com.xxmassdeveloper.mpchartexample.custom;

import android.util.Log;

import com.github.mikephil.charting.formatter.ValueFormatter;

public class TimeValueFormatter extends ValueFormatter {

    public TimeValueFormatter() {
    }

    @Override
    public String getFormattedValue(float value) {
        Log.e("lake", "getFormattedValue: " + value);
        return ((int) value) % 24 + "时";
//        return super.getFormattedValue(value);
    }
}
