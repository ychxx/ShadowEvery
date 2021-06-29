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
import com.yc.everylib.widget.chart_data.ChartBarData;

import static com.yc.everylib.utils.YcEmptyKt.getNoEmpty;

/**
 * 首页工种分布柱状图的标记图层
 */
public class ChartBarMarkerView extends MarkerView {
    private TextView mItem1Tv;
    private TextView mItem2Tv;
    private TextView mItem3Tv;
    private ChartBarData mChartBarData;

    public ChartBarMarkerView(Context context, Chart chart) {
        super(context, R.layout.yc_chart_marker_view);
        mItem1Tv = findViewById(R.id.chartMakerItem1Tv);
        mItem2Tv = findViewById(R.id.chartMakerItem2Tv);
        mItem3Tv = findViewById(R.id.chartMakerItem3Tv);
        setChartView(chart);
    }


    public void setChartBarData(ChartBarData chartBarData) {
        mChartBarData = chartBarData;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        if (mChartBarData != null) {
            int xIndex = (int) e.getX();
            mItem1Tv.setText("工种：" + getNoEmpty(mChartBarData.getXData().get(xIndex), "-"));
            mItem2Tv.setText("未出勤：" + mChartBarData.getYData1().get(xIndex).intValue() + " 人");
            mItem3Tv.setText("已出勤：" + mChartBarData.getYData2().get(xIndex).intValue() + " 人");
        }
        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }
}
