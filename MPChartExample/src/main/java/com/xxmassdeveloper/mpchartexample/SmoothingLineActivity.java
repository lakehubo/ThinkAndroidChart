package com.xxmassdeveloper.mpchartexample;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.xxmassdeveloper.mpchartexample.custom.TimeValueFormatter;
import java.util.ArrayList;

public class SmoothingLineActivity extends AppCompatActivity {
    private LineChart chart;
    protected Typeface tfRegular;
    protected Typeface tfLight;
    private static final int MAX_SUM = 73;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smoothing_line);
        tfRegular = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
        tfLight = Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf");

        chart = findViewById(R.id.chart1);
//        chart.setViewPortOffsets(0, 0, 0, 0);

        // no description text
        chart.getDescription().setEnabled(false);

        // enable touch gestures
        chart.setTouchEnabled(true);

        // enable scaling and dragging
        chart.setDragEnabled(true);
        chart.setScaleEnabled(false);
        chart.zoom(3f, 1f, 0, 0);

        // if disabled, scaling can be done on x- and y-axis separately
        chart.setPinchZoom(false);

        chart.setDrawGridBackground(false);
        chart.setMaxHighlightDistance(600);

        XAxis x = chart.getXAxis();
//        x.setEnabled(true);
        x.setPosition(XAxis.XAxisPosition.BOTTOM);
        x.setLabelCount(8);
//        x.setCenterAxisLabels(true);
        x.setValueFormatter(new TimeValueFormatter());
        x.setDrawGridLines(false);
        x.setLabelXOffset(1f);
        x.setTextColor(Color.parseColor("#cbcbcb"));

        // 设置x轴的LimitLine，index是从0开始的
        for (int i = 0; i < MAX_SUM; i++) {
            LimitLine xLimitLine = new LimitLine(i + 0.0f);
            xLimitLine.setLineColor(Color.parseColor("#d8d8d8"));
            xLimitLine.enableDashedLine(12, 4, 0);
            if ((i % 3) == 0) {
                xLimitLine.setDrawLimitBottomLineBehindData(true);
            } else {
                xLimitLine.setDrawLimitBottomLineBehindData(false);
            }
            x.addLimitLine(xLimitLine);
        }


        YAxis y = chart.getAxisLeft();
        y.setTypeface(tfLight);
        y.setLabelCount(6);
        y.setAxisMinimum(0);
        y.setAxisMaximum(500);
//        y.setTextColor(Color.BLACK);
        y.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        y.setDrawGridLines(false);
        y.setTextColor(Color.parseColor("#d8d8d8"));
//        y.setAxisLineColor(Color.BLACK);

        for (int i = 0; i < 2; i++) {
            LimitLine yLimitLine = new LimitLine((i + 1) * 200f);
            yLimitLine.setLineColor(Color.parseColor("#ffa200"));
            y.addLimitLine(yLimitLine);
        }

        chart.getAxisRight().setEnabled(false);

        setData(MAX_SUM, 500);

        chart.getLegend().setEnabled(false);

        chart.animateXY(2000, 2000);

        // don't forget to refresh the drawing
        chart.invalidate();
    }

    private void setData(int count, float range) {

        ArrayList<Entry> values = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            float val = (float) (Math.random() * (50 + 1)) + 220;
            values.add(new Entry(i, val));
        }

        ArrayList<Entry> values2 = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            float val = (float) (Math.random() * (50 + 1)) + 300;
            values2.add(new Entry(i, val));
        }

        ArrayList<Entry> shadowV2 = new ArrayList<>();

        for (int i = 0; i < values2.size(); i++) {
            Entry entry = values2.get(i).copy();
            float y = entry.getY();
            entry.setY(y - 10f);
            shadowV2.add(entry);
        }

        ArrayList<Entry> shadowV = new ArrayList<>();

        for (int i = 0; i < values.size(); i++) {
            Entry entry = values.get(i).copy();
            float y = entry.getY();
            entry.setY(y - 10f);
            shadowV.add(entry);
        }

        LineDataSet set1, set2, shadowSet, shadowSet2;

        if (chart.getData() != null &&
                chart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) chart.getData().getDataSetByIndex(3);
            set2 = (LineDataSet) chart.getData().getDataSetByIndex(4);
            shadowSet = (LineDataSet) chart.getData().getDataSetByIndex(0);
            shadowSet2 = (LineDataSet) chart.getData().getDataSetByIndex(1);
            set1.setValues(values);
            set2.setValues(values2);
            shadowSet.setValues(shadowV);
            shadowSet2.setValues(shadowV2);
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();
        } else {
            shadowSet = new LineDataSet(shadowV, "DataSet 2");
            shadowSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
            shadowSet.setColor(Color.parseColor("#40000000"));
            shadowSet.setCubicIntensity(0.2f);
            shadowSet.setDrawCircles(false);
            shadowSet.setDrawFilled(false);
            shadowSet.setLineWidth(1.8f);
            shadowSet.setDrawShadow(true);
            shadowSet.setShadowColor(Color.parseColor("#ffffff"));
            shadowSet.setDrawHorizontalHighlightIndicator(false);

            shadowSet2 = new LineDataSet(shadowV2, "DataSet 2");
            shadowSet2.setMode(LineDataSet.Mode.CUBIC_BEZIER);
            shadowSet2.setColor(Color.parseColor("#40000000"));
            shadowSet2.setCubicIntensity(0.2f);
            shadowSet2.setDrawCircles(false);
            shadowSet2.setDrawFilled(false);
            shadowSet2.setLineWidth(1.8f);
            shadowSet2.setDrawShadow(true);
            shadowSet2.setShadowColor(Color.parseColor("#ffffff"));
            shadowSet2.setDrawHorizontalHighlightIndicator(false);

            // create a dataset and give it a type
            set1 = new LineDataSet(values, "DataSet 1");
            set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
            set1.setCubicIntensity(0.2f);
            set1.setDrawFilled(false);
            set1.setDrawCircles(false);
            set1.setLineWidth(1.8f);
            set1.setColor(Color.parseColor("#ffffa200"));
            set1.setDrawHorizontalHighlightIndicator(false);

            set2 = new LineDataSet(values2, "DataSet 2");
            set2.setMode(LineDataSet.Mode.CUBIC_BEZIER);
            set2.setCubicIntensity(0.2f);
            set2.setColor(Color.RED);
            set2.setDrawCircles(false);
            set2.setDrawFilled(false);
            set2.setLineWidth(1.8f);
            set2.setDrawHorizontalHighlightIndicator(false);

            // create a data object with the data sets
            LineData data = new LineData(shadowSet, shadowSet2, set1, set2);
            data.setValueTypeface(tfLight);
            data.setValueTextSize(9f);
            data.setDrawValues(false);

            // set data
            chart.setData(data);
        }
    }
}
