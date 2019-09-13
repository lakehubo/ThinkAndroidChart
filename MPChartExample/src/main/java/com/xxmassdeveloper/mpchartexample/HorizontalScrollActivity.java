package com.xxmassdeveloper.mpchartexample;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.StackedValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.xxmassdeveloper.mpchartexample.custom.MyValueFormatter;
import com.xxmassdeveloper.mpchartexample.custom.SimpleMarkerView;
import com.xxmassdeveloper.mpchartexample.custom.WeekValueFormatter;
import java.util.ArrayList;

public class HorizontalScrollActivity extends AppCompatActivity {

    private BarChart chart;
    private static final int MAX_SUM = 21;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal_scroll);
        chart = findViewById(R.id.mBarChart);
//        chart.setOnChartValueSelectedListener(this);

        chart.getDescription().setEnabled(false);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        chart.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        chart.setPinchZoom(false);
        chart.setScaleEnabled(false);
        chart.setDrawGridBackground(false);
        chart.setDrawBarShadow(false);
        chart.setDragEnabled(true);

        chart.setDrawValueAboveBar(false);//数据位置是否在柱状体上方
        chart.setHighlightFullBarEnabled(false);

        chart.zoom(3f, 0.7f, 0, 0);//横向放大

        // change the position of the y-labels
        YAxis leftAxis = chart.getAxisLeft();
        MyValueFormatter valueFormatter = new MyValueFormatter("");
        leftAxis.setValueFormatter(valueFormatter);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
        leftAxis.setDrawGridLines(false);
        chart.getAxisRight().setEnabled(false);

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawLabels(true);
        xAxis.setDrawGridLines(false);
        xAxis.setLabelCount(7);
        xAxis.setValueFormatter(new WeekValueFormatter());

        // 设置x轴的LimitLine，index是从0开始的
        for (int i = 0; i < MAX_SUM; i++) {
            LimitLine xLimitLine = new LimitLine(i + 0.5f);
            xLimitLine.setLineColor(Color.parseColor("#d8d8d8"));
            if (((i + 1) % (MAX_SUM / 3)) != 0)
                xLimitLine.enableDashedLine(12, 4, 0);
            else
                xLimitLine.disableDashedLine();
            xAxis.addLimitLine(xLimitLine);
        }


        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setFormSize(8f);
        l.setFormToTextSpace(4f);
        l.setXEntrySpace(6f);

        ArrayList<BarEntry> values = new ArrayList<>();

        for (int i = 0; i < MAX_SUM; i++) {
            int mul = 250;
            int val1 = (int) (Math.random() * mul) + mul;
            values.add(new BarEntry(i, val1));
        }

        BarDataSet set1;//数据设置

        if (chart.getData() != null &&
                chart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) chart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(values, "Statistics Vienna 2014");
            set1.setDrawIcons(false);
            set1.setColors(Color.parseColor("#ffea6b0e"));

            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
//            data.setValueFormatter(new StackedValueFormatter(false, "", 1));
            data.setDrawValues(false);
            data.setBarWidth(0.6f);

            chart.setData(data);
        }

        SimpleMarkerView mv = new SimpleMarkerView(this, valueFormatter);
        mv.setChartView(chart); // For bounds control
        chart.setMarker(mv); //
    }
}
