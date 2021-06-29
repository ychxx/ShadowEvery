package com.yc.everylib.widget.chart;

import android.content.Context;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.util.Log;

import androidx.core.content.res.ResourcesCompat;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.yc.everylib.R;
import com.yc.everylib.utils.YcResources;
import com.yc.everylib.utils.YcUI;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ChartHelper {
    public static void init(LineChart lineChart, Context context) {
        lineChart.setNoDataText(context.getString(R.string.dataEmpty));
        lineChart.setNoDataTextColor(YcResources.getColor(R.color.chart_x_y_text_color));
        lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry entry, Highlight highlight) {
                lineChart.centerViewToAnimated(entry.getX(), entry.getY(), lineChart.getData().getDataSetByIndex(highlight.getDataSetIndex()).getAxisDependency(), 500);
            }

            @Override
            public void onNothingSelected() {
                Log.i("Nothing selected", "Nothing selected.");
            }
        });
        // 不使用描述文本相关信息
        lineChart.getDescription().setEnabled(false);
        lineChart.getDescription().setText("描述");
        // 手势能否触摸图表
        lineChart.setTouchEnabled(true);
        //减速摩擦系数[0,1]之间，0立刻停止，1，自动转换为0.999f
        lineChart.setDragDecelerationFrictionCoef(0.9f);

        // 将其设置为true以启用图表的拖动（用手指移动图表）（这不会影响缩放）。
        lineChart.setDragEnabled(true);
        //将此设置为true以绘制网格背景，否则为false
        lineChart.setDrawGridBackground(false);
        //将其设置为true以允许在完全缩小时拖动图表曲面时突出显示。 默认值：true
        lineChart.setHighlightPerDragEnabled(true);

        // 如果设置为true，则可以用2个手指同时缩放x和y轴，如果为false，则可以分别缩放x和y轴。 默认值：false
        lineChart.setPinchZoom(false);// 如果设置为true，则可以用2个手指同时缩放x和y轴，如果为false，则可以分别缩放x和y轴。 默认值：false
        lineChart.setScaleEnabled(false);//将其设置为true以在X轴和Y轴上为图表启用缩放（通过手势放大和缩小）（这不影响拖动）
        lineChart.setScaleXEnabled(false);
        lineChart.setScaleYEnabled(false);//禁止y轴放大和缩小
        // 设置背景颜色
        lineChart.setBackgroundResource(R.color.white);
        //使用指定的动画时间在x轴上动画显示图表。
//        lineChart.animateX(1500);
        //右侧y轴设置为不使用
        lineChart.getAxisRight().setEnabled(true);

        //右侧y轴设置为透明的
        lineChart.getAxisRight().setEnabled(false);
//        lineChart.getAxisRight().setTextColor(Color.TRANSPARENT);//y轴字的颜色
////        lineChart.getAxisRight().setAxisLineColor(Color.TRANSPARENT);
//        lineChart.getAxisRight().setSpaceTop(5f);//将顶部轴空间设置为整个范围的百分比。默认10f（即10%）
//        lineChart.getAxisRight().setDrawGridLines(true);    //将此设置为true，以便绘制该轴的网格线
//        lineChart.getAxisRight().setGranularityEnabled(false);  //在轴值间隔上启用/禁用粒度控制。
//        lineChart.getAxisRight().setXOffset(13f);
        //左侧侧y轴设置为透明的
        lineChart.getAxisLeft().setAxisLineColor(Color.TRANSPARENT);//y轴颜色透明
        lineChart.getAxisLeft().setAxisMinimum(0f); // y轴最小值
        lineChart.getAxisLeft().setTypeface(ResourcesCompat.getFont(context, R.font.dinmittelschriftstd));
        lineChart.getAxisLeft().setTextColor(YcResources.getColor(R.color.chart_x_y_text_color));//y轴字的颜色

        lineChart.getAxisLeft().setGridLineWidth(0.5f);
        lineChart.getAxisLeft().setGridColor(YcResources.getColor(R.color.chart_y_grid_color));
        lineChart.getAxisLeft().setGridDashedLine(new DashPathEffect(new float[]{10, 5}, 1));
        //x周相关设置
//        CustomAxisX formatter = new CustomAxisX(list);
//        xAxis.setTypeface(mTfLight);

        //将其设置为true以启用绘制该轴的网格线。
        lineChart.getXAxis().setDrawGridLines(false);
        //x轴在下方
        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        lineChart.getXAxis().setAxisMinimum(-0.5f);//从负0.5开始，使得左侧留一些空
        lineChart.getXAxis().setDrawAxisLine(false);
        lineChart.getXAxis().setGranularity(1f);//设置x轴间距
        lineChart.getXAxis().setLabelCount(12);//设置
        lineChart.getXAxis().setLabelRotationAngle(0);//文字倾斜
        lineChart.getXAxis().setTypeface(ResourcesCompat.getFont(context, R.font.dinmittelschriftstd));
        lineChart.getXAxis().setTextColor(YcResources.getColor(R.color.chart_x_y_text_color));//x轴字的颜色
        float textSize = context.getResources().getDimensionPixelSize(R.dimen.text_size_8);
        textSize = textSize / (YcUI.getDisplayMetrics().scaledDensity);
        lineChart.getXAxis().setTextSize(textSize);
        //
//        lineChart.getLegend().setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
//        lineChart.getLegend().setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
//        lineChart.getLegend().setDrawInside(true);
        lineChart.getLegend().setEnabled(false);//关闭显示label
        lineChart.animateXY(0, 0);
        lineChart.notifyDataSetChanged();
        lineChart.invalidate();//刷新
    }

    public static void setLineDataSet(LineChart lineChart, List<Double> dataList, int index) {
        ArrayList<Entry> entries = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++) {
            entries.add(new Entry(i, dataList.get(i).floatValue()));
        }
        LineDataSet dataSet;
        if (lineChart.getData() != null && lineChart.getData().getDataSetCount() > index) {
            dataSet = (LineDataSet) lineChart.getData().getDataSetByIndex(index);
            dataSet.setValues(entries);
        } else {
            dataSet = new LineDataSet(entries, "");
            dataSet.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
            dataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
//            set.setColor(color);//设置线的颜色
            dataSet.setLineWidth(1.5f);//设置线的宽度
            //顶点圆
            dataSet.setCircleRadius(4.5f);//设置顶点的半径
            dataSet.setDrawValues(false);//关闭顶点上方的文字
//            dataSet.setValueTextColor(YcResources.getColor(R.color.chart_value_text_color));
//            dataSet.setValueTypeface(ResourcesCompat.getFont(App.getInstance(), R.font.dinmittelschriftstd));
            dataSet.setCircleSize(2.5f);
            dataSet.setCircleColor(YcResources.getColor(R.color.chart_value_circle_color));//设置顶点的颜色,由于重新Renderer这边设置颜色可能失效
//            dataSet.setCubicIntensity(0.1f);
            //指引线
//            set.setHighlightEnabled(false);//关闭指引线宽度
//            set.setHighlightLineWidth(1f); //指引线宽度
            dataSet.setHighLightColor(YcResources.getColor(R.color.chart_high_light_color)); //指引线的颜色
            dataSet.enableDashedHighlightLine(10, 5, 1);
            //开启填充（关闭后会极大提升性能）
            dataSet.setDrawFilled(true);
            dataSet.setFillDrawable(YcResources.getDrawable(R.drawable.every_selector_chart_fill));
            dataSet.setFillAlpha(100);
            dataSet.setDrawHorizontalHighlightIndicator(false);
            lineChart.getLineData().addDataSet(dataSet);
        }
    }


    public static void init(BarChart barChart, Context context) {
        barChart.setNoDataText(context.getString(R.string.dataEmpty));
        barChart.setNoDataTextColor(YcResources.getColor(R.color.chart_x_y_text_color));
        barChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry entry, Highlight highlight) {
                barChart.centerViewToAnimated(entry.getX(), entry.getY(), barChart.getData().getDataSetByIndex(highlight.getDataSetIndex()).getAxisDependency(), 500);
            }

            @Override
            public void onNothingSelected() {
                Log.i("Nothing selected", "Nothing selected.");
            }
        });

        barChart.setPinchZoom(false);// 如果设置为true，则可以用2个手指同时缩放x和y轴，如果为false，则可以分别缩放x和y轴。 默认值：false
        barChart.setScaleEnabled(false);    //将其设置为true以在X轴和Y轴上为图表启用缩放（通过手势放大和缩小）（这不影响拖动）
        barChart.setScaleXEnabled(false);
        barChart.setScaleYEnabled(false);//禁止y轴放大和缩小
        barChart.setDoubleTapToZoomEnabled(false);//禁止双击屏幕缩放
        // 不使用描述文本相关信息
        barChart.getDescription().setEnabled(false);
        barChart.getDescription().setText("描述");
        // 手势能否触摸图表
        barChart.setTouchEnabled(true);
        //减速摩擦系数[0,1]之间，0立刻停止，1，自动转换为0.999f
        barChart.setDragDecelerationFrictionCoef(0.9f);
        // 将其设置为true以启用图表的拖动（用手指移动图表）（这不会影响缩放）。
        barChart.setDragEnabled(true);
        //将此设置为true以绘制网格背景，否则为false
        barChart.setDrawGridBackground(false);
        //将其设置为true以允许在完全缩小时拖动图表曲面时突出显示。 默认值：true
        barChart.setHighlightPerDragEnabled(true);

        // 设置背景颜色
        barChart.setBackgroundResource(R.color.white);
        //使用指定的动画时间在x轴上动画显示图表。
//        lineChart.animateX(1500);
        //右侧y轴设置为不使用
        barChart.getAxisRight().setEnabled(false);
//        lineChart.getAxisRight().setTextColor(Color.TRANSPARENT);//y轴字的颜色
////        lineChart.getAxisRight().setAxisLineColor(Color.TRANSPARENT);
//        lineChart.getAxisRight().setSpaceTop(5f);//将顶部轴空间设置为整个范围的百分比。默认10f（即10%）
//        lineChart.getAxisRight().setDrawGridLines(true);    //将此设置为true，以便绘制该轴的网格线
//        lineChart.getAxisRight().setGranularityEnabled(false);  //在轴值间隔上启用/禁用粒度控制。
//        lineChart.getAxisRight().setXOffset(13f);
        //左侧侧y轴设置为透明的

        barChart.getAxisLeft().setAxisLineColor(Color.TRANSPARENT);//y轴颜色透明
        barChart.getAxisLeft().setAxisMinimum(0f); // y轴最小值
        barChart.getAxisLeft().setTypeface(ResourcesCompat.getFont(context, R.font.dinmittelschriftstd));
        barChart.getAxisLeft().setTextColor(YcResources.getColor(R.color.chart_x_y_text_color));//y轴字的颜色
        barChart.getAxisLeft().setGridLineWidth(0.5f);
        barChart.getAxisLeft().setGridColor(YcResources.getColor(R.color.chart_y_grid_color));
        barChart.getAxisLeft().setGridDashedLine(new DashPathEffect(new float[]{10, 5}, 1));
        //x周相关设置
//        CustomAxisX formatter = new CustomAxisX(list);
//        xAxis.setTypeface(mTfLight);

        //将其设置为true以启用绘制该轴的网格线。
        barChart.getXAxis().setDrawGridLines(false);
        //x轴在下方
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);

        barChart.getXAxis().setDrawAxisLine(false);
        barChart.getXAxis().setLabelCount(6);//设置

        barChart.getXAxis().setAxisMinimum(-0.5f);//设置x轴最小值
//        barChart.setVisibleXRange(0,7);
        barChart.getXAxis().setLabelRotationAngle(0);//文字倾斜
        barChart.getXAxis().setYOffset(10);//x轴label(文字) 偏移量(上方)  必须调用setExtraTopOffset添加额外偏移量，否则会导致滑动时高度偏差问题
        barChart.setExtraTopOffset(10);//额外的偏移量(上方)
        barChart.setExtraBottomOffset(2);//额外的偏移量(下方) 添加偏移后会导致下方缺少一些像素
        barChart.getXAxis().setTypeface(ResourcesCompat.getFont(context, R.font.dinmittelschriftstd));
        barChart.getXAxis().setTextColor(YcResources.getColor(R.color.chart_x_y_text_color));//x轴字的颜色
        float textSize = context.getResources().getDimensionPixelSize(R.dimen.text_size_8);
        textSize = textSize / (YcUI.getDisplayMetrics().scaledDensity);
        barChart.getXAxis().setTextSize(textSize);

//        lineChart.getLegend().setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
//        lineChart.getLegend().setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
//        lineChart.getLegend().setDrawInside(true);
        barChart.getLegend().setEnabled(false);//关闭显示label
        barChart.animateXY(0, 0);
        barChart.notifyDataSetChanged();
        barChart.invalidate();//刷新
    }

    public static void setBarDataSet(BarChart barChart, List<Double> dataList1, int index, int color, boolean isHighlightEnabled) {
        ArrayList<BarEntry> entries = new ArrayList<>();
        for (int i = 0; i < dataList1.size(); i++) {
            entries.add(new BarEntry(i, dataList1.get(i).floatValue()));
        }
        BarDataSet dataSet;
        if (barChart.getData() != null && barChart.getData().getDataSetCount() > index) {
            dataSet = (BarDataSet) barChart.getData().getDataSetByIndex(index);
            dataSet.setValues(entries);
        } else {
            dataSet = new BarDataSet(entries, "");
            dataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
//            dataSet.setBarShadowColor(Color.argb(0, 0, 0, 0));
            dataSet.setColor(color);//设置颜色
//            dataSet.setDrawIcons(false);
            dataSet.setDrawValues(false);//关闭顶点上方的文字
            dataSet.setHighlightEnabled(isHighlightEnabled);
//            data.setValueFormatter(new StackedValueFormatter(false, "", 1));
//            data.setValueTextColor(Color.GREEN);
            barChart.getData().addDataSet(dataSet);
        }
    }

}
