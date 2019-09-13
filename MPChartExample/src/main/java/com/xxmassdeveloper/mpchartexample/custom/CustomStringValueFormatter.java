package com.xxmassdeveloper.mpchartexample.custom;

import android.util.Log;

import com.github.mikephil.charting.formatter.ValueFormatter;

public class CustomStringValueFormatter extends ValueFormatter {

    private String[] mValues;

    public CustomStringValueFormatter(String[] values) {
        this.mValues = values;
    }

    @Override
    public String getFormattedValue(float value) {
        Log.e("lake", "getFormattedValue: " + value);
        if ((int) value/10 > mValues.length)
            return super.getFormattedValue(value);
        return mValues[(int) value/10];
//        return super.getFormattedValue(value);
    }
}
