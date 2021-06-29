package com.yc.everylib.widget.chart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.yc.everylib.R;
import com.yc.everylib.widget.chart_data.ChartData;

import static com.yc.everylib.utils.YcEmptyKt.getNoEmpty;

/**
 * 首页考勤
 */
public class ChartLineMarkerView extends MarkerView {
    private final TextView mItem1Tv;
    private final TextView mItem2Tv;
    private ChartData mChartBarData;

    public ChartLineMarkerView(Context context, Chart chart) {
        super(context, R.layout.yc_chart_marker_view);
        mItem1Tv = findViewById(R.id.chartMakerItem1Tv);
        mItem2Tv = findViewById(R.id.chartMakerItem2Tv);
        setChartView(chart);
    }


    public void setChartBarData(ChartData chartBarData) {
        mChartBarData = chartBarData;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        if (mChartBarData != null) {
            int xIndex = (int) e.getX();
            mItem1Tv.setText("时间：" + getNoEmpty(mChartBarData.getXData().get(xIndex), "-"));
            mItem2Tv.setText("出勤人数：" + mChartBarData.getYData().get(xIndex).intValue() + " 人");
        }
        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }
}
