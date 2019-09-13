package com.xxmassdeveloper.mpchartexample;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.StackedValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.xxmassdeveloper.mpchartexample.custom.CustomStringValueFormatter;
import com.xxmassdeveloper.mpchartexample.custom.WeekValueFormatter;

import java.util.ArrayList;

public class VerticalBarActivity extends AppCompatActivity {

    private HorizontalBarChart chart;
    Typeface tfLight, tfRegular;
    private String[] labs = new String[]{"三楼", "一楼", "二楼", "地下室", "阁楼", "三楼", "一楼", "二楼", "地下室", "阁楼"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertical_bar);
        tfLight = Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf");
        tfRegular = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
        chart = findViewById(R.id.chart1);
        chart.setDrawBarShadow(false);

        chart.setDrawValueAboveBar(true);

        chart.getDescription().setEnabled(false);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        chart.setMaxVisibleValueCount(60);
        chart.setScaleEnabled(false);
        chart.zoom(1f, 0.8f, 0, 0);
        // scaling can now only be done on x- and y-axis separately
        chart.setPinchZoom(false);

        // draw shadows for each bar that show the maximum value
        // chart.setDrawBarShadow(true);

        chart.setDrawGridBackground(false);

        XAxis xl = chart.getXAxis();
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
        xl.setTypeface(tfLight);
        xl.setDrawAxisLine(false);
        xl.setDrawGridLines(false);
        xl.setGranularity(1f);
//        xl.setCenterAxisLabels(true);
        xl.setLabelCount(10);//每页显示的下标数量
        xl.setValueFormatter(new CustomStringValueFormatter(labs));

        YAxis yl = chart.getAxisLeft();
        yl.setEnabled(false);
        yl.setTypeface(tfLight);
        yl.setDrawAxisLine(false);
        yl.setDrawGridLines(false);
        yl.setAxisMinimum(0f); // this replaces setStartAtZero(true)
//        yl.setInverted(true);

        YAxis yr = chart.getAxisRight();
        yr.setEnabled(false);
        yr.setTypeface(tfLight);
        yr.setDrawAxisLine(false);
        yr.setDrawGridLines(false);
        yr.setAxisMinimum(0f); // this replaces setStartAtZero(true)
//        yr.setInverted(true);

        chart.setFitBars(true);
        chart.animateY(2500);

        // setting data
        setData(10, 10);

        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setFormSize(8f);
        l.setXEntrySpace(4f);
    }

    private void setData(int count, float range) {

        float barWidth = 7.5f;
        float spaceForBar = 10f;
        ArrayList<BarEntry> values = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            float val = (float) (Math.random() * range);
            values.add(new BarEntry(i * spaceForBar, val));
        }

        BarDataSet set1;

        if (chart.getData() != null &&
                chart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) chart.getData().getDataSetByIndex(0);
//            set1.setValueTextColor(Color.parseColor("#ffea6b0e"));
            set1.setValues(values);
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(values, "DataSet 1");
            set1.setColors(Color.parseColor("#ffea6b0e"));
            set1.setValueTextColor(Color.parseColor("#ffe56006"));
            set1.setDrawIcons(false);

            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);

            data.setValueTextSize(12f);
            data.setValueTypeface(tfLight);
            data.setBarWidth(barWidth);
            chart.setData(data);
        }
    }
}
