package com.xxmassdeveloper.mpchartexample.custom;

import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.xxmassdeveloper.mpchartexample.R;

public class SimpleMarkerView extends MarkerView {

    private final TextView tvDate;
    private final ValueFormatter xAxisValueFormatter;


    public SimpleMarkerView(Context context, ValueFormatter xAxisValueFormatter) {
        super(context, R.layout.simple_mark_view_layout);

        this.xAxisValueFormatter = xAxisValueFormatter;
        tvDate = findViewById(R.id.date);
    }

    // runs every time the MarkerView is redrawn, can be used to update the
    // content (user-interface)
    @Override
    public void refreshContent(Entry e, Highlight highlight) {

        tvDate.setText(String.format("%s月%s日\n%s年", "09", "13","2019"));

        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }
}