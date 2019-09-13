package com.xxmassdeveloper.mpchartexample.custom;

import android.util.Log;

import com.github.mikephil.charting.formatter.ValueFormatter;

public class WeekValueFormatter extends ValueFormatter {

    public WeekValueFormatter() {
    }

    @Override
    public String getFormattedValue(float value) {
        Log.e("lake", "getFormattedValue: "+value);
        switch (((int)value+1)%7){
            case 1:
                return "周一";
            case 2:
                return "周二";
            case 3:
                return "周三";
            case 4:
                return "周四";
            case 5:
                return "周五";
            case 6:
                return "周六";
            case 0:
                return "周日";
        }
        return super.getFormattedValue(value);
    }
}
